package kvtodev.mindustack.minterpreter;

public class ReadInst extends Instruction {

    Variable page;

    public ReadInst(int index, Variable data, Variable page, Variable ptr, int var) {
        this.index = index;
        this.ret = data;
        this.r2 = ptr;
        this.imm = var;
        this.page = page;
    }

    @Override
    void execute(Minterpreter env) {
        env.memory.read(ret,page, r2, imm);
        env.counter.value++;
    }
}
