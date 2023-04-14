package fpmibsu.outloud.enumfiles;

public enum Status {
    CHECKED("CHECKED"),
    DENIED("DENIED"),
    ACCEPTED("ACCEPTED"),
    WAITED("WAITED");

    private String value;

    private Status(String value) {
        this.value = value;
    }

    public static Status fromString(String value) {
        if(value != null) {
            for (Status status : Status.values()) {
                if(value.equalsIgnoreCase(status.value)) {
                    return status;
                }
            }
        }
        throw new IllegalArgumentException("Wrong report status!");
    }
}
