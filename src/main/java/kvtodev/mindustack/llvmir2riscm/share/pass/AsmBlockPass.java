package kvtodev.mindustack.llvmir2riscm.share.pass;

import kvtodev.mindustack.llvmir2riscm.compiler.backend.riscm.hierarchy.AsmBlock;

public interface AsmBlockPass extends Pass {
    void runOnBlock(AsmBlock block);
}
