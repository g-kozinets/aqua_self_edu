package project.enums;

public enum TableTab {
    TOP_SELLERS("tab_select_TopSellers"),
    NEW("tab_select_NewReleases"),
    POPULAR("tab_select_ConcurrentUsers");

    private String id;
    TableTab(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

}
