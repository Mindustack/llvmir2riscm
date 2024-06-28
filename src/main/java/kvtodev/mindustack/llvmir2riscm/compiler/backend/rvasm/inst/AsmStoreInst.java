package kvtodev.mindustack.llvmir2riscm.compiler.backend.rvasm.inst;

import kvtodev.mindustack.llvmir2riscm.compiler.backend.rvasm.hierarchy.AsmBlock;
import kvtodev.mindustack.llvmir2riscm.compiler.backend.rvasm.operand.Immediate;
import kvtodev.mindustack.llvmir2riscm.compiler.backend.rvasm.operand.Register;

import java.util.Objects;

public class AsmStoreInst extends AsmBaseInst {

    public AsmStoreInst(Register adr, Register rs2, Immediate imm, AsmBlock parentBlock) {
        super(null, Objects.requireNonNull(adr), rs2, imm, parentBlock);
    }

    @Override
    public AsmBaseInst copy() {
        return new AsmStoreInst(rs1, rs2, imm, null);
    }

    @Override
    public String format() {
        return String.format("write %s %s %s\t", rs2, rs1, imm);


    }
}
