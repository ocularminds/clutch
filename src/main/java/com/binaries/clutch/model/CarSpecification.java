package com.binaries.clutch.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity//(table="car_specs")
public class CarSpecification {

    @Id
    private String id;
    private String label;
    private String model;
    private String color;
    private String roof;
    private int wheels;
    private String status;

    public CarSpecification() {
        this.wheels = 2;
        this.status = "OPEN";
        this.color = "BLACK";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getRoof() {
        return roof;
    }

    public void setRoof(String roof) {
        this.roof = roof;
    }

    public int getWheels() {
        return wheels;
    }

    public void setWheels(int wheels) {
        this.wheels = wheels;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
