package kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.constant;

import kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.type.NumType;

public class NumConst extends BaseConst {
    public Number constData;

    public NumConst() {
        super("const", new NumType());
        this.constData = 0;
    }


    public NumConst(Number constData) {
        super("const", new NumType());
        this.constData = constData;
    }


    public NumConst(boolean bool) {

        super("const", new NumType());
        this.constData = bool ? 1 : 0;

    }


    @Override
    public boolean isNull() {
        return false;
    }

    @Override
    public String toString() {
        return String.valueOf(constData);
    }
}
