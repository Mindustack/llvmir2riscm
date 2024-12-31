package kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir;

import kvtodev.mindustack.llvmir2riscm.compiler.backend.riscm.operand.BaseOperand;
import kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.constant.BaseConst;
import kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.type.IRBaseType;

import java.util.ArrayList;
import java.util.List;


public class Value {
    public static Boolean rename = false;
    public IRBaseType type;
    public List<User> users = new ArrayList<User>();
    public String name;
    public Object object;
    public BaseOperand asmOperand = null;
    public BaseConst initValue;



    public Value(String name, IRBaseType type) {
        this.name = name;
        this.type = type;
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    @Override
    public String toString() {
        return name + ':' + type;
    }

    public int size() {
        if (initValue != null) return initValue.size();
        return type.size();

    }


}
