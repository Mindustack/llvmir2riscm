package kvtodev.mindustack.llvmir2riscm.compiler.backend.riscm.inst;

import kvtodev.mindustack.llvmir2riscm.compiler.backend.riscm.hierarchy.AsmBlock;
import kvtodev.mindustack.llvmir2riscm.compiler.backend.riscm.operand.Immediate;
import kvtodev.mindustack.llvmir2riscm.compiler.backend.riscm.operand.PhysicalReg;
import kvtodev.mindustack.llvmir2riscm.compiler.backend.riscm.operand.Register;
import kvtodev.mindustack.llvmir2riscm.vmConfig.VmCodeGen;

import java.util.Objects;

public class AsmStoreInst extends AsmBaseInst {

    public AsmStoreInst(Register adr, Register rs2, Immediate imm, AsmBlock parentBlock) {
        super(null, Objects.requireNonNull(adr), rs2, imm, parentBlock);
    }

    @Override
    public long genBin() {
         return (VmCodeGen.codePreProcessor.getFlagCounter("store") << 42) +
                (PhysicalReg.getAddress(rd) << 33) +
                (PhysicalReg.getAddress(rs1) << 24) + imm.getValue().longValue();
    }

    @Override
    public AsmBaseInst copy() {
        return new AsmStoreInst(rs1, rs2, imm, null);
    }

    @Override
    public String genLiter() {
        return String.format("write %s at %s %s\t", rs2, rs1, imm);


    }
}
