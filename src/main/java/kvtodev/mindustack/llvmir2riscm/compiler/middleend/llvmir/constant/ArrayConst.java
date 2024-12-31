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
    public String toLiteral() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('[');
        for (BaseConst constDatum : this.constData) {
            stringBuilder.append(constDatum.toLiteral()).append(',');
        }
        return stringBuilder.append(']').toString();
    }

    @Override
    public String toBin() {
        StringBuilder stringBuilder=new StringBuilder();
        for (BaseConst constDatum : this.constData) {
            stringBuilder.append(constDatum.toBin());
            if(constDatum instanceof NumConst){
                stringBuilder.append("\n");
            }
        }
        return stringBuilder.toString();
    }


    @Override
    public int size() {
        return this.type.size();
    }
}
