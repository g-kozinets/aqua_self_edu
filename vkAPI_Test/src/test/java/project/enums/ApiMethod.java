package project.enums;

public enum  ApiMethod {
    POST("wall.post"),
    DELETE_POST("wall.delete"),
    EDIT_POST("wall.edit"),
    GET_POST("wall.getById"),
    LIKE("likes.add"),
    GET_LIKES("likes.getList"),
    SAVE_WALL_PHOTO("photos.saveWallPhoto"),
    GEL_WALL_SERVER("photos.getWallUploadServer"),
    COMMENT("wall.createComment");

    private String method;

    ApiMethod(String method) {
        this.method = method;
    }

    public String getMethod() {
        return method;
    }
}
