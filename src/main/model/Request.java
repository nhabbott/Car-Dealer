package main.model;

import javafx.scene.control.Button;

public class Request {
    String name, vin, make, model, year, mileage, price;

    Button accept;
    Button decline;

    public Request(String name, String vin, String make, String model, String year, String mileage, String price
                    ,Button accept, Button decline) {
        this.name = name;
        this.vin = vin;
        this.make = make;
        this.model = model;
        this.year = year;
        this.mileage = mileage;
        this.price = price;
        this.accept = accept;
        this.decline = decline;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Button getAccept() {
        return accept;
    }

    public void setAccept(Button accept) {
        this.accept = accept;
    }

    public Button getDecline() {
        return decline;
    }

    public void setDecline(Button decline) {
        this.decline = decline;
    }
}
