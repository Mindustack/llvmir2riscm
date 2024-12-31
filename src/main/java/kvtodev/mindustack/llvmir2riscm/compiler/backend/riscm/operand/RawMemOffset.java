package kvtodev.mindustack.llvmir2riscm.compiler.backend.riscm.operand;

public class RawMemOffset extends Immediate {

    public int size ;
    public RawMemOffset(int size) {
        super(0);
        this.size=size;
    }
    @Override
    public String toString() {
        return String.valueOf(getValue());
    }
}
