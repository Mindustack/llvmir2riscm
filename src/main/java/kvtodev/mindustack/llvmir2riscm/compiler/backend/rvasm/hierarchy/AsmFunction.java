package kvtodev.mindustack.llvmir2riscm.compiler.backend.rvasm.hierarchy;

import kvtodev.mindustack.llvmir2riscm.compiler.backend.rvasm.operand.BaseOperand;
import kvtodev.mindustack.llvmir2riscm.compiler.backend.rvasm.operand.Register;
import kvtodev.mindustack.llvmir2riscm.compiler.share.pass.Pointer;

import java.util.ArrayList;
import java.util.List;

public class AsmFunction extends BaseOperand {

    public final List<AsmBlock> blocks = new ArrayList<>();
    public final List<Register> arguments = new ArrayList<>();

    public AsmBlock entryBlock, exitBlock;
    public Pointer<Integer> stackUse=new Pointer<>(0);


    public AsmFunction(String identifier) {
        super(identifier);
        stackUse.pointed=1;
    }
}
