package carsProject.enums;

public enum CarParameters {
    MAKER("makeId"),
    MODEL("modelId"),
    YEAR("year");

    private String id;
    CarParameters(String id) {
        this.id = id;
    }

    public String getId() {
        return (String) id;
    }
}
