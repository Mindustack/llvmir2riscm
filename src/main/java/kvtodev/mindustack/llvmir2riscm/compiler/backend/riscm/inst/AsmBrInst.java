package kvtodev.mindustack.llvmir2riscm.compiler.backend.riscm.inst;

import kvtodev.mindustack.llvmir2riscm.compiler.backend.riscm.hierarchy.AsmBlock;
import kvtodev.mindustack.llvmir2riscm.compiler.backend.riscm.operand.PhysicalReg;
import kvtodev.mindustack.llvmir2riscm.compiler.backend.riscm.operand.Register;
import kvtodev.mindustack.llvmir2riscm.vmConfig.VmCodeGen;

// no beqz now

public class AsmBrInst extends AsmBaseInst {
    public AsmBlock dest;
    String op;

    public AsmBrInst(String op, Register rs1, Register rs2, AsmBlock dest, AsmBlock parentBlock) {
        super(null, rs1, rs2, null, parentBlock);
        this.op = op;
        this.dest = dest;
    }

    @Override
    public long genBin() {
        return (VmCodeGen.codePreProcessor.getFlagCounter("jump-"+op))
                +(PhysicalReg.getAddress(rs1)<<33)
                +(PhysicalReg.getAddress(rs2)<<24)
                +dest.asmAddress.pointed;
    }

    @Override
    public AsmBaseInst copy() {
        return new AsmBrInst(op, rs1, rs2, dest, null);
    }

    @Override
    public String genLiter() {
        // beq rs1, rs2, dest
        return String.format("jump %s %s %s %s", dest, op, rs1, rs2);
    }
}
