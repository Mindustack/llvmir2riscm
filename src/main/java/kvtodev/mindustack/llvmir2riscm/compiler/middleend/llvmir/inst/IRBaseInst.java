package kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.inst;

import kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.User;
import kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.hierarchy.IRBlock;
import kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.type.IRBaseType;
import kvtodev.mindustack.llvmir2riscm.share.pass.IRInstPass;

public abstract class IRBaseInst extends User {

    public IRBlock parentBlock;
    public String instName;


    public IRBaseInst(String name, IRBaseType type) {
        super(name, type);
    }


    public abstract boolean isTerminator();

    public abstract IRBaseInst copy();

    public abstract void accept(IRInstPass visitor) throws Exception;
}
