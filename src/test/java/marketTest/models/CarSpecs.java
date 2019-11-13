package marketTest.models;

public class CarSpecs extends BaseModel {
    private String maker;
    private String model;
    private int year;
    private String engine;
    private String transmission;

    public CarSpecs(String maker, String model, int year) {
        this.maker = maker;
        this.model = model;
        this.year = year;
    }


}
