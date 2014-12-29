package icf.backend;

public enum OperationType {
    COMPILE,
    EXECUTE,
    UNKNOWN;

    public static OperationType fromString(String type) {
        if (type.equalsIgnoreCase("compile"))
            return COMPILE;
        else if (type.equalsIgnoreCase("execute"))
            return EXECUTE;
        else
            return UNKNOWN;
    }
}
