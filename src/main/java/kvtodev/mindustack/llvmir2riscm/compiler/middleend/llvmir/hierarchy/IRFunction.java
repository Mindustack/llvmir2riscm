package kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.hierarchy;

import kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.User;
import kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.type.IRBaseType;
import kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.type.VoidType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class IRFunction extends User {

    public final List<IRBlock> blocks = new LinkedList<>();
    public IRModule parentModule;
    public IRBlock entryBlock, exitBlock;
    public boolean isGlobal = true;

    public List<IRBaseType> argTypes = new ArrayList<>();

    public IRFunction(String name, IRBaseType funcType, IRModule parentModule) {
        super(name, funcType);
        entryBlock.parentFunction = this;
        exitBlock.parentFunction = this;
        this.parentModule = parentModule;
    }

    public void addBlock(IRBlock block) {
        this.blocks.add(block);
        block.parentFunction = this;
    }

    public IRFunction(String name, IRBaseType retType, IRBaseType... argTypes) {
        super(name, retType);
        Collections.addAll(this.argTypes, argTypes);
    }

    public boolean isVoid() {
        return this.type instanceof VoidType;
    }

}
