package kvtodev.mindustack.llvmir2riscm.compiler.share.pass;

import kvtodev.mindustack.llvmir2riscm.compiler.backend.rvasm.hierarchy.AsmBlock;

public interface AsmBlockPass extends Pass {
    void runOnBlock(AsmBlock block);
}
