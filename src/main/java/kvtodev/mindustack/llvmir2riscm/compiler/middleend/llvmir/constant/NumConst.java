package kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.constant;

import kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.type.NumType;

public class NumConst extends BaseConst {
    private final  String binaryData;
    private final  Number numberData;


    public NumConst(String value) {
        super("const", new NumType());
        this.binaryData = value;
        this.numberData=0;
    }
    public NumConst(long value) {
        super("const", new NumType());
        this.binaryData = Long.toBinaryString(value);
        this.numberData=value;
    }
    public NumConst(double value) {
        super("const", new NumType());
        this.binaryData = Double.toHexString(value);
        this.numberData=value;
    }
    @Override
    public boolean isNull() {
        return false;
    }

    public Number get() {
       return numberData;
    }

    @Override
    public String toLiteral() {
       return numberData.toString();
    }

    @Override
    public String toBin() {
        return binaryData;
    }


}
