package carsProject.models;

import java.util.ArrayList;

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

    public void setTrim(ArrayList<String> trim) {
        setEngine(trim.get(0));
        setTransmission(trim.get(1));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarSpecs carSpecs = (CarSpecs) o;
        return year == carSpecs.year &&
                maker.equals(carSpecs.maker) &&
                model.equals(carSpecs.model) &&
                engine.equals(carSpecs.engine) &&
                transmission.equals(carSpecs.transmission);
    }

    public String getFullName() {
        return String.format("%s %s %s", year, maker, model);
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