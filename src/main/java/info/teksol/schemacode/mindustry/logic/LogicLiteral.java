package info.teksol.schemacode.mindustry.logic;

import info.teksol.schemacode.mindustry.processor.MindustryValue;

public interface LogicLiteral extends LogicValue, MindustryValue {

    default boolean isLiteral() {
        return true;
    }

    default boolean isConstant() {
        return true;
    }

    boolean isNull();

    double getDoubleValue();

    String format();
}
