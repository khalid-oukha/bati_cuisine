package entities;

import enums.ComponentType;

public class Material extends Component {
    private double unitCost;
    private double quantity;
    private double transportCost;
    private double qualityCoefficient;

    public Material(int id, String name, ComponentType componentType, Project project, double unitCost, double quantity, double transportCost, double qualityCoefficient) {
        super(id, name, componentType, project);
        this.unitCost = unitCost;
        this.quantity = quantity;
        this.transportCost = transportCost;
        this.qualityCoefficient = qualityCoefficient;
    }

    public Material(String name, ComponentType componentType, Project project, double unitCost, double quantity, double transportCost, double qualityCoefficient) {
        super(name, componentType, project);
        this.unitCost = unitCost;
        this.quantity = quantity;
        this.transportCost = transportCost;
        this.qualityCoefficient = qualityCoefficient;
    }

    public double getUnitCost() {
        return unitCost;
    }

    public double getQuantity() {
        return quantity;
    }

    public double getTransportCost() {
        return transportCost;
    }

    public double getQualityCoefficient() {
        return qualityCoefficient;
    }

}
