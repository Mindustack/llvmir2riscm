package kvtodev.mindustack.llvmir2riscm.compiler.backend.optim;

import kvtodev.mindustack.llvmir2riscm.compiler.backend.riscm.hierarchy.AsmBlock;
import kvtodev.mindustack.llvmir2riscm.compiler.backend.riscm.hierarchy.AsmFunction;
import kvtodev.mindustack.llvmir2riscm.compiler.backend.riscm.inst.*;
import kvtodev.mindustack.llvmir2riscm.compiler.backend.riscm.operand.PhysicalReg;
import kvtodev.mindustack.llvmir2riscm.share.pass.AsmFuncPass;

import java.util.HashMap;
import java.util.HashSet;

/**
 * This Peephole optimization intends to remove codes like:
 * sw  t0, addr
 * ... (load not be modified)
 * lw  t0, addr
 */

public class LoadStorePeephole implements AsmFuncPass {

    @Override
    public void runOnFunc(AsmFunction function) {
        for (AsmBlock block : function.blocks) {
            HashSet<AsmBaseInst> toRemove = new HashSet<>();
            HashMap<AsmBaseInst, AsmBaseInst> toReplace = new HashMap<>();

            AsmStoreInst lastStore = null;
            HashSet<PhysicalReg> dirty = new HashSet<>();

            for (AsmBaseInst inst : block.instructions) {
                if (inst instanceof AsmStoreInst) {
                    assert inst.rs2 != null;
                    assert inst.rs2.color != null;
                    // sw rs2, offset(rs1), next lw of rs2 can be remove
                    // is it possible to do alias analysis in this stage?
                    dirty.clear();
                    lastStore = (AsmStoreInst) inst;
                } else if (inst instanceof AsmLoadInst) {
                    // lw rd, offset(rs1)
                    // remove the load
                    if (lastStore != null) {
                        if (inst.rs1.color == lastStore.rs1.color &&
                                inst.imm.getValue() == lastStore.imm.getValue() &&
                                !dirty.contains(lastStore.rs1.color) &&
                                !dirty.contains(inst.rd.color))
                            if (lastStore.rs2.color == inst.rd.color)
                                toRemove.add(inst);
                            else if (!dirty.contains(lastStore.rs2.color))
                                toReplace.put(inst, new AsmMoveInst(inst.rd.color, lastStore.rs2.color, null));

                    }
                }
                // side effect
                else if (inst instanceof AsmBrInst || inst instanceof AsmCallInst) {
                    lastStore = null;
                }

                // def
                if (inst.rd != null && inst.rd.color != null) {
                    dirty.add(inst.rd.color);
                }
            }
            toRemove.forEach(inst -> block.instructions.remove(inst));
            var it = block.instructions.listIterator();

            while (it.hasNext()) {
                var inst = it.next();
                if (toReplace.containsKey(inst)) {
                    var replace = toReplace.get(inst);
                    it.set(replace);
                }
            }
        }
    }
}
