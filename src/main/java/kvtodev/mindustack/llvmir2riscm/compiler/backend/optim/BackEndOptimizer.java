package kvtodev.mindustack.llvmir2riscm.compiler.backend.optim;

import kvtodev.mindustack.llvmir2riscm.compiler.backend.rvasm.hierarchy.AsmFunction;
import kvtodev.mindustack.llvmir2riscm.compiler.backend.rvasm.hierarchy.AsmModule;
import kvtodev.mindustack.llvmir2riscm.compiler.share.pass.AsmModulePass;

public class BackEndOptimizer implements AsmModulePass {
    @Override
    public void runOnModule(AsmModule module) {
        for (AsmFunction function : module.functions) {

            new CoalesceMoves().runOnFunc(function);
            new ZeroInstPeephole().runOnFunc(function);
            new BlockMerge().runOnFunc(function);
            new ReorderBlock().runOnFunc(function);
            new RedundantInst().runOnFunc(function);
            new LoadStorePeephole().runOnFunc(function);
        }
    }
}