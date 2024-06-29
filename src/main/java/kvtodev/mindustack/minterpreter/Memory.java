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

  public void read(Variable ret, Variable index) {
    ret.value = mem.get((int) index.asInteger());
  }

  public void write(Variable source, Variable index) {
    mem.set((int) index.asInteger(), source.value);
  }
}
