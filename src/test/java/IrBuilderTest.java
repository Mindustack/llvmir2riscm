import kvtodev.mindustack.llvmir2riscm.llvmir2riscm;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class IrBuilderTest {
    public static void main(String[] args) {
//        test("testcases/Arithmetic_operation.ll");
//        test("testcases/Array_operations.ll");
//        test("testcases/Array_pointer.ll");
//        test("testcases/Assignment_statement.ll");
//        test("testcases/Assign_values_to_arrays_through_loops.ll");
//        test("testcases/Boolean_operation.ll");
//        test("testcases/Branch_without_loop.ll");
//        test("testcases/break_statement.ll");
//        test("testcases/char.ll");
//        test("testcases/Compound_sentence.ll");
//        test("testcases/continue_statement.ll");
//        test("testcases/double.ll");
//        test("testcases/Empty_statement.ll");
//        test("testcases/float.ll");
//        test("testcases/Function_declaration.ll");
//        test("testcases/Function_definition.ll");
//        test("testcases/Function_return_value.ll");
//        test("testcases/hanshucanshu_erweishuzu.ll");
//        test("testcases/hanshucanshu_sanweishuzu.ll");
//        test("testcases/hanshucanshu_yiweishuzu.ll");
//        test("testcases/if-else_statement.ll");
//        test("testcases/if_statement.ll");
//        test("testcases/long.ll");
//        test("testcases/Loop_in_branch.ll");
//        test("testcases/Multi-parameter_function_call.ll");
//        test("testcases/Multiple_declarations.ll");
//        test("testcases/No-argument_function_call.ll");
//        test("testcases/Nomain.ll");
//        test("testcases/Number_array_definition.ll");
//        test("testcases/OneArray.ll");
//        test("testcases/Operate_arrays_through_loops.ll");
//        test("testcases/Other_statements.ll");
//        test("testcases/Pointer_array.ll");
//        test("testcases/pointer.ll");
//        test("testcases/Pointer_passed_as_parameter.ll");
//        test("testcases/Relational_operations.ll");
//        test("testcases/Short-circuit_evaluation.ll");
//        test("testcases/short.ll");
//        test("testcases/Single_statement.ll");
        test("testcases/Structure.ll");
//        test("testcases/The_function_has_a_lot_of_arguments.ll");
//        test("testcases/The_function_name_is_very_long.ll");
//        test("testcases/TwoArray.ll");
//        test("testcases/Variable_duplicate_name.ll");
//        test("testcases/Variable_names_are_very_long.ll");
//        test("testcases/while_loop.ll");
    }


    public static void test(String item) {

        try {
            llvmir2riscm llvmir2riscm = new llvmir2riscm();
            llvmir2riscm.compile(new FileInputStream(item));
            llvmir2riscm.compile(new FileInputStream("libc/stdio.ll"));


            llvmir2riscm.assemble();
            llvmir2riscm.printBin();
            llvmir2riscm.printLiter();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }


    }
}
