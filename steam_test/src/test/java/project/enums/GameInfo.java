package project.enums;

public enum  GameInfo {
    GAME_NAME("//div[@class='tab_item_name']"),
    DISCOUNT("//div[@class='discount_pct']"),
    ORIGINAL_PRICE("//div[@class='discount_original_price']"),
    FINAL_PRICE("//div[@class='discount_final_price']");

    private String tag;
    GameInfo(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }
}
