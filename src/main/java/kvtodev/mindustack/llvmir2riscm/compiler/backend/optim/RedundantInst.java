package kvtodev.mindustack.llvmir2riscm.compiler.backend.optim;

import kvtodev.mindustack.llvmir2riscm.compiler.backend.riscm.hierarchy.AsmBlock;
import kvtodev.mindustack.llvmir2riscm.compiler.backend.riscm.hierarchy.AsmFunction;
import kvtodev.mindustack.llvmir2riscm.compiler.backend.riscm.inst.*;
import kvtodev.mindustack.llvmir2riscm.compiler.backend.riscm.operand.Immediate;
import kvtodev.mindustack.llvmir2riscm.compiler.backend.riscm.operand.PhysicalReg;
import kvtodev.mindustack.llvmir2riscm.share.pass.AsmFuncPass;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

public class RedundantInst implements AsmFuncPass {

    private void foldMoves(AsmFunction function) {
        for (AsmBlock block : function.blocks) {
            HashSet<AsmBaseInst> toRemoveSet = new HashSet<>();

            for (int i = 0; i < block.instructions.size() - 1; i++)
                // move twice
                if (block.instructions.get(i) instanceof AsmMoveInst && block.instructions.get(i + 1) instanceof AsmMoveInst &&
                        block.instructions.get(i).rd.color == block.instructions.get(i + 1).rd.color) {
                    toRemoveSet.add(block.instructions.get(i));
                }

            block.instructions.removeAll(toRemoveSet);
        }
    }

    private boolean isValidAddi(AsmBaseInst inst) {
        return inst instanceof AsmALUInst && Objects.equals(((AsmALUInst) inst).op, "add") && inst.imm != null && inst.rd.color == inst.rs1.color;
    }

    private void foldAddi(AsmFunction function) {
        for (AsmBlock block : function.blocks) {
            HashSet<AsmBaseInst> toRemoveSet = new HashSet<>();

            for (int i = 0; i < block.instructions.size() - 1; i++)
                if (isValidAddi(block.instructions.get(i)) &&
                        isValidAddi(block.instructions.get(i + 1)) &&
                        block.instructions.get(i).rd.color == block.instructions.get(i + 1).rd.color) {
                    toRemoveSet.add(block.instructions.get(i));
                    block.instructions.get(i + 1).imm = new Immediate(block.instructions.get(i).imm.getValue().intValue() + block.instructions.get(i + 1).imm.getValue().intValue());
                }

            block.instructions.removeAll(toRemoveSet);
        }
    }

    private void notUsedDef(AsmFunction function) {
        for (AsmBlock block : function.blocks) {
            HashMap<PhysicalReg, AsmBaseInst> defButNotUsed = new HashMap<>();
            HashSet<AsmBaseInst> toRemoveSet = new HashSet<>();

            for (AsmBaseInst inst : block.instructions) {
                if (inst.rs1 != null && inst.rs1.color != null)
                    defButNotUsed.remove(inst.rs1.color);
                if (inst.rs2 != null && inst.rs2.color != null)
                    defButNotUsed.remove(inst.rs2.color);

                if (inst.rd != null && inst.rd.color != null) {
                    if (defButNotUsed.containsKey(inst.rd.color))
                        toRemoveSet.add(defButNotUsed.get(inst.rd.color));

                    defButNotUsed.put(inst.rd.color, inst);
                }

                if (inst instanceof AsmCallInst || inst instanceof AsmBrInst)
                    defButNotUsed.clear();
            }

            block.instructions.removeAll(toRemoveSet);
        }
    }

    @Override
    public void runOnFunc(AsmFunction function) {
        foldMoves(function);
        foldAddi(function);
        notUsedDef(function);
    }
}
