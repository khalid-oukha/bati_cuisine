package entities;

import enums.project_status;

public class Project {
    private int id;
    private String name;
    private double profitMargin;
    private double totalCost;
    private project_status status;
    private Client client;
    private double totalCostWithProfitMargin;

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
        this.status = project_status.IN_PROGRESS;
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

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public void setStatus(project_status status) {
        this.status = status;
    }

    public void setProfitMargin(double profitMargin) {
        this.profitMargin = profitMargin;
    }

    public void setTotalCostWithProfitMargin(double totalCostWithProfitMargin) {
        this.totalCostWithProfitMargin = totalCostWithProfitMargin;
    }

    public String toString() {
        return "\n================================================================================================\n" +
                "=                                     Project Details                                            =\n" +
                "================================================================================================\n" +
                "  Project ID        : " + id + "\n" +
                "  Project Name      : '" + name + "'\n" +
                "  Profit Margin     : " + profitMargin + "%\n" +
                "  Total Cost        : " + totalCost + " DH\n" +
                "  Total Cost with Profit Margin: " + totalCostWithProfitMargin + " DH\n" +
                "  Status            : " + status + "\n" +
                "  Client Name       : " + client.getName() + "\n" +
                "================================================================================================\n";
    }

}
