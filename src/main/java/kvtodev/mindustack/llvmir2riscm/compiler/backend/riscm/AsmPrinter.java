package kvtodev.mindustack.llvmir2riscm.compiler.backend.riscm;

import kvtodev.mindustack.llvmir2riscm.compiler.backend.riscm.hierarchy.AsmBlock;
import kvtodev.mindustack.llvmir2riscm.compiler.backend.riscm.hierarchy.AsmFunction;
import kvtodev.mindustack.llvmir2riscm.compiler.backend.riscm.hierarchy.AsmModule;
import kvtodev.mindustack.llvmir2riscm.share.pass.AsmBlockPass;
import kvtodev.mindustack.llvmir2riscm.share.pass.AsmFuncPass;
import kvtodev.mindustack.llvmir2riscm.share.pass.AsmModulePass;

public class AsmPrinter implements AsmModulePass, AsmFuncPass, AsmBlockPass {

    long cur=512;
    @Override
    public String toString() {
        return content.toString();
    }

    public AsmPrinter(AsmModule irModule) {
        this.runOnModule(irModule);
    }

    StringBuilder content = new StringBuilder();

    @Override
    public void runOnBlock(AsmBlock block) {
        block.asmAddress.pointed=cur;
        content.append('\n');
        content.append('\t').append(block.identifier).append(":\t\t");
        block.instructions.forEach(inst -> {
            try {
                content.append("\n\t\t");
                content.append(inst.genLiter());
                cur+=1;
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public void runOnFunc(AsmFunction function) {

        content.append("\n#");
        content.append(function.identifier).append(":\t");
        function.arguments.forEach(value -> content.append(value).append('\t'));
        function.blocks.forEach(this::runOnBlock);
    }

    @Override
    public void runOnModule(AsmModule module) {
//
        module.functions.forEach(this::runOnFunc);
    }
}
