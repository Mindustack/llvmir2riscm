package kvtodev.mindustack.llvmir2riscm.compiler.backend.rvasm;

import kvtodev.mindustack.llvmir2riscm.compiler.backend.rvasm.hierarchy.AsmBlock;
import kvtodev.mindustack.llvmir2riscm.compiler.backend.rvasm.hierarchy.AsmFunction;
import kvtodev.mindustack.llvmir2riscm.compiler.backend.rvasm.hierarchy.AsmModule;
import kvtodev.mindustack.llvmir2riscm.compiler.backend.rvasm.inst.*;
import kvtodev.mindustack.llvmir2riscm.compiler.backend.rvasm.operand.*;
import kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.Value;
import kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.constant.NumConst;
import kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.hierarchy.IRBlock;
import kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.hierarchy.IRFunction;
import kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.hierarchy.IRModule;
import kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.inst.*;
import kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.type.*;
import kvtodev.mindustack.llvmir2riscm.compiler.share.Lang;
import kvtodev.mindustack.llvmir2riscm.compiler.share.pass.IRBlockPass;
import kvtodev.mindustack.llvmir2riscm.compiler.share.pass.IRFuncPass;
import kvtodev.mindustack.llvmir2riscm.compiler.share.pass.IRInstPass;
import kvtodev.mindustack.llvmir2riscm.compiler.share.pass.IRModulePass;

import java.util.ArrayList;

public class AsmBuilder implements IRModulePass, IRFuncPass, IRBlockPass, IRInstPass {

    public final AsmModule module = new AsmModule();

    public AsmFunction CurrentFunction;
    AsmBlock CurrentBlock;

    public AsmBuilder() {
    }

    @Override
    public void runOnModule(IRModule irModule) {
        for (Value globalVariable : irModule.globalVariables) {
            globalVariable.asmOperand = heapAlloca(globalVariable.size());
        }
        for (IRFunction externFunction : irModule.externFunctions) {
            AsmFunction asmFunction = new AsmFunction(externFunction.name);

            externFunction.asmOperand = asmFunction;
            for (int i = 0; i < externFunction.operands.size(); i++) {
                Value arg = externFunction.operands.get(i);
                VirtualReg reg = new VirtualReg();
                arg.asmOperand = reg;
                asmFunction.arguments.add(reg);
            }
            AsmBlock asmBlock = new AsmBlock(asmFunction.identifier + "0");
            new AsmMoveInst(PhysicalReg.reg("pc"), PhysicalReg.reg("ra"), asmBlock);
            asmFunction.blocks.add(asmBlock);
            asmFunction.entryBlock = asmBlock;


            module.externFunctions.add(asmFunction);

        }
        AsmFunction main = null;
        for (IRFunction irFunc : irModule.functions) {
            AsmFunction function = new AsmFunction(irFunc.name);
            CurrentFunction = function;
            irFunc.asmOperand = function;

            for (IRBlock irBlock : irFunc.blocks) {
                AsmBlock block = new AsmBlock(irBlock.name);
                block.loopDepth = irBlock.loopDepth;
                irBlock.asmOperand = block;
                function.blocks.add(block);
            }
            for (IRBlock irBlock : irFunc.blocks) {
                irBlock.prevs.forEach(pre -> ((AsmBlock) irBlock.asmOperand).prevs.add((AsmBlock) pre.asmOperand));
                irBlock.nexts.forEach(nxt -> ((AsmBlock) irBlock.asmOperand).nexts.add((AsmBlock) nxt.asmOperand));
            }
            function.entryBlock = (AsmBlock) irFunc.entryBlock.asmOperand;
            function.exitBlock = (AsmBlock) irFunc.exitBlock.asmOperand;
            for (int i = 0; i < irFunc.operands.size(); i++) {
                Value arg = irFunc.operands.get(i);
                VirtualReg reg = new VirtualReg();
                arg.asmOperand = reg;
                function.arguments.add(reg);
            }
            if (irFunc.name.equals("@main")) {
                main = function;
                new AsmLiInst(PhysicalReg.reg("sp"),new Immediate(2048),main.entryBlock);
                new AsmLiInst(PhysicalReg.reg("ra"), new Immediate(-2), main.entryBlock);
            } else module.functions.add(function);

        }
//        module.functions.remove(main);
        module.functions.addFirst(main);

        irModule.functions.forEach(this::runOnFunc);
    }

    @Override
    public void runOnFunc(IRFunction function) {
        CurrentFunction = (AsmFunction) function.asmOperand;


        new AsmALUInst(Lang.SubOperation, PhysicalReg.reg("sp"), PhysicalReg.reg("sp"), new RawStackOffset(CurrentFunction.stackUse), CurrentFunction.entryBlock);


        ArrayList<Register> calleeSaveTemp = new ArrayList<>();
        for (PhysicalReg phyReg : PhysicalReg.calleeSaved) {
            VirtualReg rd = new VirtualReg();
            calleeSaveTemp.add(rd);
            new AsmMoveInst(rd, phyReg, CurrentFunction.entryBlock);
        }
        VirtualReg raTemp = new VirtualReg();
        new AsmMoveInst(raTemp, PhysicalReg.reg("ra"), CurrentFunction.entryBlock);


        for (int i = 0; i < (CurrentFunction.arguments).size(); i++) {
            new AsmMoveInst((CurrentFunction.arguments).get(i), PhysicalReg.a(i), CurrentFunction.entryBlock);
        }


        function.blocks.forEach(this::runOnBlock);
        for (int i = 0; i < PhysicalReg.calleeSaved.size(); i++) {
            new AsmMoveInst(PhysicalReg.calleeSaved.get(i), calleeSaveTemp.get(i), CurrentFunction.exitBlock);
        }

        new AsmMoveInst(PhysicalReg.reg("ra"), raTemp, CurrentFunction.exitBlock);
        new AsmALUInst(Lang.AddOperation, PhysicalReg.reg("sp"), PhysicalReg.reg("sp"), new RawStackOffset(CurrentFunction.stackUse), CurrentFunction.exitBlock);

        new AsmMoveInst(PhysicalReg.reg("pc"), PhysicalReg.reg("ra"), CurrentBlock);
    }

    private RawMemOffset heapAlloca(int size) {
        RawMemOffset rawMemOffset = new RawMemOffset(this.module.heapUse.pointed);

        module.heapUse.pointed += 1;
        return rawMemOffset;
    }

    @Override
    public void runOnBlock(IRBlock block) {
        CurrentBlock = (AsmBlock) block.asmOperand;
        for (IRBaseInst inst : block.instructions) {
            try {
                inst.accept(this);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }

    // InstSelector
    @Override
    public void visit(IRAllocaInst inst) {
        VirtualReg reg = new VirtualReg();
        inst.asmOperand = reg;
        new AsmALUInst(Lang.AddOperation, reg, PhysicalReg.reg("sp"), StackAlloc(((PointerType) inst.type).pointedType.size()), CurrentBlock);
    }

    public Immediate StackAlloc(int byteSize) {
        RawStackOffset rawStackOffset = new RawStackOffset(CurrentFunction.stackUse.pointed);
        CurrentFunction.stackUse.pointed += byteSize;
        return rawStackOffset;

    }

    @Override
    public void visit(IRBinaryInst inst) {

        String result;
        switch (inst.op) {
            case "sdiv":
                result = Lang.DivOperation;
                break;
            case "srem":
                result = Lang.ModOpertion;
                break;
            case "shl":
                result = Lang.ShiftLeftOperation;
                break;
            case "ashr":
                result = Lang.ShiftRightOperation;
                break;
            // notice: LLVM IR and RV32 Asm have many in common
            default:
                result = inst.op;
                break;
        }
        newALUInst(result, getReg(inst), inst.lhs(), inst.rhs());
    }

    @Override
    public void visit(IRBrInst inst) {
        inst.asmOperand = null;

        if (inst.isJump()) {
            new AsmJmpInst((AsmBlock) inst.destBlock().asmOperand, CurrentBlock);
            return;
        }

        if (inst.condition() instanceof IRICmpInst && ((IRICmpInst) inst.condition()).forBr()) {

            String op;
            boolean hasSecond;
            switch (((IRICmpInst) inst.condition()).op) {
                case "sgt":
                case "ugt":
                    op = Lang.LessSuffix;
                    hasSecond = true;
                    break;
                case "sge":
                case "uge":
                    op = Lang.GreaterSuffix;
                    hasSecond = false;
                    break;
                case "slt":
                case "ult":
                    op = Lang.LessSuffix;
                    hasSecond = false;
                    break;
                case "sle":
                case "ule":
                    op = Lang.GreaterSuffix;
                    hasSecond = true;
                    break;
                case "eq":
                    op = Lang.EqualSuffix;
                    hasSecond = true;
                    break;
                case "ne":
                    op = Lang.NotEqualSuffix;
                    hasSecond = true;
                    break;

                default:
                    op = ((IRICmpInst) inst.condition()).op;
                    hasSecond = false;
                    break;
            }
            if (hasSecond)
                new AsmBrInst(op, getReg(((IRICmpInst) inst.condition()).rhs()), getReg(((IRICmpInst) inst.condition()).lhs()), (AsmBlock) inst.ifTrueBlock().asmOperand, CurrentBlock);
            else
                new AsmBrInst(op, getReg(((IRICmpInst) inst.condition()).lhs()), getReg(((IRICmpInst) inst.condition()).rhs()), (AsmBlock) inst.ifTrueBlock().asmOperand, CurrentBlock);
        } else {
            new AsmBrInst(Lang.NotEqualSuffix, getReg(inst.condition()), getReg(new NumConst(0)), (AsmBlock) inst.ifTrueBlock().asmOperand, CurrentBlock);
        }

        new AsmJmpInst((AsmBlock) inst.ifFalseBlock().asmOperand, CurrentBlock);
    }

    @Override
    public void visit(IRCallInst inst) {


        AsmFunction callFunc = ((AsmFunction) inst.getCallFunc().asmOperand);
        for (int i = 0; i < callFunc.arguments.size(); i++) {
            newMoveInst(PhysicalReg.a(i), inst.getArg(i));
        }

        new AsmCallInst(callFunc, CurrentBlock);

        if (!inst.getCallFunc().isVoid()) {
            new AsmMoveInst(getReg(inst), PhysicalReg.reg("a0"), CurrentBlock);
        }


    }

    @Override
    public void visit(IRGetElementPtrInst inst) {

        IRBaseType type = ((PointerType) inst.operands.getFirst().type).pointedType;
        Register instReg = getReg(inst);

        VirtualReg virtualReg = new VirtualReg();
        Value operand;
        for (int i = 1; i < inst.operands.size(); ++i) {

            operand = inst.getOperand(i);

            int elementSize;
            if (type instanceof ArrayType) {

                type = ((ArrayType) type).elementType;
                elementSize = type.size();

                newALUInst(Lang.MulOperation, virtualReg, operand, new NumConst(elementSize));
                new AsmALUInst(Lang.AddOperation, instReg, instReg, virtualReg, CurrentBlock);

            } else if (type instanceof StructType) {

                assert operand instanceof NumConst;

                var memberVarTypes = ((StructType) type).memberVarTypes;

                var constData = ((NumConst) operand).constData;

                new AsmLiInst(virtualReg, new Immediate(((StructType) type).memberOffset(((NumConst) operand).constData.intValue())), CurrentBlock);
                type = memberVarTypes.get(constData.intValue());
                new AsmALUInst(Lang.AddOperation, instReg, instReg, virtualReg, CurrentBlock);
            } else if (type instanceof PointerType) {
                type = ((PointerType) type).pointedType;
                elementSize = type.size();

                newALUInst(Lang.MulOperation, virtualReg, operand, new NumConst(elementSize));
                new AsmALUInst(Lang.AddOperation, instReg, instReg, virtualReg, CurrentBlock);

            }
            else if(type instanceof NumType){
                new AsmALUInst(Lang.AddOperation,instReg,instReg,getReg(operand),CurrentBlock);
//                break;
            }
        }
        new AsmALUInst(Lang.AddOperation, instReg, instReg, getReg(inst.operands.getFirst()), CurrentBlock);
    }

    @Override
    public void visit(IRICmpInst inst) {
        if (inst.forBr()) return;

        Register instReg = getReg(inst);
        switch (inst.op) {

            case "slt":
            case "ult":
                newALUInst(Lang.LessThanOperation, instReg, inst.lhs(), inst.rhs());
                break;

            case "sgt":
            case "":
                newALUInst(Lang.GreaterThanOperation, instReg, inst.rhs(), inst.lhs());
                break;
            case "sge":
            case "uge":
                newALUInst(Lang.GreaterThanEqOperation, instReg, inst.lhs(), inst.rhs());
                break;
            case "sle":
            case "ule":
                newALUInst(Lang.LessThanEqOperation, instReg, inst.rhs(), inst.lhs());
                break;
            case "eq": {
                newALUInst(Lang.EqualOperation, instReg, inst.lhs(), inst.rhs());
                break;
            }
            case "ne": {
                newALUInst(Lang.NotEqualOperation, instReg, inst.lhs(), inst.rhs());
                break;
            }
            default:
                throw new InternalError("unknown ASM compare op");
        }
    }

    @Override
    public void visit(IRLoadInst inst) {
        Register instReg = getReg(inst);
        if (inst.loadPtr().asmOperand instanceof RawStackOffset) {
            new AsmLoadInst(instReg, PhysicalReg.reg("sp"), getImm(inst.loadPtr()), CurrentBlock);
        } else if (inst.loadPtr().asmOperand instanceof RawMemOffset) {
            new AsmLoadInst(instReg, PhysicalReg.reg("zero"), getImm(inst.loadPtr()), CurrentBlock);
        } else {

            new AsmLoadInst(instReg, getReg(inst.loadPtr()), new Immediate(0), CurrentBlock);
        }
    }

    @Override
    public void visit(IRRetInst inst) {
        if (!inst.isVoid()) newMoveInst(PhysicalReg.reg("a0"), inst.retVal());
    }

    @Override
    public void visit(IRStoreInst inst) {
        if (inst.storePtr().asmOperand instanceof RawStackOffset) {
            new AsmStoreInst(PhysicalReg.reg("sp"), getReg(inst.storeValue()), getImm(inst.storePtr()), CurrentBlock);
        } else if (inst.storePtr().asmOperand instanceof RawMemOffset) {
            new AsmStoreInst(PhysicalReg.reg("zero"), getReg(inst.storeValue()), getImm(inst.storePtr()), CurrentBlock);
        } else {

            new AsmStoreInst(getReg(inst.storePtr()), getReg(inst.storeValue()), new Immediate(0), CurrentBlock);
        }

    }

    @Override
    public void visit(IRCastInst inst) {
        newMoveInst(getReg(inst), inst.getOperand(0));
    }

    @Override
    public void visit(IRMoveInst inst) {
        newMoveInst(getReg(inst.dest()), inst.source());
    }

    public void newMoveInst(Register dest, Value source) {
        if (source.asmOperand instanceof Immediate) {
            new AsmLiInst(dest, getImm(source), CurrentBlock);
        } else {
            new AsmMoveInst(dest, getReg(source), CurrentBlock);
        }
    }

    private AsmALUInst newALUInst(String rvOp, Register dest, Value lhs, Value rhs) {

        if (rhs.asmOperand instanceof Immediate) {
            //1r 1i
            return new AsmALUInst(rvOp, dest, getReg(lhs), getImm(rhs), CurrentBlock);
        } else if (rvOp.equals(Lang.ShiftLeftOperation) || rvOp.equals(Lang.ShiftRightOperation)) {
            //1r
            new AsmALUInst(rvOp, dest, getReg(lhs), CurrentBlock);
        }
        return new AsmALUInst(rvOp, dest, getReg(lhs), getReg(rhs), CurrentBlock);
    }

    public Register getReg(Value value) {

        if (value.asmOperand != null) {
            if (value.asmOperand instanceof RawStackOffset) {
                VirtualReg virtualReg = new VirtualReg();
                new AsmLoadInst(virtualReg, PhysicalReg.reg("sp"), (Immediate) value.asmOperand, CurrentBlock);
                return virtualReg;

            } else if (value.asmOperand instanceof RawMemOffset) {
                VirtualReg virtualReg = new VirtualReg();
                new AsmLoadInst(virtualReg, PhysicalReg.reg("zero"), (Immediate) value.asmOperand, CurrentBlock);
                return virtualReg;

            }
            return (Register) value.asmOperand;
        }

        // raw data
        if (value instanceof NumConst) {

            Number intValue = 0;
            intValue = ((NumConst) value).constData;
            Register ret;
            if (CurrentBlock.recordLi.containsKey(intValue)) {
                ret =CurrentBlock.recordLi.get(intValue);
            } else {
                ret = new VirtualReg();
                new AsmLiInst(ret, new Immediate(intValue), CurrentBlock);
                CurrentBlock.recordLi.put(intValue, ret);
            }
            return ret;
        }

        VirtualReg newReg = new VirtualReg();
        value.asmOperand = newReg;
        return newReg;
    }

    public Immediate getImm(Value value) {
        if (value.asmOperand instanceof RawStackOffset || value.asmOperand instanceof RawMemOffset)
            return (Immediate) value.asmOperand;
        // if (value instanceof NumConst)
        return new Immediate(((NumConst) value).constData);
    }

}

