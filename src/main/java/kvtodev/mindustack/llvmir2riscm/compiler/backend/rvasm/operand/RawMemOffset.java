package kvtodev.mindustack.llvmir2riscm.compiler.backend.rvasm.operand;

import kvtodev.mindustack.llvmir2riscm.compiler.share.pass.Pointer;

public class RawMemOffset extends Immediate {
    Pointer<Integer> ptr;
    public RawMemOffset(int offset) {
        super(offset);
    }public RawMemOffset(Pointer<Integer> offset) {
        super(0);
        this.ptr=offset;
    }

    @Override
    public Number getValue() {
        if(ptr!=null)return ptr.pointed;
        return super.getValue();
    }

    @Override
    public String toString() {
        return String.valueOf(getValue());
    }
}
