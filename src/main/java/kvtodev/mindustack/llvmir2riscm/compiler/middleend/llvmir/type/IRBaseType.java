package kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.type;

import java.util.List;

public abstract class IRBaseType {

    abstract public boolean match(IRBaseType other);

    abstract public int size(); // byte

    abstract public List<String> toReg(List<String> list,String name);
    @Override
    abstract public String toString();

}
