package kvtodev.mindustack.llvmir2riscm.share.pass;

import kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.inst.*;

public interface IRInstPass extends Pass {
    void visit(IRAllocaInst inst);

    void visit(IRBinaryInst inst);

    void visit(IRBrInst inst);

    void visit(IRCallInst inst);

    void visit(IRGetElementPtrInst inst);

    void visit(IRICmpInst inst);

    void visit(IRLoadInst inst);

    void visit(IRRetInst inst);

    void visit(IRStoreInst inst);

    void visit(IRCastInst inst);

    void visit(IRMoveInst inst);
}
