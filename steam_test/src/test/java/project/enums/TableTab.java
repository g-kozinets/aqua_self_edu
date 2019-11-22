package project.enums;

public enum TableTab {
    TOP_SELLERS("tab_select_TopSellers" , "topSelling"),
    NEW("tab_select_NewReleases" , "new"),
    POPULAR("tab_select_ConcurrentUsers", "popular");

    private String id;
    private String attr;
    TableTab(String id, String attr) {
        this.id = id;
        this.attr = attr;
    }

    public String getId() {
        return id;
    }
    public String getAttr() {
        return attr;
    }
}
