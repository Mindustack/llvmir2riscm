package kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.hierarchy;

import kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.Value;
import kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.inst.IRBaseInst;
import kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.inst.IRBrInst;
import kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.type.LabelType;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class IRBlock extends Value {
    public LinkedList<IRBaseInst> instructions = new LinkedList<>();

    public IRFunction parentFunction;

    public List<IRBlock> prevs = new ArrayList<>();

    public List<IRBlock> nexts = new ArrayList<>();

    public int loopDepth;

    public int visited = 0;

    public IRBlock(String label) {
        super(label, new LabelType());
    }

    public void addInst(IRBaseInst inst) {
        inst.parentBlock = this;
        instructions.addLast(inst);
    }

    public IRBaseInst terminator() {
        if (instructions.isEmpty()) throw new InternalError("empty IRBLock! no terminator! " + this.name);
        return instructions.getLast();
    }

    public void redirect(IRBlock oldDest, IRBlock newDest) {
        IRBaseInst inst = this.instructions.getLast();
        if (inst instanceof IRBrInst) {
            if (((IRBrInst) inst).isJump()) {
                ((IRBrInst) inst).resetDestBlock(newDest);
            } else {
                if (((IRBrInst) inst).ifTrueBlock() == oldDest) ((IRBrInst) inst).resetIfTrueBlock(newDest);
                if (((IRBrInst) inst).ifFalseBlock() == oldDest) ((IRBrInst) inst).resetIfFalseBlock(newDest);
            }
        } else {
            throw new InternalError("redirecting ret block");
        }
    }

    @Override
    public String toString() {
        return "IRBlock" + ":" + name ;
    }
}
