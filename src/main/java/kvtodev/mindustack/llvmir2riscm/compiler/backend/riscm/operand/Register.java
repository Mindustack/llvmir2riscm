package kvtodev.mindustack.llvmir2riscm.compiler.backend.riscm.operand;

import kvtodev.mindustack.llvmir2riscm.compiler.backend.regalloc.InterferenceGraph;

public abstract class Register extends BaseOperand {

    // assigned in RegisterAllocator
    public PhysicalReg color;
    // location in stack, only spill registers have (graphColor spill & function call spill)
    public RawStackOffset stackOffset;
    // info in InterferenceGraph, used in RegisterAllocator
    public InterferenceGraph.Node node = new InterferenceGraph.Node();

    public Register(String identifier) {
        super(identifier);
    }

    @Override
    public String toString() {
        if (color == null) {
            return identifier;
            // throw new InternalError(this);
        }
        return color.identifier;
        // return identifier;
    }
}
