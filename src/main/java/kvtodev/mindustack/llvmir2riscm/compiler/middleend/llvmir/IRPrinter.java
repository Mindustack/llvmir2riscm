package kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir;

import kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.hierarchy.IRBlock;
import kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.hierarchy.IRFunction;
import kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.hierarchy.IRModule;
import kvtodev.mindustack.llvmir2riscm.share.pass.IRBlockPass;
import kvtodev.mindustack.llvmir2riscm.share.pass.IRFuncPass;
import kvtodev.mindustack.llvmir2riscm.share.pass.IRModulePass;

import java.util.List;
import java.util.Objects;


public class IRPrinter implements IRModulePass, IRFuncPass, IRBlockPass {
    @Override
    public String toString() {
        return content.toString();
    }

    public IRPrinter(IRModule irModule) {
        this.runOnModule(irModule);
    }
    public IRPrinter(List<IRModule> modules){

    }

    StringBuilder content = new StringBuilder();

    @Override
    public void runOnBlock(IRBlock block) {
        content.append('\n');
        content.append('\t').append(block.name).append(":\t\t");
        block.instructions.forEach(inst -> {
            try {
                content.append("\n\t\t");
                content.append(inst.instName).append("\t\t").append(inst.operands);
                if(!Objects.equals(inst.name, inst.instName))content.append("\t\t->").append(inst.name);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public void runOnFunc(IRFunction function) {

        content.append('\n');
        content.append(function.name).append(':');
        function.operands.forEach(value -> content.append(value).append('\t'));
        content.append("->").append(function.type);
        function.blocks.forEach(this::runOnBlock);
    }

    @Override
    public void runOnModule(IRModule module) {


        module.globalVariables.forEach(var -> {
            content.append('\n').append(var.name).append(':').append(var.type);
            if (!var.initValue.isNull()) content.append('=').append(var.initValue);
        });
        module.functions.forEach(this::runOnFunc);
    }

}
