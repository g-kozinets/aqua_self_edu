package project.enums;

public enum Genre {
    INDIE("indie"),
    ACTION("action"),
    ADVENTURE("adventure");

    private String id;

    Genre(String id) {
        this.id = id;
    }

    public String getAttr() {
        return id;
    }
}
