package kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.inst;

import kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.Value;
import kvtodev.mindustack.llvmir2riscm.compiler.share.pass.IRInstPass;

public class IRAllocaInst extends IRBaseInst {

    // PS: allocaType is the *pointedType* of this Inst
    public IRAllocaInst(Value allocaValue) {
        super("alloca", allocaValue.type);
        this.addOperand(allocaValue);
        instName= "alloca";
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
