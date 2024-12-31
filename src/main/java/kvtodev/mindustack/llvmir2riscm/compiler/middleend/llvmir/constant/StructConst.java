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

    @Override
    public String toLiteral() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('{');
        for (BaseConst constDatum : this.constData) {
            stringBuilder.append(constDatum.toLiteral()).append(',');
        }
        return stringBuilder.append('}').toString();
    }

    @Override
    public String toBin() {
         StringBuilder stringBuilder=new StringBuilder();
        for (BaseConst constDatum : this.constData) {
            stringBuilder.append(constDatum.toBin());
        }
        return stringBuilder.toString();
    }

    public int size() {
        return this.type.size();
    }


}
