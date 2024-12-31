package kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.type;

import java.util.List;

public class LabelType extends IRBaseType {
    @Override
    public boolean match(IRBaseType other) {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override

    public List<String> toReg(List<String> list, String name) {
        throw new RuntimeException("unsupported operation");
    }

    @Override
    public String toString() {
        return "label";
    }
}
