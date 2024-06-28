package kvtodev.mindustack.llvmir2riscm.compiler.share.pass;

import kvtodev.mindustack.llvmir2riscm.compiler.backend.rvasm.hierarchy.AsmFunction;

public interface AsmFuncPass extends Pass {
    void runOnFunc(AsmFunction function);
}
