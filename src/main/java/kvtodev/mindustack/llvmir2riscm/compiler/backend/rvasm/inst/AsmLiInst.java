package kvtodev.mindustack.llvmir2riscm.compiler.backend.rvasm.inst;

import kvtodev.mindustack.llvmir2riscm.compiler.backend.rvasm.hierarchy.AsmBlock;
import kvtodev.mindustack.llvmir2riscm.compiler.backend.rvasm.operand.Immediate;
import kvtodev.mindustack.llvmir2riscm.compiler.backend.rvasm.operand.Register;

public class AsmLiInst extends AsmBaseInst {
    public AsmLiInst(Register rd, Immediate imm, AsmBlock parentBlock) {
        super(rd, null, null, imm, parentBlock);
    }

    @Override
    public AsmBaseInst copy() {
        return new AsmLiInst(rd, imm, null);
    }

    @Override
    public String format() {
        return String.format("set %s %s", rd, imm);
    }//set a number
}
