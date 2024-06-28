package kvtodev.mindustack.llvmir2riscm.compiler.share.pass;

import kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.hierarchy.IRBlock;

public interface IRBlockPass extends Pass {
    void runOnBlock(IRBlock block);
}
