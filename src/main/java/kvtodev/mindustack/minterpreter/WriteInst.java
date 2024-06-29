package kvtodev.mindustack.minterpreter;

public class WriteInst extends Instruction {
  public WriteInst(int index, Variable ret, Variable ptr, int i) {
    this.index = index;
    this.ret = ret;
    this.r2 = ptr;
    this.imm=i;
  }

  @Override
  void execute(Minterpreter env) {
    env.memory.write(ret, r2);
    env.counter.value++;
  }
}
