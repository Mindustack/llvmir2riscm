package kvtodev.mindustack.llvmir2riscm.compiler.share.pass;

import kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.hierarchy.IRFunction;

public interface IRFuncPass extends Pass {
    void runOnFunc(IRFunction function);
}
