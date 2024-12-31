package kvtodev.mindustack.llvmir2riscm.compiler.backend.riscm.inst;

import kvtodev.mindustack.llvmir2riscm.compiler.backend.riscm.hierarchy.AsmBlock;
import kvtodev.mindustack.llvmir2riscm.compiler.backend.riscm.operand.Immediate;
import kvtodev.mindustack.llvmir2riscm.compiler.backend.riscm.operand.PhysicalReg;
import kvtodev.mindustack.llvmir2riscm.compiler.backend.riscm.operand.Register;
import kvtodev.mindustack.llvmir2riscm.vmConfig.VmCodeGen;

public class AsmLiInst extends AsmBaseInst {
    public AsmLiInst(Register rd, Immediate imm, AsmBlock parentBlock) {
        super(rd, null, null, imm, parentBlock);
    }

    @Override
    public long genBin() {

        if(imm.getValue().longValue()!=0){
            return (VmCodeGen.codePreProcessor.getFlagCounter("setRII")<<42)
                    +(PhysicalReg.getAddress(rd)<<33)
                    +(imm.getValue().longValue());
        }else if(imm.getValue().doubleValue()==0){
            return  (VmCodeGen.codePreProcessor.getFlagCounter("setRII")<<42)
                    +(PhysicalReg.getAddress(rd)<<33);
        }else {
            return  (VmCodeGen.codePreProcessor.getFlagCounter("setRIF")<<42)
                    +(PhysicalReg.getAddress(rd)<<33)
                    +(Double.doubleToLongBits(imm.getValue().doubleValue()));
        }
    }

    @Override
    public AsmBaseInst copy() {
        return new AsmLiInst(rd, imm, null);
    }

    @Override
    public String genLiter() {
        return String.format("set %s %s", rd, imm);
    }//set a number
}
