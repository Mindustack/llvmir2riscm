package kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.type;


import java.util.ArrayList;
import java.util.List;

public class StructType extends IRBaseType {
    public List<IRBaseType> memberVarTypes = new ArrayList<>();

    public StructType() {

    }


    @Override
    public boolean match(IRBaseType other) {
        if (!(other instanceof StructType)) return false;
        if (this.memberVarTypes.size() != ((StructType) other).memberVarTypes.size()) return false;

        for (int i = 0; i < this.memberVarTypes.size(); i++) {


            if (this.memberVarTypes.get(i).getClass() == ((StructType) other).memberVarTypes.get(i).getClass())
                return false;
        }


        return true;


    }

    @Override
    public int size() {
        int ret = 0;
        for (IRBaseType memberVarType : memberVarTypes) ret += memberVarType.size();
        return ret;
    }

    public int memberOffset(int index) {
        int ret = 0;
        for (int i = 0; i < index; ++i) ret += memberVarTypes.get(i).size();
        return ret;
    }

    @Override
    public String toString() {
        return memberVarTypes.toString();
    }


    @Override
    public List<String> toReg(List<String> list, String name) {
      for (int i = 0; i < this.memberVarTypes.size(); i++) {
        this.memberVarTypes.get(i).toReg(list,name+"_"+i);
        
      }
      return list;
    }

}
