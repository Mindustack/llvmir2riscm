package kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.inst;

import kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.Value;
import kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.type.IRBaseType;
import kvtodev.mindustack.llvmir2riscm.share.pass.IRInstPass;

public class IRBinaryInst extends IRBaseInst {
    public String op;

    public IRBinaryInst(String op, IRBaseType retType, Value lhs, Value rhs) {
        super(op, retType);
        this.op = op;
        this.addOperand(lhs);
        this.addOperand(rhs);
         instName= "op-"+op;
    }

    public Value lhs() {
        return this.getOperand(0);
    }

    public Value rhs() {
        return this.getOperand(1);
    }

    @Override
    public boolean isTerminator() {
        return false;
    }



    @Override
    public IRBaseInst copy() {
        return null;
    }

    @Override
    public void accept(IRInstPass visitor) {
        visitor.visit(this);
    }
}
