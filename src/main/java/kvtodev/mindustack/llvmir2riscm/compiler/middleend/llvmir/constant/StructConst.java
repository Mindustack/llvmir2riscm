package kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.constant;

import kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.type.IRBaseType;
import kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.type.StructType;

import java.util.ArrayList;
import java.util.List;


public class StructConst extends BaseConst {
    public List<BaseConst> constData;

    public StructConst(IRBaseType type) {
        // constData=new ArrayList<>();
        super("const", new StructType());


        constData = new ArrayList<>();
    }

    @Override
    public boolean isNull() {
        return constData.isEmpty();
    }

    public int size() {
        return this.type.size();
    }

    @Override
    public String toString() {
        return constData.toString();
    }
}
