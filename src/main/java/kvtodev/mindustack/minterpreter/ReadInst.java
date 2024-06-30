package kvtodev.mindustack.minterpreter;

public class ReadInst extends Instruction {
  public ReadInst(int index, Variable data, Variable ptr, int var) {
    this.index = index;
    this.ret = data;
    this.r2 = ptr;
    this.imm=var;
  }

  @Override
  void execute(Minterpreter env) {
    env.memory.read(ret, r2,imm);
    env.counter.value++;
  }
}
