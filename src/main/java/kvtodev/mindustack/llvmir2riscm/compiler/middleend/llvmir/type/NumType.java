package kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.type;

import java.util.List;

public class NumType extends IRBaseType {



    @Override
    public boolean match(IRBaseType other) {
        return other instanceof NumType ;
    }

    @Override
    public int size() {
        return 1;
    }

    @Override
    public String toString() {
        return "num"
                ;
    }

    @Override
    public List<String> toReg(List<String> list, String name) {
      list.add(name);
      return list;
    }
}
