package services;

import entities.Component;
import entities.Material;
import entities.Project;
import enums.ComponentType;
import repositories.material.MaterialRepository;
import repositories.material.MaterialRepositoryImpl;

import java.util.List;

public class MaterialService {
    private final MaterialRepository materialRepository;
    private final ComponentService componentService;

    public MaterialService() {
        this.materialRepository = new MaterialRepositoryImpl();
        this.componentService = new ComponentService();
    }

    public Material createMaterial(String name, double quantity, double unitCost, double transportCost, double qualityCoefficient, Project project, double taxRate) {

        Component component = componentService.createComponent(name, ComponentType.MATERIAL, taxRate, project);

        if (component != null) {
            Material material = new Material(
                    component.getId(),
                    component.getName(),
                    ComponentType.MATERIAL,
                    component.getVatRate(),
                    component.getProject(),
                    unitCost,
                    quantity,
                    transportCost,
                    qualityCoefficient
            );

            if (materialRepository.create(material)) {
                return material;
            }
        }
        return null;
    }

    public double calculateTotalCost(Material material) {
        return ((material.getUnitCost() * material.getQuantity()) + material.getTransportCost()) * material.getQualityCoefficient();
    }

    public double calculateTotalCostWithVat(Material material) {
        return calculateTotalCost(material) * (1 + material.getVatRate());
    }

    public void displayMaterialDetails(List<Material> materials) {

        for (Material material : materials) {
            System.out.println("Name: " + material.getName() + "\n" +
                    "Quantity: " + material.getQuantity() + "\n" +
                    "Unit Cost: " + material.getUnitCost() + "\n" +
                    "Transport Cost: " + material.getTransportCost() + "\n" +
                    "Quality Coefficient: " + material.getQualityCoefficient() + "\n" +
                    "Total Cost: " + calculateTotalCost(material) + "\n" +
                    "Total Cost with VAT: " + calculateTotalCostWithVat(material) + "\n");
        }
    }
}
