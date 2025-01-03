package kvtodev.mindustack.llvmir2riscm.compiler.backend.optim;

import kvtodev.mindustack.llvmir2riscm.compiler.backend.riscm.hierarchy.AsmBlock;
import kvtodev.mindustack.llvmir2riscm.compiler.backend.riscm.hierarchy.AsmFunction;
import kvtodev.mindustack.llvmir2riscm.compiler.backend.riscm.inst.AsmJmpInst;
import kvtodev.mindustack.llvmir2riscm.share.pass.AsmFuncPass;

import java.util.ArrayList;

public class ReorderBlock implements AsmFuncPass {

    private final ArrayList<AsmBlock> reorderedList = new ArrayList<>();

    private void reorderJump(AsmBlock block) {
        if (reorderedList.contains(block)) return;
        reorderedList.add(block);
        var terminator = block.terminator();
        if (terminator instanceof AsmJmpInst)
            reorderJump(((AsmJmpInst) terminator).dest);
    }

    @Override
    public void runOnFunc(AsmFunction function) {

        // reorder
        for (AsmBlock block : function.blocks) {
            if (reorderedList.contains(block)) continue;
            reorderJump(block);
        }

        function.blocks.clear();
        function.blocks.addAll(reorderedList);

        for (int i = 0; i < function.blocks.size() - 1; i++) {
            var terminator = function.blocks.get(i).terminator();
            if (terminator instanceof AsmJmpInst && ((AsmJmpInst) terminator).dest == function.blocks.get(i + 1))
                function.blocks.get(i).instructions.removeLast();
        }
    }
}
