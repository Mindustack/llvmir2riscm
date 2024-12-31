package kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.type;


public class PointerType extends NumType {
    public IRBaseType pointedType;

    public PointerType(IRBaseType pointed) {
        this.pointedType = pointed;
    }

    @Override
    public boolean match(IRBaseType other) {
      //TODO
       return false;
    }

    @Override
    public String toString() {
        return pointedType + "*";
    }
}
