package info.teksol.schemacode.mindustry.processor;

public interface MindustryValue {
    MindustryValueType getMindustryValueType();

    double getDoubleValue();

    long getLongValue();

    Object getObject();

    default boolean isObject() {
        return getMindustryValueType() == MindustryValueType.OBJECT;
    }
}
