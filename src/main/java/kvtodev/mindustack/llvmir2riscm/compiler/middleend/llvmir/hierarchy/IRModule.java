package kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.hierarchy;

import kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.Value;

import java.util.ArrayList;
import java.util.List;

public class IRModule {

  public List<IRFunction> functions = new ArrayList<>();
  public List<Value> globalVariables = new ArrayList<>();
  public List<IRFunction> externFunctions = new ArrayList<>();
  
  }
