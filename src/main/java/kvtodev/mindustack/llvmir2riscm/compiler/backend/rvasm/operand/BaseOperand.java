package kvtodev.mindustack.llvmir2riscm.compiler.backend.rvasm.operand;

public abstract class BaseOperand {
    public String identifier;

    public BaseOperand(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public String toString() {
        return identifier;
    }
}
