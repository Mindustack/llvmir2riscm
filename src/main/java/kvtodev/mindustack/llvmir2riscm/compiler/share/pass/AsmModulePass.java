package kvtodev.mindustack.llvmir2riscm.compiler.share.pass;

import kvtodev.mindustack.llvmir2riscm.compiler.backend.rvasm.hierarchy.AsmModule;

public interface AsmModulePass extends Pass {
    void runOnModule(AsmModule module);
}
