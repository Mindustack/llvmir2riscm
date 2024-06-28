package kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.constant;

import kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.type.ArrayType;
import kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.type.IRBaseType;

import java.util.ArrayList;
import java.util.List;

public class ArrayConst extends BaseConst {
    public List<BaseConst> constData;

    public ArrayConst(IRBaseType type, int length) {
        super("const", new ArrayType(type, length));
        constData = new ArrayList<>();
    }

    @Override
    public boolean isNull() {
        return constData.isEmpty();
    }

    @Override
    public String toString() {
        return constData.toString();
    }

    @Override
    public int size() {
        return this.type.size();
    }
}
