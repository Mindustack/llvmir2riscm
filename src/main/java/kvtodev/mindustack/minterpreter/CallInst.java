package kvtodev.mindustack.minterpreter;

public class CallInst extends Instruction {

    public String label;

    public CallInst(int index, String label) {
        this.index = index;
        this.label = label;


    }

    @Override
    public String toString() {
        return this.index + ": " + this.getClass().getSimpleName() + ' ' + this.label

        ;
    }

    @Override
    void execute(Minterpreter env) {
        env.varFctr.getVar("ra").value = env.counter.value + 1;
        env.counter.value = env.labels.get(this.label).index;
    }
}
