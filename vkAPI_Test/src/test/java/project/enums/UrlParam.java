package project.enums;

public enum  UrlParam {
    POST_ID("post_id"),
    MESSAGE("message"),
    PHOTO("photo"),
    TYPE("type"),
    ITEM_ID("item_id"),
    ATTACHMENT("attachments"),
    POSTS("photos"),
    EXTENSION("extended"),
    FILTER("filtes"),
    HASH("hash"),
    SERVER("server");

    private String method;

    UrlParam(String param) {
        this.method = param;
    }

    public String getParam() {
        return method;
    }
}
