package project.enums;

public enum SideMenuId {
    MY_PROFILE("l_pr"),
    NEWS("l_nwsf");

    private String id;

    SideMenuId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
