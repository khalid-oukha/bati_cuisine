package entities;

import enums.project_status;

public class Project {
    private int id;
    private String name;
    private double profitMargin;
    private double totalCost;
    private project_status status;
    private Client client;

    public Project(int id, String name, double profitMargin, double totalCost, project_status status, Client client) {
        this.id = id;
        this.name = name;
        this.profitMargin = profitMargin;
        this.totalCost = totalCost;
        this.status = status;
        this.client = client;
    }

    public Project(String name, Client client) {
        this.name = name;
        this.client = client;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getProfitMargin() {
        return profitMargin;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public project_status getStatus() {
        return status;
    }

    public Client getClient() {
        return client;
    }

    public void setId(int id) {
        this.id = id;
    }
}
