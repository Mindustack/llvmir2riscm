package kvtodev.mindustack.llvmir2riscm.share.pass;

import kvtodev.mindustack.llvmir2riscm.compiler.backend.riscm.hierarchy.AsmModule;

public interface AsmModulePass extends Pass {
    void runOnModule(AsmModule module);
}
