package info.teksol.schemacode.mindustry;

public record Unit(
        String baseName,
        String name,
        int id
) implements NumberedConstant {

    public static Unit forId(int id) {
        return NumberedConstants.UNIT_ID_MAP.get(id);
    }

    public static Unit forName(String name) {
        return NumberedConstants.UNIT_MAP.get(name);
    }
}
