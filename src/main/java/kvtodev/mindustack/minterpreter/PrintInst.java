package kvtodev.mindustack.minterpreter;

public class PrintInst extends Instruction {
    public PrintInst(int index, Variable ret) {
        this.index = index;
        this.ret = ret;
    }

    @Override
    void execute(Minterpreter env) {
        System.out.println(this.ret.value);

        env.counter.value++;
    }
}
