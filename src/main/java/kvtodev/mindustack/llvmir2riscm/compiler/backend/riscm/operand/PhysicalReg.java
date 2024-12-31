package kvtodev.mindustack.llvmir2riscm.compiler.backend.riscm.operand;

import kvtodev.mindustack.llvmir2riscm.llvmir2riscm;
import kvtodev.mindustack.llvmir2riscm.share.Lang;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhysicalReg extends Register {
  /*
   * RV32 Physical Reg Arch
   */
  public static final Map<String, PhysicalReg> phyRegs = new HashMap<>() {
    {
      Lang.Reg.forEach(regName -> put(regName, new PhysicalReg(regName)));
    }
  };

  // calling convention
  public static List<PhysicalReg> callerSaved = new ArrayList<>() {
    {
      Lang.RegCallerSaved.forEach(regName -> add(phyRegs.get(regName)));
      add(reg("ra"));
    }
  };
  public static List<PhysicalReg> calleeSaved = new ArrayList<>() {
    {
      Lang.RegCalleeSaved.forEach(regName -> add(phyRegs.get(regName)));
    }
  };
  public static List<PhysicalReg> assignable = new ArrayList<>() {
    {
      addAll(callerSaved);
      addAll(calleeSaved);
    }
  };
  public static long  getAddress(Register reg){
    if(reg==null)return  0;
    try{
     return Lang.Reg.indexOf(reg.identifier);
    } catch (Exception e) {
      llvmir2riscm.logger.severe("getAddress failed "+reg.identifier);
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }

  public PhysicalReg(String identifier) {
    super(identifier);
  }

  public static PhysicalReg reg(String regName) {
    return phyRegs.get(regName);
  }

  public static PhysicalReg a(int index) {
    return phyRegs.get(Lang.FuncArgRegPrefix + index);

  }

  public static PhysicalReg t(int index) {
    return phyRegs.get(Lang.TempRegPrefix + index);
  }

  public static PhysicalReg s(int index) {
    return phyRegs.get(Lang.SavedRegPrefix + index);
  }

  public static PhysicalReg gp(int index) {
    return phyRegs.get(Lang.GPRegPrefix + index);
  }
}
