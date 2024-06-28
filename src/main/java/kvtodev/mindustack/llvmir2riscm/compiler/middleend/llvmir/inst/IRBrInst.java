package kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.inst;

import kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.Value;
import kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.hierarchy.IRBlock;
import kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.type.VoidType;
import kvtodev.mindustack.llvmir2riscm.compiler.share.pass.IRInstPass;

public class IRBrInst extends IRBaseInst {
    public IRBrInst(IRBlock destBlock) {
        super("br\t", new VoidType());
        this.addOperand(destBlock);

        this.instName= "br\t";
    }

    public IRBrInst(Value condition, IRBlock ifTrueBlock, IRBlock ifFalseBlock) {
        super("br\t", new VoidType());
        this.addOperand(condition);
        this.addOperand(ifTrueBlock);
        this.addOperand(ifFalseBlock);
        this.instName= "br\t";
    }

    public boolean isJump() {
        return this.operands.size() == 1;
    }

    public IRBlock destBlock() {
        assert isJump();
        return (IRBlock) this.getOperand(0);
    }

    public Value condition() {
        assert !isJump();
        return this.getOperand(0);
    }

    public IRBlock ifTrueBlock() {
        return (IRBlock) this.getOperand(1);
    }

    public IRBlock ifFalseBlock() {
        return (IRBlock) this.getOperand(2);
    }

    public void resetDestBlock(IRBlock newBlock) {
        this.resetOperand(0, newBlock);
    }

    public void resetIfTrueBlock(IRBlock newBlock) {
        this.resetOperand(1, newBlock);
    }

    public void resetIfFalseBlock(IRBlock newBlock) {
        this.resetOperand(2, newBlock);
    }

    @Override
    public boolean isTerminator() {
        return true;
    }


    @Override
    public IRBaseInst copy() {
        if (isJump()) return new IRBrInst(destBlock());
        return new IRBrInst(condition(), ifTrueBlock(), ifFalseBlock());
    }

    @Override
    public void accept(IRInstPass visitor) {
        visitor.visit(this);
    }
}
