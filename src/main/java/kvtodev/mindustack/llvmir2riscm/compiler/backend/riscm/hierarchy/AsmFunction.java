package kvtodev.mindustack.llvmir2riscm.compiler.backend.riscm.hierarchy;

import kvtodev.mindustack.llvmir2riscm.compiler.backend.riscm.operand.BaseOperand;
import kvtodev.mindustack.llvmir2riscm.compiler.backend.riscm.operand.Register;
import kvtodev.mindustack.llvmir2riscm.share.Pointer;

import java.util.ArrayList;
import java.util.List;

public class AsmFunction extends BaseOperand {

    public  long entryAddress;
    public final List<AsmBlock> blocks = new ArrayList<>();
    public final List<Register> arguments = new ArrayList<>();

    public AsmBlock entryBlock, exitBlock;
    public Pointer<Integer> stackUse=new Pointer<>(0);
    public boolean isGlobal;


    public AsmFunction(String identifier) {
        super(identifier);
    }
}
