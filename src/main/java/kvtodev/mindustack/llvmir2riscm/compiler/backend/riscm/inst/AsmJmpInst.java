package kvtodev.mindustack.llvmir2riscm.compiler.backend.riscm.inst;

import kvtodev.mindustack.llvmir2riscm.compiler.backend.riscm.hierarchy.AsmBlock;
import kvtodev.mindustack.llvmir2riscm.vmConfig.VmCodeGen;

public class AsmJmpInst extends AsmBaseInst {
    public AsmBlock dest;

    public AsmJmpInst(AsmBlock dest, AsmBlock parentBlock) {
        super(null, null, null, null, parentBlock);
        this.dest = dest;
    }

    @Override
    public AsmBaseInst copy() {
        return new AsmJmpInst(dest, null);
    }

    @Override
    public long genBin() {
        return (VmCodeGen.codePreProcessor.getFlagCounter("jump-always")<<42)+dest.asmAddress.pointed;
    }

    @Override
    public String genLiter() {
        // j offset
        return String.format("jump %s always", dest);
    }
}
