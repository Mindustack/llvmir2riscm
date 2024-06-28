package kvtodev.mindustack.minterpreter;

public class WriteInst extends Instruction {
  public WriteInst(int index, Variable ret, Variable memory, Variable ptr, int i) {
    this.index = index;
    this.ret = ret;
    this.r1 = memory;
    this.r2 = ptr;
    this.imm=i;
  }

  @Override
  void execute(Minterpreter env) {
    env.memFctr.getMem(r1).write(ret, r2);
    env.counter.value++;
  }
}
