package kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.type;
import kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.Value;

import java.util.ArrayList;
import java.util.List;

public class ArrayType extends IRBaseType {
    public IRBaseType elementType;
    public int length;

    public ArrayType(IRBaseType elementType, int length) {
        this.elementType = elementType;
        this.length = length;
    }

    @Override
    public boolean match(IRBaseType other) {
        return false;
    }

    @Override
    public int size() {
        return elementType.size() * length;
    }

    @Override
    public String toString() {
        return "[" + length + " x " + elementType + "]";
    }

    @Override
    public List<String> toReg(List<String> list, String name) {
      for (int i = 0; i < this.length; i++) {
        this.elementType.toReg(list,name+"_"+i);
      }
      return list;
    }
}
