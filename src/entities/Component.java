package entities;

public abstract class Component {
    private int id;
    private String name;
    private String componentType;
    private double vatRate;
    private Project project;

    public Component(int id, String name, String componentType, double vatRate, Project project) {
        this.id = id;
        this.name = name;
        this.componentType = componentType;
        this.vatRate = vatRate;
        this.project = project;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getComponentType() {
        return componentType;
    }

    public double getVatRate() {
        return vatRate;
    }

    public Project getProject() {
        return project;
    }

    public void setName(String name) {
        this.name = name;
    }

}
