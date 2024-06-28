package kvtodev.mindustack.llvmir2riscm.compiler.backend.rvasm.inst;

import kvtodev.mindustack.llvmir2riscm.compiler.backend.rvasm.hierarchy.AsmBlock;
import kvtodev.mindustack.llvmir2riscm.compiler.backend.rvasm.operand.Immediate;
import kvtodev.mindustack.llvmir2riscm.compiler.backend.rvasm.operand.Register;

import java.util.Objects;

public class AsmLoadInst extends AsmBaseInst {

    public AsmLoadInst(Register rd, Register rs1, Immediate imm, AsmBlock parentBlock) {
        super(rd, Objects.requireNonNull(rs1), null, imm, parentBlock);
    }

    @Override
    public AsmBaseInst copy() {
        return new AsmLoadInst(rd, rs1, imm, null);
    }

    @Override
    public String format() {
        return String.format("read %s %s %s\t", rd, rs1, imm);


    }
}
