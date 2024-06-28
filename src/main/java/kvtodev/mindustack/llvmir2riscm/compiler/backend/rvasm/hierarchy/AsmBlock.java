package kvtodev.mindustack.llvmir2riscm.compiler.backend.rvasm.hierarchy;

import kvtodev.mindustack.llvmir2riscm.compiler.backend.rvasm.inst.AsmBaseInst;
import kvtodev.mindustack.llvmir2riscm.compiler.backend.rvasm.operand.BaseOperand;
import kvtodev.mindustack.llvmir2riscm.compiler.backend.rvasm.operand.Register;

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
