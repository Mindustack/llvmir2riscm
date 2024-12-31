package kvtodev.mindustack.llvmir2riscm.compiler.backend.riscm.hierarchy;

import kvtodev.mindustack.llvmir2riscm.compiler.backend.riscm.inst.AsmBaseInst;
import kvtodev.mindustack.llvmir2riscm.compiler.backend.riscm.operand.BaseOperand;
import kvtodev.mindustack.llvmir2riscm.compiler.backend.riscm.operand.Register;
import kvtodev.mindustack.llvmir2riscm.share.Pointer;

import java.util.*;

public class AsmBlock extends BaseOperand {

    public LinkedList<AsmBaseInst> instructions = new LinkedList<>();

    // control flow graph
    public List<AsmBlock> prevs = new ArrayList<>(), nexts = new ArrayList<>();

    // liveIn and LiveOut in block, assigned in LivenessAnalyzer
    public Set<Register> liveIn = new HashSet<>(), liveOut = new HashSet<>();

    // from IRBlock
    public int loopDepth = 0;
    public Map<Number, Register> recordLi = new HashMap<>();
   public Pointer<Long> asmAddress=new Pointer<>();

    public AsmBlock(String label) {
        super(label);
    }

    public void addInst(AsmBaseInst inst) {
        instructions.add(inst);
    }

    public AsmBaseInst terminator() {
        if (instructions.isEmpty()) throw new InternalError("empty AsmBLock! no terminator! " + this.identifier);
        return instructions.getLast();
    }
}
