package com.binaries.clutch.model;

public class Report {
    private String id;
    private String model;
    private String status;
    private String color;
    private int total;
    private static String[] models = {"VBOOT", "DONOUGHT", "NIMBLE", "CONVERTIBLE"};
    private static String[] colors = {"RED", "BLACK", "BLUE", "WHITE"};
    private static String[] states = {"OPEN", "APPROVED", "REJECTED", "PRODUCED"};

    public Report() {
        int rand = (int) (Math.random() * 10000);
        this.id = Integer.toString(rand);
        this.model = models[rand % 4];
        this.status = states[rand % 4];
        this.color = colors[rand % 4];
        this.total = rand;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
