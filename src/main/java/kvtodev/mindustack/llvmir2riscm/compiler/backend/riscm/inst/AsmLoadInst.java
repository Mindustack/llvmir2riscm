package kvtodev.mindustack.llvmir2riscm.compiler.backend.riscm.inst;

import kvtodev.mindustack.llvmir2riscm.compiler.backend.riscm.hierarchy.AsmBlock;
import kvtodev.mindustack.llvmir2riscm.compiler.backend.riscm.operand.Immediate;
import kvtodev.mindustack.llvmir2riscm.compiler.backend.riscm.operand.PhysicalReg;
import kvtodev.mindustack.llvmir2riscm.compiler.backend.riscm.operand.Register;
import kvtodev.mindustack.llvmir2riscm.vmConfig.VmCodeGen;

import java.util.Objects;

public class AsmLoadInst extends AsmBaseInst {

    public AsmLoadInst(Register rd, Register rs1, Immediate imm, AsmBlock parentBlock) {
        super(rd, Objects.requireNonNull(rs1), null, imm, parentBlock);
    }

    @Override
    public long genBin() {
        return (VmCodeGen.codePreProcessor.getFlagCounter("load") << 42) +
                (PhysicalReg.getAddress(rd) << 33) +
                (PhysicalReg.getAddress(rs1) << 24) + imm.getValue().longValue();
    }

    @Override
    public AsmBaseInst copy() {
        return new AsmLoadInst(rd, rs1, imm, null);
    }

    @Override
    public String genLiter() {
        return String.format("read %s at %s %s\t", rd, rs1, imm);


    }
}
