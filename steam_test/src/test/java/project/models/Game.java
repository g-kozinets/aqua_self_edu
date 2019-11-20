package project.models;

import java.util.Comparator;
import static framework.utils.TestUtils.convertToInt;

public class Game {
    private String name;
    private String originalPrice;
    private String finalPrice;
    private String discount;

    public Game(String name, String originalPrice, String finalPrice, String discount) {
        this.name = name;
        this.originalPrice = originalPrice;
        this.finalPrice = finalPrice;
        this.discount = discount;
    }


    public static Comparator<Game> byDiscount = new Comparator<Game>() {

        public int compare(Game g1, Game g2) {
            int discount1 = convertToInt(g1.getDiscount());
            int discount2 = convertToInt(g2.getDiscount());

            /*For ascending order*/
            return discount1-discount2;

            /*For descending order*/
            //rollno2-rollno1;
        }};

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(String finalPrice) {
        this.finalPrice = finalPrice;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }
}
