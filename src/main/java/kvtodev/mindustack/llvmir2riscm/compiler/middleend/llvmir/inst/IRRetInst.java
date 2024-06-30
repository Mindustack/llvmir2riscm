package kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.inst;

import kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.Value;
import kvtodev.mindustack.llvmir2riscm.compiler.share.pass.IRInstPass;

public class IRRetInst extends IRBaseInst {


  public IRRetInst(Value retVal) {
    super("ret\t", retVal.type);
    this.addOperand(retVal);
    this.instName= "ret\t";
  }
  public IRRetInst(){
    super("ret\t",null) ;
    this.instName="ret\t";
  }

  public Value retVal() {
    return (this.getOperand(0));
  }

  public boolean isVoid() {
    return this.operands.isEmpty();
  }

    @Override
    public boolean isTerminator() {
        return false;
    }

  @Override
  public IRBaseInst copy() {
    return null;
  }

  @Override
  public void accept(IRInstPass visitor) {
    visitor.visit(this);
  }
}
