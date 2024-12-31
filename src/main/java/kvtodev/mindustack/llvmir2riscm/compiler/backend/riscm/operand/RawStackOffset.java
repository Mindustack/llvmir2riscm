package kvtodev.mindustack.llvmir2riscm.compiler.backend.riscm.operand;

// RawStackOffset will be turned to Immediate with correct position
// throw an error if there is RawStackOffset not be eliminated

import kvtodev.mindustack.llvmir2riscm.share.Pointer;

public class RawStackOffset extends Immediate {


    private Pointer<Integer> ptr;
    public RawStackOffset(int offset) {
        super(offset);
    }
 public RawStackOffset(Pointer<Integer> offset) {
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


