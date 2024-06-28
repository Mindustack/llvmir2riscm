package kvtodev.mindustack.llvmir2riscm.compiler.backend.optim;

import kvtodev.mindustack.llvmir2riscm.compiler.backend.rvasm.hierarchy.AsmBlock;
import kvtodev.mindustack.llvmir2riscm.compiler.backend.rvasm.hierarchy.AsmFunction;
import kvtodev.mindustack.llvmir2riscm.compiler.backend.rvasm.inst.AsmALUInst;
import kvtodev.mindustack.llvmir2riscm.compiler.backend.rvasm.inst.AsmBaseInst;
import kvtodev.mindustack.llvmir2riscm.compiler.backend.rvasm.operand.PhysicalReg;
import kvtodev.mindustack.llvmir2riscm.compiler.share.Lang;
import kvtodev.mindustack.llvmir2riscm.compiler.share.pass.AsmFuncPass;

public class ZeroInstPeephole implements AsmFuncPass {

    @Override
    public void runOnFunc(AsmFunction function) {

        for (AsmBlock block : function.blocks) {
            var it = block.instructions.iterator();

            while (it.hasNext()) {
                AsmBaseInst inst = it.next();

                if (inst instanceof AsmALUInst) {
                    String op = ((AsmALUInst) inst).op;

                    switch (op) {
                        case Lang.AddOperation:
                        case Lang.SubOperation:
                        case Lang.OrOperation:
                        case Lang.XorOperation:
                        case Lang.ShiftLeftOperation:
                        case Lang.ShiftRightOperation: {
                            if (inst.rd.color == inst.rs1.color) {
                                if (inst.imm != null && inst.imm.getValue().intValue() == 0) it.remove();
                                if (inst.rs2 != null && inst.rs2.color == PhysicalReg.reg("zero")) it.remove();
                            }
                        }
                    }
                }
            }
        }

    }
}
