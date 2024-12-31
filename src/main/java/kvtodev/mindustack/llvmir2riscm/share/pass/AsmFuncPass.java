package kvtodev.mindustack.llvmir2riscm.share.pass;

import kvtodev.mindustack.llvmir2riscm.compiler.backend.riscm.hierarchy.AsmFunction;

public interface AsmFuncPass extends Pass {
    void runOnFunc(AsmFunction function);
}
