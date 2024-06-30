package kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir;

import kvtodev.mindustack.llvmir2riscm.compiler.middleend.analyzer.MiddleendAnalyzer;
import kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.constant.ArrayConst;
import kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.constant.BaseConst;
import kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.constant.NumConst;
import kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.constant.StructConst;
import kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.hierarchy.IRBlock;
import kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.hierarchy.IRFunction;
import kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.hierarchy.IRModule;
import kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.inst.*;
import kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.type.*;
import kvtodev.mindustack.llvmir2riscm.compiler.share.Lang;
import kvtodev.mindustack.llvmir2riscm.llvmir2riscm;
import kvtodev.mindustack.llvmir2riscm.parser.LLVMIR.LLVMIRBaseVisitor;
import kvtodev.mindustack.llvmir2riscm.parser.LLVMIR.LLVMIRLexer;
import kvtodev.mindustack.llvmir2riscm.parser.LLVMIR.LLVMIRParser;
import kvtodev.mindustack.llvmir2riscm.parser.LLVMIR.LLVMIRParser.FuncDefContext;
import kvtodev.mindustack.llvmir2riscm.parser.LLVMIR.LLVMIRParser.IncContext;
import kvtodev.mindustack.llvmir2riscm.parser.LLVMIR.LLVMIRParser.ParamContext;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.io.IOException;
import java.util.*;

public class IRBuilder extends LLVMIRBaseVisitor<Value> {

    static IRBlock CurrentBlock;
    static int counter = 0;
    public Map<String, Value> valueMap;
    public IRModule irModule = new IRModule();
    Value infoValue = new Value("infoValue", null);
    IRFunction CurrentFunction;
    Map<String, Value> GlobalValueMap = new HashMap<>();
    private MiddleendAnalyzer analyzer;

    public IRBuilder(CharStream charStream) throws IOException {
        run(charStream);
    }

    public IRBuilder() {
    }

    private void log(String msg) {
        llvmir2riscm.logger.info(msg);
    }

    private String parseLabel(TerminalNode terminalNode) {
        String label;
        if (terminalNode == null) {
            label = CurrentFunction.name + '0';
        } else {

            label = terminalNode.getText();
            label = '%' + label.substring(0, label.length() - 1);
        }
        return label;
    }

    @Override
    public Value visitSExtInst(LLVMIRParser.SExtInstContext ctx) {
        return new IRCastInst(visit(ctx.typeValue()), visitType(ctx.type()).type);
    }

    @Override
    public Value visitFenceInst(LLVMIRParser.FenceInstContext ctx) {
        throw new RuntimeException("unsupported llvmir Inst");
    }

    @Override
    public Value visitFNegInst(LLVMIRParser.FNegInstContext ctx) {
        throw new RuntimeException("unsupported llvmir Inst");
    }

    @Override
    public Value visitAShrInst(LLVMIRParser.AShrInstContext ctx) {
        throw new RuntimeException("unsupported llvmir Inst");
    }

    @Override
    public Value visitExtractElementInst(LLVMIRParser.ExtractElementInstContext ctx) {
        throw new RuntimeException("unsupported llvmir Inst");
    }

    @Override
    public Value visitInsertElementInst(LLVMIRParser.InsertElementInstContext ctx) {
        throw new RuntimeException("unsupported llvmir Inst");
    }

    @Override
    public Value visitShuffleVectorInst(LLVMIRParser.ShuffleVectorInstContext ctx) {
        throw new RuntimeException("unsupported llvmir Inst");
    }

    @Override
    public Value visitCmpXchgInst(LLVMIRParser.CmpXchgInstContext ctx) {
        throw new RuntimeException("unsupported llvmir Inst");
    }

    @Override
    public Value visitAtomicRMWInst(LLVMIRParser.AtomicRMWInstContext ctx) {
        throw new RuntimeException("unsupported llvmir Inst");
    }

    @Override
    public Value visitFpTruncInst(LLVMIRParser.FpTruncInstContext ctx) {
        throw new RuntimeException("unsupported llvmir Inst");
    }

    @Override
    public Value visitFpExtInst(LLVMIRParser.FpExtInstContext ctx) {
        throw new RuntimeException("unsupported llvmir Inst");
    }

    @Override
    public Value visitFpToUiInst(LLVMIRParser.FpToUiInstContext ctx) {
        return new IRCastInst(visit(ctx.typeValue()), visitType(ctx.type()).type);
//        throw new RuntimeException("unsupported llvmir Inst");
    }

    @Override
    public Value visitFpToSiInst(LLVMIRParser.FpToSiInstContext ctx) {
        return new IRCastInst(visit(ctx.typeValue()), visitType(ctx.type()).type);
//        throw new RuntimeException("unsupported llvmir Inst");
    }

    @Override
    public Value visitUiToFpInst(LLVMIRParser.UiToFpInstContext ctx) {
        return new IRCastInst(visit(ctx.typeValue()), visitType(ctx.type()).type);
    }

    @Override
    public Value visitSiToFpInst(LLVMIRParser.SiToFpInstContext ctx) {
        return new IRCastInst(visit(ctx.typeValue()), visitType(ctx.type()).type);
//        throw new RuntimeException("unsupported llvmir Inst");
    }

    @Override
    public Value visitIntToPtrInst(LLVMIRParser.IntToPtrInstContext ctx) {
        return new IRCastInst(visit(ctx.typeValue()), visitType(ctx.type()).type);
//        throw new RuntimeException("unsupported llvmir Inst");
    }

    @Override
    public Value visitAddrSpaceCastInst(LLVMIRParser.AddrSpaceCastInstContext ctx) {
        throw new RuntimeException("unsupported llvmir Inst");
    }

    @Override
    public Value visitFCmpInst(LLVMIRParser.FCmpInstContext ctx) {
        throw new RuntimeException("unsupported llvmir Inst");
    }

    @Override
    public Value visitFreezeInst(LLVMIRParser.FreezeInstContext ctx) {
        throw new RuntimeException("unsupported llvmir Inst");
    }

    @Override
    public Value visitVaargInst(LLVMIRParser.VaargInstContext ctx) {
        throw new RuntimeException("unsupported llvmir Inst");
    }

    @Override
    public Value visitLandingPadInst(LLVMIRParser.LandingPadInstContext ctx) {
        throw new RuntimeException("unsupported llvmir Inst");
    }

    @Override
    public Value visitCatchPadInst(LLVMIRParser.CatchPadInstContext ctx) {
        throw new RuntimeException("unsupported llvmir Inst");
    }

    @Override
    public Value visitCleanupPadInst(LLVMIRParser.CleanupPadInstContext ctx) {
        throw new RuntimeException("unsupported llvmir Inst");
    }

    public void run(CharStream charStream) {
        LLVMIRLexer irLexer = new LLVMIRLexer(charStream);
        LLVMIRParser irParser = new LLVMIRParser(new CommonTokenStream(irLexer));
        visitCompilationUnit(irParser.compilationUnit());
        log("Build Module finish from .ll file.");
    }

    @Override
    public Value visitCompilationUnit(LLVMIRParser.CompilationUnitContext ctx) {
        log(ctx.topLevelEntity(0).getText());
        var iterator = ctx.topLevelEntity().listIterator();
        List<FuncDefContext> funcCtx = new ArrayList<>();
        valueMap = GlobalValueMap;
        while (iterator.hasNext()) {
            var context = iterator.next();

            if (context.funcDecl() != null) {
                var irFunction = (IRFunction) visitFuncDecl(context.funcDecl());
                irModule.externFunctions.add(irFunction);

            } else if (context.funcDef() != null) {
                IRFunction irFunction = (IRFunction) visitFuncHeader(context.funcDef().funcHeader());
                irFunction.parentModule = irModule;
                irModule.functions.add(irFunction);
                funcCtx.add(context.funcDef());
            } else if (context.globalDef() != null) {
                irModule.globalVariables.add(visitGlobalDef(context.globalDef()));
            } else if (context.typeDef() != null) {
                visitTypeDef(context.typeDef());
            }
        }
        funcCtx.forEach(this::visitFuncDef);
        //assume that after clang's compilation there is no value with same name in different func
        return null;
    }

    @Override
    public Value visitTypeDef(LLVMIRParser.TypeDefContext ctx) {

        String TypeName = ctx.LocalIdent().getText();
        var type = visitType(ctx.type()).type;
        return newValue(TypeName, type);
    }

    @Override
    public Value visitGlobalDef(LLVMIRParser.GlobalDefContext ctx) {
        String glbName = ctx.GlobalIdent().getText();

        Value glbVar = newValue(glbName, new PointerType(visitType(ctx.type()).type));

        if (ctx.constant() != null) {
            glbVar.initValue = (BaseConst) visit(ctx.constant());

        } else {
            glbVar.initValue = new NumConst(0);
        }

        return setValue(glbName, glbVar);

    }

    @Override
    public Value visitFuncDecl(LLVMIRParser.FuncDeclContext ctx) {
        return visitFuncHeader(ctx.funcHeader());
    }

    @Override
    public Value visitFuncDef(LLVMIRParser.FuncDefContext ctx) {
        valueMap = new HashMap<>();
        IRFunction function = (IRFunction) getValue(ctx.funcHeader().GlobalIdent().getText());
        function.operands.forEach(v -> setValue(v.name, v));
        CurrentFunction = function;
        analyzer = new MiddleendAnalyzer();

        for (var blockCtx : ctx.funcBody().basicBlock()) {
            TerminalNode terminalNode = blockCtx.LabelIdent();
            IRBlock block = new IRBlock(parseLabel(terminalNode));
            setValue(block.name, block);
            function.addBlock(block);
        }
        ctx.funcBody().basicBlock().forEach(this::visitBasicBlock);
        analyzer.runOnFunc(function);

        solveRawOnlyName();
        return function;
    }

    @Override
    public Value visitFuncHeader(LLVMIRParser.FuncHeaderContext ctx) {
        String funcName = ctx.GlobalIdent().getText();

        IRBaseType retType = visitType(ctx.type()).type;

        IRFunction function = new IRFunction(funcName, retType);

        List<LLVMIRParser.ParamContext> params = ctx.params().param();
        for (ParamContext param : params) {
            String name;
            if (param.LocalIdent() != null) {
                name = param.LocalIdent().getText();

            } else {
                name = "";
            }
            IRBaseType argType = visitType(param.type()).type;
            function.argTypes.add(argType);
            function.operands.add(new Value(name, argType));
        }
        return setValue(funcName, function);
    }

    @Override
    public Value visitBasicBlock(LLVMIRParser.BasicBlockContext ctx) {
        TerminalNode terminalNode = ctx.LabelIdent();
        IRBlock block = (IRBlock) getValue(parseLabel(terminalNode));

        CurrentBlock = block;
        for (var instCtx : ctx.instruction()) {
            Value visit = visit(instCtx);
            if (!(visit instanceof IRBaseInst)) continue;
            IRBaseInst inst = (IRBaseInst) visit;
            CurrentBlock.addInst(inst);
        }
        IRBaseInst inst = (IRBaseInst) ctx.terminator().accept(this);
        if (inst == null) return block;
        CurrentBlock.addInst(inst);

        return block;
    }

    @Override
    public Value visitTerminator(LLVMIRParser.TerminatorContext ctx) {

        return visitChildren(ctx);
    }

    @Override
    public Value visitRetTerm(LLVMIRParser.RetTermContext ctx) {
        if (ctx.value() != null) {
            return new IRRetInst(visit(ctx.value()));
        }
        return new IRRetInst();
    }

    @Override
    public Value visitBrTerm(LLVMIRParser.BrTermContext ctx) {

        return new IRBrInst((IRBlock) visit(ctx.label()));

    }

    @Override
    public Value visitCondBrTerm(LLVMIRParser.CondBrTermContext ctx) {
        return new IRBrInst(visit(ctx.value()), (IRBlock) visit(ctx.label(0)), (IRBlock) visit(ctx.label(1)));

    }

    @Override
    public Value visitCase(LLVMIRParser.CaseContext ctx) {
        IRICmpInst iriCmpInst = new IRICmpInst("eq", infoValue, visitTypeConst(ctx.typeConst()));

        iriCmpInst.name = CurrentBlock.name + "_check";
        IRBrInst irBrInst = new IRBrInst(iriCmpInst, (IRBlock) getValue(visitLabel(ctx.label()).name), (IRBlock) infoValue.object);
        CurrentBlock.addInst(iriCmpInst);
        CurrentBlock.addInst(irBrInst);
        return null;
    }

    @Override
    public Value visitSwitchTerm(LLVMIRParser.SwitchTermContext ctx) {

        Value check = visitTypeValue(ctx.typeValue());
        String base = CurrentBlock.name;
        IRBlock theDefault = (IRBlock) getValue(visitLabel(ctx.label()).name);
        IRBlock next;
        List<LLVMIRParser.CaseContext> caseContexts = ctx.case_();
        for (int i = 0; i < caseContexts.size(); i++) {
            infoValue = check;


            LLVMIRParser.CaseContext caseContext = caseContexts.get(i);
            if (i == caseContexts.size() - 1) {
                next = theDefault;
            } else {
                next = new IRBlock(base + "case" + i);
                CurrentFunction.addBlock(next);
            }
            infoValue.object = next;
            visitCase(caseContext);
            CurrentBlock = next;
        }
        return null;
    }

    @Override
    public Value visitLabel(LLVMIRParser.LabelContext ctx) {
        String name = ctx.LocalIdent().getSymbol().getText();

        return getValue(name);

    }

    @Override
    public Value visitBoolConst(LLVMIRParser.BoolConstContext ctx) {
        return new NumConst(Objects.equals(ctx.getText(), "true"));
    }

    @Override
    public Value visitIntConst(LLVMIRParser.IntConstContext ctx) {
        String text = ctx.IntLit().getText();
        if (text.startsWith("0x")) return new NumConst(Long.parseLong(text.substring(2), 16));
        return new NumConst((Integer.parseInt(text)));

    }

    @Override
    public Value visitFloatConst(LLVMIRParser.FloatConstContext ctx) {
        String text = ctx.FloatLit().getText();
        if (text.startsWith("0x")) return new NumConst(Double.longBitsToDouble(Long.parseLong(text.substring(2), 16)));
        return new NumConst((Float.parseFloat(text)));
    }

    @Override
    public Value visitNullConst(LLVMIRParser.NullConstContext ctx) {
        return new NumConst();
    }

    @Override
    public Value visitStructConst(LLVMIRParser.StructConstContext ctx) {
        var structConst = new StructConst(infoValue.type);
        for (var c : ctx.typeConst()) {
            var value = visit(c);
            if (value instanceof BaseConst) {
                structConst.constData.add((BaseConst) value);
            } else {
                var e = new NumConst();
                structConst.constData.add(e);
            }
        }

        return structConst;
    }

    @Override
    public Value visitArrayConst(LLVMIRParser.ArrayConstContext ctx) {

        var arrayConst = new ArrayConst(infoValue.type, ctx.typeConst().size());
        for (var c : ctx.typeConst()) {
            arrayConst.constData.add((BaseConst) visit(c));
        }

        return arrayConst;
    }

    @Override
    public Value visitZeroInitializerConst(LLVMIRParser.ZeroInitializerConstContext ctx) {
        return new NumConst(0);
    }

    @Override
    public Value visitConstantExpr(LLVMIRParser.ConstantExprContext ctx) {

        var inst = visitChildren(ctx);

        return setValue(infoValue.name, inst);

    }

    @Override
    public Value visitTypeConst(LLVMIRParser.TypeConstContext ctx) {
        visitFirstClassType(ctx.firstClassType());
        return visitConstant(ctx.constant());
    }

    @Override
    public Value visitTypeValue(LLVMIRParser.TypeValueContext ctx) {
        visitFirstClassType(ctx.firstClassType());
        return visit(ctx.value());
    }

    @Override
    public Value visitValue(LLVMIRParser.ValueContext ctx) {
        if (ctx.LocalIdent() != null) {
            return getValue(ctx.LocalIdent().getText());
        }
        return visitChildren(ctx);

    }

    @Override
    public Value visitType(LLVMIRParser.TypeContext ctx) {
        if (ctx.getText().equals("void")) {
            infoValue.type = new VoidType();
            return infoValue;
        }
        return visitChildren(ctx);

    }

    @Override
    public Value visitFirstClassType(LLVMIRParser.FirstClassTypeContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public Value visitConcreteType(LLVMIRParser.ConcreteTypeContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public Value visitIntType(LLVMIRParser.IntTypeContext ctx) {
        infoValue.type = new NumType();
        return infoValue;
    }

    @Override
    public Value visitFloatType(LLVMIRParser.FloatTypeContext ctx) {
        infoValue.type = new NumType();
        return infoValue;
    }

    @Override
    public Value visitPointerType(LLVMIRParser.PointerTypeContext ctx) {
        if (ctx.opaquePointerType() != null) {
            return visitOpaquePointerType(ctx.opaquePointerType());
        }
        infoValue.type = new PointerType(visitType(ctx.type()).type);

        return infoValue;
    }

    @Override
    public Value visitLabelType(LLVMIRParser.LabelTypeContext ctx) {
        infoValue.type = new LabelType();
        return infoValue;
    }

    @Override
    public Value visitArrayType(LLVMIRParser.ArrayTypeContext ctx) {
        infoValue.type = new ArrayType(visitType(ctx.type()).type, Integer.parseInt(ctx.IntLit().getText()));
        return infoValue;
    }

    @Override
    public Value visitStructType(LLVMIRParser.StructTypeContext ctx) {
        var structType = new StructType();
        for (var c : ctx.type()) {
            structType.memberVarTypes.add(visitType(c).type);
        }

        infoValue.type = structType;
        return infoValue;

    }

    @Override
    public Value visitNamedType(LLVMIRParser.NamedTypeContext ctx) {

        infoValue.type = getValue(ctx.LocalIdent().getText()).type;
        return infoValue;
    }

    @Override
    public Value visitOpaquePointerType(LLVMIRParser.OpaquePointerTypeContext ctx) {
        infoValue.type = new PointerType(new VoidType());
        return infoValue;
    }

    @Override
    public Value visitGetElementPtrExpr(LLVMIRParser.GetElementPtrExprContext ctx) {

        List<LLVMIRParser.GepIndexContext> typeValueContexts = ctx.gepIndex();
        ArrayList<Value> indices = new ArrayList<>();

        for (int i = 0; i < typeValueContexts.size(); i++) {
            var offsetCtx = typeValueContexts.get(i);
            indices.add(offsetCtx.typeConst().accept(this));
        }
        IRGetElementPtrInst inst = new IRGetElementPtrInst(visitTypeConst(ctx.typeConst()), null, indices);
        for (int i = 1; i < inst.indicesNum(); ++i) {

            if (inst.type instanceof StructType) {
                inst.type = ((StructType) inst.type).memberVarTypes.get(((NumConst) indices.get(i)).constData.intValue());
            } else if (inst.type instanceof ArrayType) {
                inst.type = ((ArrayType) inst.type).elementType;
            } else if (inst.type instanceof PointerType) {
                inst.type = // (PointerType) inst.type;
                        ((PointerType) inst.type).pointedType;
            } else if (inst.type instanceof VoidType) {
                break;
            } else {
                throw new InternalError("getelementptr in other types");

            }

        }

        inst.type = new PointerType(inst.type);
        return inst;
    }

    @Override
    public Value visitLocalDefInst(LLVMIRParser.LocalDefInstContext ctx) {
        infoValue.name = ctx.LocalIdent().getText();
        var value = visit(ctx.valueInstruction());
        //here null means it comes from a phi or
        if (value == null) return newValue(ctx.LocalIdent().getText(), infoValue.type);
        setValue(ctx.LocalIdent().getText(), value);
        return value;

    }

    @Override
    public Value visitValueInstruction(LLVMIRParser.ValueInstructionContext ctx) {

        return visitChildren(ctx);
    }

    @Override
    public Value visitStoreInst(LLVMIRParser.StoreInstContext ctx) {
        return new IRStoreInst(visit(ctx.typeValue(1)), visit(ctx.typeValue(0)));
    }

    @Override
    public Value visitAddInst(LLVMIRParser.AddInstContext ctx) {
        IRBinaryInst inst = new IRBinaryInst("add", new NumType(), visit(ctx.typeValue()), visit(ctx.value()));
        return inst;
    }

    @Override
    public Value visitFAddInst(LLVMIRParser.FAddInstContext ctx) {
        IRBinaryInst inst = new IRBinaryInst("add", new NumType(), visit(ctx.typeValue()), visit(ctx.value()));
        return inst;
    }

    @Override
    public Value visitSubInst(LLVMIRParser.SubInstContext ctx) {

        IRBinaryInst inst = new IRBinaryInst("sub", new NumType(), visit(ctx.typeValue()), visit(ctx.value()));
        return inst;
    }

    @Override
    public Value visitFSubInst(LLVMIRParser.FSubInstContext ctx) {
        IRBinaryInst inst = new IRBinaryInst("sub", new NumType(), visit(ctx.typeValue()), visit(ctx.value()));
        return inst;
    }

    @Override
    public Value visitMulInst(LLVMIRParser.MulInstContext ctx) {
        IRBinaryInst inst = new IRBinaryInst("mul", new NumType(), visit(ctx.typeValue()), visit(ctx.value()));
        return inst;
    }

    @Override
    public Value visitFMulInst(LLVMIRParser.FMulInstContext ctx) {
        IRBinaryInst inst = new IRBinaryInst("mul", new NumType(), visit(ctx.typeValue()), visit(ctx.value()));
        return inst;
    }

    @Override
    public Value visitUDivInst(LLVMIRParser.UDivInstContext ctx) {
        IRBinaryInst inst = new IRBinaryInst("div", new NumType(), visit(ctx.typeValue()), visit(ctx.value()));
        return inst;
    }

    @Override
    public Value visitSDivInst(LLVMIRParser.SDivInstContext ctx) {
        IRBinaryInst inst = new IRBinaryInst("div", new NumType(), visit(ctx.typeValue()), visit(ctx.value()));
        return inst;
    }

    @Override
    public Value visitFDivInst(LLVMIRParser.FDivInstContext ctx) {
        IRBinaryInst inst = new IRBinaryInst("div", new NumType(), visit(ctx.typeValue()), visit(ctx.value()));
        return inst;
    }

    @Override
    public Value visitURemInst(LLVMIRParser.URemInstContext ctx) {
        IRBinaryInst inst = new IRBinaryInst("mod", new NumType(), visit(ctx.typeValue()), visit(ctx.value()));
        return inst;
    }

    @Override
    public Value visitSRemInst(LLVMIRParser.SRemInstContext ctx) {
        IRBinaryInst inst = new IRBinaryInst("mod", new NumType(), visit(ctx.typeValue()), visit(ctx.value()));
        return inst;
    }

    @Override
    public Value visitFRemInst(LLVMIRParser.FRemInstContext ctx) {
        IRBinaryInst inst = new IRBinaryInst("mod", new NumType(), visit(ctx.typeValue()), visit(ctx.value()));
        return inst;
    }

    @Override
    public Value visitShlInst(LLVMIRParser.ShlInstContext ctx) {
        IRBinaryInst inst = new IRBinaryInst("shl", new NumType(), visit(ctx.typeValue()), visit(ctx.value()));
        return inst;
    }

    @Override
    public Value visitLShrInst(LLVMIRParser.LShrInstContext ctx) {
        IRBinaryInst inst = new IRBinaryInst("lshr", new NumType(), visit(ctx.typeValue()), visit(ctx.value()));
        return inst;
    }

    @Override
    public Value visitAndInst(LLVMIRParser.AndInstContext ctx) {
        IRBinaryInst inst = new IRBinaryInst("and", new NumType(), visit(ctx.typeValue()), visit(ctx.value()));
        return inst;
    }

    @Override
    public Value visitOrInst(LLVMIRParser.OrInstContext ctx) {
        IRBinaryInst inst = new IRBinaryInst("or", new NumType(), visit(ctx.typeValue()), visit(ctx.value()));
        return inst;

    }

    @Override
    public Value visitXorInst(LLVMIRParser.XorInstContext ctx) {
        IRBinaryInst inst = new IRBinaryInst("xor", new NumType(), visit(ctx.typeValue()), visit(ctx.value()));
        return inst;
    }

    @Override
    public Value visitExtractValueInst(LLVMIRParser.ExtractValueInstContext ctx) {
        var value = visit(ctx.typeValue());
        List<TerminalNode> intLit = ctx.IntLit();
        String name = "";
        for (int i = 0; i < intLit.size(); i++) {
            name = name + "_" + Integer.parseInt(intLit.get(i).getText());
        }

        return new IRMoveInst(newValue(infoValue.name, new NumType()), getValue(value.name + name));

    }

    @Override
    public Value visitInsertValueInst(LLVMIRParser.InsertValueInstContext ctx) {

        var oldAggre = visit(ctx.typeValue(0)).name;

        var type = infoValue.type;
        List<String> regs = type.toReg(new ArrayList<String>(), "");
        List<TerminalNode> intLit = ctx.IntLit();
        String newAggre = visit(ctx.typeValue(1)).name;
        String target = "";
        for (int i = 0; i < intLit.size(); i++) {
            target += Integer.parseInt(intLit.get(i).getText());
        }
        regs.remove(target);
        for (String regs2 : regs) {
            CurrentBlock.addInst(new IRMoveInst(newValue(newAggre + regs2, new NumType()), getValue(oldAggre + regs2)));

        }
        return new IRMoveInst(newValue(newAggre + target, new NumType()), getValue(oldAggre + target));
    }

    @Override
    public Value visitAllocaInst(LLVMIRParser.AllocaInstContext ctx) {

        return new IRAllocaInst(newValue(infoValue.name, new PointerType(visitType(ctx.type()).type)));

    }

    @Override
    public Value visitLoadInst(LLVMIRParser.LoadInstContext ctx) {
        IRLoadInst inst = new IRLoadInst(visit(ctx.typeValue()));
        return inst;
    }

    @Override
    public Value visitGetElementPtrInst(LLVMIRParser.GetElementPtrInstContext ctx) {
        List<LLVMIRParser.TypeValueContext> typeValueContexts = ctx.typeValue();
        LLVMIRParser.TypeValueContext typeValueContext0 = ctx.typeValue(0);
        LLVMIRParser.TypeContext type = ctx.type();
        ArrayList<Value> indices = new ArrayList<>();

        for (int i = 1; i < typeValueContexts.size(); i++) {
            var offsetCtx = typeValueContexts.get(i);
            indices.add(offsetCtx.value().accept(this));
        }

        IRGetElementPtrInst inst = new IRGetElementPtrInst(visitTypeValue(typeValueContext0), visitType(type).type, indices);

        for (int i = 1; i < inst.indicesNum(); ++i) {

            if (inst.type instanceof StructType) {
                inst.type = ((StructType) inst.type).memberVarTypes.get(((NumConst) indices.get(i)).constData.intValue());
            } else if (inst.type instanceof ArrayType) {
                inst.type = ((ArrayType) inst.type).elementType;
            } else if (inst.type instanceof PointerType) {
                inst.type = ((PointerType) inst.type).pointedType;
            } else if (inst.type instanceof VoidType) {
                break;
            } else {
                throw new InternalError("getelementptr in other types");

            }

        }
        inst.type = new PointerType(inst.type);
        return inst;
    }

    @Override
    public Value visitTruncInst(LLVMIRParser.TruncInstContext ctx) {
        IRCastInst inst = new IRCastInst(visit(ctx.typeValue()), visitType(ctx.type()).type);
        return inst;
    }

    @Override
    public Value visitZExtInst(LLVMIRParser.ZExtInstContext ctx) {
        return new IRCastInst(visit(ctx.typeValue()), visitType(ctx.type()).type);
    }

    @Override
    public Value visitPtrToIntInst(LLVMIRParser.PtrToIntInstContext ctx) {

        return new IRCastInst(visit(ctx.typeValue()), visitType(ctx.type()).type);
    }

    @Override
    public Value visitBitCastInst(LLVMIRParser.BitCastInstContext ctx) {
        IRCastInst inst = new IRCastInst(visit(ctx.typeValue()), visitType(ctx.type()).type);
        return inst;
    }

    @Override
    public Value visitICmpInst(LLVMIRParser.ICmpInstContext ctx) {

        String op = ctx.iPred().getText();

        IRICmpInst inst = new IRICmpInst(op, visit(ctx.typeValue()), visit(ctx.value()));

        return inst;
    }

    @Override
    public Value visitPhiInst(LLVMIRParser.PhiInstContext ctx) {

        String originVarName = infoValue.name;
        for (IncContext phi : ctx.inc()) {
            IRBlock origin = (IRBlock) visit(phi.LocalIdent());
            IRBlock block = new IRBlock(origin.name + "_" + CurrentBlock.name);
            IRMoveInst mvinst = new IRMoveInst(getValue(originVarName), visit(phi.value()));
            IRBrInst br = new IRBrInst(CurrentBlock);
            block.addInst(mvinst);
            block.addInst(br);
            // add new block to func
            analyzer.addRedirect(origin, CurrentBlock, block);
            CurrentFunction.addBlock(block);
        }
        visit(ctx.type());
        return null;
    }

    @Override
    public Value visitSelectInst(LLVMIRParser.SelectInstContext ctx) {// todo
        Value temp = infoValue;
        String name = infoValue.name;
        IRBlock trueBlock = new IRBlock(name + "_1");
        IRBlock falseBlock = new IRBlock(name + "_2");
        IRBlock block = new IRBlock(CurrentBlock.name + "_");
        // origin block
        IRBrInst br = new IRBrInst(visit(ctx.getChild(0)), trueBlock, falseBlock);
        br.parentBlock = CurrentBlock;
        CurrentBlock.addInst(br);
        CurrentFunction.addBlock(CurrentBlock);
        CurrentBlock = block;
        // true or false blocks
        trueBlock.addInst(new IRMoveInst(getValue(name), visitTypeValue(ctx.typeValue(0))));
        trueBlock.addInst(new IRBrInst(block));
        falseBlock.addInst(new IRMoveInst(getValue(name), visitTypeValue(ctx.typeValue(1))));
        falseBlock.addInst(new IRBrInst(block));
        CurrentFunction.addBlock(trueBlock);
        CurrentFunction.addBlock(falseBlock);
        infoValue = temp;
        return null;
    }

    @Override
    public Value visitCallInst(LLVMIRParser.CallInstContext ctx) {
        IRFunction callFunc = (IRFunction) visitValue(ctx.value());
        if (Lang.ignoredLLVMFunctions.contains(callFunc.name)) return null;
        ArrayList<Value> argsValue = new ArrayList<>();
        for (var arg : ctx.args().arg())
            argsValue.add(visit(arg));

        IRCallInst inst = new IRCallInst(callFunc, argsValue);

        inst.type = visitType(ctx.type()).type;
        return inst;
    }

    @Override
    public Value visitArg(LLVMIRParser.ArgContext ctx) {

        return visit(ctx.value());

    }

    private Value newValue(String name, IRBaseType type) {// new get
        Value ret = new Value(name, type);
        if (name == "") return ret;
        valueMap.put(name, ret);
        return ret;
    }

    private Value setValue(String name, Value value) {// cover rename
        if (name == "") return null;
        value.name = name;
        valueMap.put(name, value);
        return value;
    }

    private Value getValue(String name) {

        if (Objects.equals(name, "")) return null;
        if (GlobalValueMap.containsKey(name)) return GlobalValueMap.get(name);
        if (!valueMap.containsKey(name)) return new RawOnlyName(name);
        return valueMap.get(name);
    }

    @Override
    public Value visitTerminal(TerminalNode node) {

        if (CurrentFunction != null) {

            return getValue(node.toString());
        }
        return super.visitTerminal(node);
    }

    public ArrayList<RawOnlyName> workList = new ArrayList<>();

    // this is used to handle forward reference
    class RawOnlyName extends Value {

        public RawOnlyName(String name) {
            super(name, null);
            workList.add(this);
        }

    }

    void solveRawOnlyName() {
        try {
            for (RawOnlyName onlyName : workList) {
                var userList = new ArrayList<>(onlyName.users);
                for (User user : userList) {
                    user.resetOperand(user.operands.indexOf(onlyName), getValue(onlyName.name));
                }
            }
            workList.clear();
        } catch (ConcurrentModificationException e) {
            llvmir2riscm.logger.severe("can't find forward reference ");
            e.printStackTrace();

        }
    }
}
