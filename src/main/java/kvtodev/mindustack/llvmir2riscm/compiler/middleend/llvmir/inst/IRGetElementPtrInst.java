package kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.inst;

import kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.Value;
import kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.type.IRBaseType;
import kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.type.PointerType;
import kvtodev.mindustack.llvmir2riscm.share.pass.IRInstPass;

import java.util.ArrayList;

public class IRGetElementPtrInst extends IRBaseInst {



 

    public IRGetElementPtrInst(Value headPointer, IRBaseType yieldType, ArrayList<Value> indices) {
        super("gep\t", yieldType);
        assert headPointer.type instanceof PointerType;
        this.addOperand(headPointer);
        for (Value index : indices) this.addOperand(index);
        this.instName= "gep\t";
    }

    public int indicesNum() {
        return this.operands.size() - 1;
    }

    public Value getIndex(int pos) {
        return this.getOperand(pos + 1);
    }

    public Value headPointer() {
        return this.getOperand(0);
    }


    public Value ptrMoveIndex() {
        return this.getIndex(0);
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
