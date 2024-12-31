package kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.inst;

import kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.Value;
import kvtodev.mindustack.llvmir2riscm.share.pass.IRInstPass;

public class IRStoreInst extends IRBaseInst {

  public IRStoreInst(Value storePtr, Value storeValue) {
    super("store", storeValue.type);
    this.addOperand(storeValue);
    this.addOperand(storePtr);
    this.instName= "store";
  }

  public Value storeValue() {
    return this.getOperand(0);
  }

  public Value storePtr() {
    return this.getOperand(1);
  }

  public void replacePtr(Value value) {
    this.resetOperand(1, value);
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
