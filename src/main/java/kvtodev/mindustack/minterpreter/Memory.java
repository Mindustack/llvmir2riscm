package kvtodev.mindustack.minterpreter;

import kvtodev.mindustack.llvmir2riscm.compiler.share.Lang;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Memory {
  List<Double> mem;

  protected Memory() {
    mem = new ArrayList<Double>(Collections.nCopies(Lang.defaultMemory, 0.0));
  }

  public void read(Variable ret, Variable index,int imm) {
    ret.value = mem.get((int) index.asInteger()+imm);
  }

  public void write(Variable source, Variable index,int imm) {
    mem.set((int) index.asInteger()+imm, source.value);
  }
}
