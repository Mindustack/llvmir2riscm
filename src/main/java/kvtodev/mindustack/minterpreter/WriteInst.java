package kvtodev.mindustack.minterpreter;

public class WriteInst extends Instruction {
  Variable page;

  public WriteInst(int index, Variable ret, Variable page, Variable ptr, int i) {
    this.index = index;
    this.ret = ret;
    this.r2 = ptr;
    this.imm=i;
    this.page=page;
  }

  @Override
  void execute(Minterpreter env) {
    env.memory.write(ret, page,r2,imm);
    env.counter.value++;
  }
}
