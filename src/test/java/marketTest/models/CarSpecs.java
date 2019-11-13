package marketTest.models;

public class CarSpecs extends BaseModel {
    private String maker;
    private String model;
    private int year;
    private String engine;
    private String transmission;

    public CarSpecs() {
    }

    public CarSpecs(String maker, String model, int year) {
        this.maker = maker;
        this.model = model;
        this.year = year;
    }


    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }
}
