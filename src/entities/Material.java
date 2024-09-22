package entities;

import enums.ComponentType;

public class Material extends Component {
    private double unitCost;
    private double quantity;
    private double transportCost;
    private double qualityCoefficient;

    public Material(int id, String name, ComponentType componentType, double vatRate, Project project, double unitCost, double quantity, double transportCost, double qualityCoefficient) {
        super(id, name, componentType, vatRate, project);
        this.unitCost = unitCost;
        this.quantity = quantity;
        this.transportCost = transportCost;
        this.qualityCoefficient = qualityCoefficient;
    }

    public Material(String name, ComponentType componentType, double vatRate, Project project, double unitCost, double quantity, double transportCost, double qualityCoefficient) {
        super(name, componentType, vatRate, project);
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

    @Override
    public String toString() {
        return "\n================================================================================================\n" +
                "=                                     Material Details                                           =\n" +
                "================================================================================================\n" +
                "  Material ID       : " + getId() + "\n" +
                "  Material Name     : '" + getName() + "'\n" +
                "  Material Type     : " + getComponentType() + "\n" +
                "  VAT Rate          : " + getVatRate() + "\n" +
                "  Project           : " + getProject().getName() + "\n" +
                "  Unit Cost         : " + unitCost + "\n" +
                "  Quantity          : " + quantity + "\n" +
                "  Transport Cost    : " + transportCost + "\n" +
                "  Quality Coefficient: " + qualityCoefficient + "\n";
    }
}
