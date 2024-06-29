package kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.inst;

import kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.Value;
import kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.type.NumType;
import kvtodev.mindustack.llvmir2riscm.compiler.share.pass.IRInstPass;

public class IRICmpInst extends IRBaseInst {
    public String op;

    public IRICmpInst(String op, Value lhs, Value rhs) {
        super("ic", new NumType());
        this.op = op;
        this.addOperand(lhs);
        this.addOperand(rhs);
        this.instName = "ic" + '-' + op;
    }

    public Value lhs() {
        return this.getOperand(0);
    }

    public Value rhs() {
        return this.getOperand(1);
    }

    public boolean forBr() {
        return this.users.size() == 1 && this.users.get(0) instanceof IRBrInst;
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
