package kvtodev.mindustack.llvmir2riscm.compiler.backend.optim;

import kvtodev.mindustack.llvmir2riscm.compiler.backend.rvasm.hierarchy.AsmBlock;
import kvtodev.mindustack.llvmir2riscm.compiler.backend.rvasm.hierarchy.AsmFunction;
import kvtodev.mindustack.llvmir2riscm.compiler.backend.rvasm.inst.AsmMoveInst;
import kvtodev.mindustack.llvmir2riscm.compiler.share.pass.AsmFuncPass;

public class CoalesceMoves implements AsmFuncPass {
    @Override
    public void runOnFunc(AsmFunction function) {
        for (AsmBlock block : function.blocks) {
            block.instructions.removeIf(inst -> inst instanceof AsmMoveInst && inst.rd.color == inst.rs1.color);
        }
    }
}
