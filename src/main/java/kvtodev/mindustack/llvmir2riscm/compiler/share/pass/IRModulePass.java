package kvtodev.mindustack.llvmir2riscm.compiler.share.pass;

import kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.hierarchy.IRModule;

public interface IRModulePass extends Pass {
    void runOnModule(IRModule module);
}
