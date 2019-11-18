package carsProject.models;

public class Car extends BaseModel implements Cloneable {
    private String maker;
    private String model;
    private int year;
    private String engine;
    private String transmission;

    public Car(String maker, String model, int year) {
        this.maker = maker;
        this.model = model;
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return year == car.year &&
                maker.equals(car.maker) &&
                model.equals(car.model) &&
                engine.equals(car.engine) &&
                transmission.equals(car.transmission);
    }

    @Override
    public Car clone() {
        Car clone = null;
        try {
            clone = (Car) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
        return clone;
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
