package entities;

public class Meterial extends Component{
    private double unitCost;
    private double quantity;
    private double transportCost;
    private double qualityCoefficient;

    public Meterial(int id, String name, String componentType, double vatRate,Project project, double unitCost, double quantity, double transportCost, double qualityCoefficient) {
        super(id, name, componentType, vatRate, project);
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
