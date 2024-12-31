package kvtodev.mindustack.llvmir2riscm.compiler.backend.riscm.operand;

public class Immediate extends BaseOperand {
    public Number getValue() {
        return value;
    }

    public void setValue(Number value) {
        this.value = value;
    }

    private Number value;

    public Immediate(Number value) {
        super(String.valueOf(value));
        this.setValue(value);
    }

//    // for Global Addr
//    protected Immediate(String identifier) {
//        super(identifier);
//        this.setValue(0);
//    }

}
