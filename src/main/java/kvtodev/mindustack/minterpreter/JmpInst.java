package kvtodev.mindustack.minterpreter;

public class JmpInst extends Instruction {
    public String label;

    public JmpInst(int index, String label, String op, Variable r1, Variable r2) {
        this.index = index;
        this.label = label;
        this.op = op;
        this.r1 = r1;
        this.r2 = r2;
    }

    @Override
    public String toString() {
        return super.toString() + " ->" + this.label;
    }

    @Override
    void execute(Minterpreter env) {
        boolean jmp = false;
        switch (this.op) {
            case "always" -> {
                jmp = true;
            }
            case "equal" -> {
                jmp = (r1.value == r2.value);
            }
            case "notEqual" -> {
                jmp = (r1.value != r2.value);
            }
            case "lessThan" -> {
                jmp = (r1.value < r2.value);
            }
            case "greaterThan" -> {
                jmp = (r1.value > r2.value);
            }
            case "lessThanEq" -> {
                jmp = (r1.value <= r2.value);
            }
            case "greaterThanEq" -> {
                jmp = (r1.value >= r2.value);
            }
            case "and" -> {
                jmp = (r1.value * r2.value != 0);
            }
            case "or" -> {
                jmp = (!(r1.value + r2.value == 0));
            }
            case "xor" -> {
                jmp = (r1.value + r2.value == 1);
            }
            default -> {
                throw new RuntimeException("invalid condition:" + op);
            }
        }
        if (jmp) env.counter.value = env.labels.get(this.label).index;
        else env.counter.value += 1;
    }
}
