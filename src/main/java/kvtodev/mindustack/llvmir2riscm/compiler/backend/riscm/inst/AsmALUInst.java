package kvtodev.mindustack.llvmir2riscm.compiler.backend.riscm.inst;

import kvtodev.mindustack.llvmir2riscm.compiler.backend.riscm.hierarchy.AsmBlock;
import kvtodev.mindustack.llvmir2riscm.compiler.backend.riscm.operand.PhysicalReg;
import kvtodev.mindustack.llvmir2riscm.compiler.backend.riscm.operand.Register;
import kvtodev.mindustack.llvmir2riscm.vmConfig.VmCodeGen;

// Arithm and Logic Inst
// add, sub, ...
// slt, seqz, ...

public class AsmALUInst extends AsmBaseInst {
    public final String op;
    static final String symbol = "op";

    public AsmALUInst(String op, Register rd, Register rs1, Register rs2, AsmBlock parentBlock) {
        super(rd, rs1, rs2, null, parentBlock);
        this.op = op;
    }


    public AsmALUInst(String op, Register rd, Register rs1, AsmBlock parentBlock) {
        super(rd, rs1, null, null, parentBlock);
        this.op = op;
    }

    @Override
    public long genBin() {
        return (VmCodeGen.codePreProcessor.getFlagCounter("op-"+this.op)<<42)
                + (PhysicalReg.getAddress(rd)<<33)
                +(PhysicalReg.getAddress(rs1)<<24)
                +(PhysicalReg.getAddress(rs2)<<15);

    }

    @Override
    public AsmBaseInst copy() {
        return new AsmALUInst(op, rd, rs1, rs2, null);
    }

    @Override
    public String genLiter() {
        // add rd, rs1, rs2
        // addi rd, rs1, imm


        if (this.imm != null) // I-Type
            return String.format("%s %s %s %s %s", symbol, op, rd, rs1, imm);
        else if (this.rs2 != null) // R-Type
            return String.format("%s %s %s %s %s", symbol, op, rd, rs1, rs2);
        else
            // unary, maybe pseudo inst
            return String.format("%s %s %s %s", symbol, op, rd, rs1);
    }
}
