package project.enums;

public enum CarParameter {
    MAKER("makeId"),
    MODEL("modelId"),
    YEAR("year");

    private String id;
    CarParameter(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
