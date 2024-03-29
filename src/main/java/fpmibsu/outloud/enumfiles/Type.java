package fpmibsu.outloud.enumfiles;

public enum Type {
    USER("USER"),
    MODERATOR("MODERATOR"),
    HELPER("HELPER");

    private String value;

    private Type(String value) {
        this.value = value;
    }

    public static Type fromString(String value) {
        if(value != null) {
            for (Type type : Type.values()) {
                if(value.equalsIgnoreCase(type.value)) {
                    return type;
                }
            }
        }
        throw new IllegalArgumentException("Wrong user type!");
    }
}
