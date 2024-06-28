package kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.inst;

import kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.Value;
import kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.type.IRBaseType;
import kvtodev.mindustack.llvmir2riscm.compiler.share.pass.IRInstPass;

public class IRCastInst extends IRBaseInst {

    public IRCastInst(Value fromValue, IRBaseType targetType) {
        super("cast", targetType);
        this.addOperand(fromValue);
        this.instName= "cast";
    }

    public Value fromValue() {
        return this.getOperand(0);
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
