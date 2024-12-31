package kvtodev.mindustack.llvmir2riscm.compiler.backend.riscm.inst;

import kvtodev.mindustack.llvmir2riscm.compiler.backend.riscm.hierarchy.AsmBlock;
import kvtodev.mindustack.llvmir2riscm.compiler.backend.riscm.operand.PhysicalReg;
import kvtodev.mindustack.llvmir2riscm.compiler.backend.riscm.operand.Register;
import kvtodev.mindustack.llvmir2riscm.vmConfig.VmCodeGen;

public class AsmMoveInst extends AsmBaseInst {
    public AsmMoveInst(Register rd, Register rs1, AsmBlock parentBlock) {
        super(rd, rs1, null, null, parentBlock);
    }

    @Override
    public long genBin() {
        return (VmCodeGen.codePreProcessor.getFlagCounter("setRR") << 42) +
                (PhysicalReg.getAddress(rd) << 33) +
                (PhysicalReg.getAddress(rs1) << 24);
    }

    @Override
    public AsmBaseInst copy() {
        return new AsmMoveInst(rd, rs1, null);
    }

    @Override
    public String genLiter() {
        return String.format("set %s %s", rd, rs1);
    }
}
