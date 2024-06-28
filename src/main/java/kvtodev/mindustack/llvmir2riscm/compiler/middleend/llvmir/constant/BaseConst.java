package kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.constant;

import kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.User;
import kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.type.IRBaseType;

public abstract class BaseConst extends User {

    public BaseConst(String name, IRBaseType type) {
        super(name, type);
    }

    public abstract boolean isNull();


    public int size() {
        return 1;
    }
}
