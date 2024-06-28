package kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.type;

import java.util.List;

public class VoidType extends IRBaseType {
    @Override
    public boolean match(IRBaseType other) {
        return other instanceof VoidType;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public String toString() {
        return "void";
    }

    @Override
    public List<String> toReg(List<String> list, String parrentName) {
      return list;
    }
}
