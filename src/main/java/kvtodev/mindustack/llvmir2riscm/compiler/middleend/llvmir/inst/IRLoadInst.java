package kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.inst;

import kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.Value;
import kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.type.PointerType;
import kvtodev.mindustack.llvmir2riscm.compiler.share.pass.IRInstPass;

public class IRLoadInst extends IRBaseInst {
    public IRLoadInst(Value loadPtr) {
        super(loadPtr.name, ((PointerType) loadPtr.type).pointedType);
        this.addOperand(loadPtr);
        this.instName= "load";
    }

    public Value loadPtr() {
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
