package kvtodev.mindustack.llvmir2riscm.compiler.backend.rvasm.inst;

import kvtodev.mindustack.llvmir2riscm.compiler.backend.rvasm.hierarchy.AsmBlock;
import kvtodev.mindustack.llvmir2riscm.compiler.backend.rvasm.operand.Register;

public class AsmMoveInst extends AsmBaseInst {
    public AsmMoveInst(Register rd, Register rs1, AsmBlock parentBlock) {
        super(rd, rs1, null, null, parentBlock);
    }

    @Override
    public AsmBaseInst copy() {
        return new AsmMoveInst(rd, rs1, null);
    }

    @Override
    public String format() {
        return String.format("set %s %s", rd, rs1);
    }
}