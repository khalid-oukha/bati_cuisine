package entities;

import enums.ComponentType;

public class Component {
    private int id;
    private String name;
    private ComponentType componentType;
    private double vatRate;
    private Project project;


    public Component(String name, ComponentType componentType, double vatRate, Project project) {
        this.name = name;
        this.componentType = componentType;
        this.vatRate = vatRate;
        this.project = project;
    }

    public Component(int id, String name, ComponentType componentType, double vatRate, Project project) {
        this.id = id;
        this.name = name;
        this.componentType = componentType;
        this.vatRate = vatRate;
        this.project = project;
    }

    public Component(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ComponentType getComponentType() {
        return componentType;
    }

    public double getVatRate() {
        return vatRate;
    }

    public Project getProject() {
        return project;
    }

    public void setId(int id) {
        this.id = id;
    }
}
