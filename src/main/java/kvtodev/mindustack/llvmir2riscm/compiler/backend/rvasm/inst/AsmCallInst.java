package kvtodev.mindustack.llvmir2riscm.compiler.backend.rvasm.inst;

import kvtodev.mindustack.llvmir2riscm.compiler.backend.rvasm.hierarchy.AsmBlock;
import kvtodev.mindustack.llvmir2riscm.compiler.backend.rvasm.hierarchy.AsmFunction;
import kvtodev.mindustack.llvmir2riscm.compiler.backend.rvasm.operand.PhysicalReg;
import kvtodev.mindustack.llvmir2riscm.compiler.backend.rvasm.operand.Register;

import java.util.HashSet;

public class AsmCallInst extends AsmBaseInst {
    public final AsmFunction callFunc;


    public AsmCallInst(AsmFunction callFunc, AsmBlock parentBlock) {
        super(null, null, null, null, parentBlock);
        this.callFunc = callFunc;
    }

    @Override
    public HashSet<Register> uses() {
        HashSet<Register> ret = new HashSet<>();
        for (int i = 0; i < callFunc.arguments.size(); i++)
            ret.add(PhysicalReg.a(i));
        return ret;
    }

    @Override
    public HashSet<Register> defs() {
        return new HashSet<>(PhysicalReg.callerSaved);
    }

    @Override
    public AsmBaseInst copy() {
        return new AsmCallInst(callFunc, null);
    }

    @Override
    public String format() {
        return
//String.format("op add ra @counter 1\njump %s always", callFunc.entryBlock.identifier)
                String.format("jump %s always", callFunc.entryBlock.identifier);

    }
}
