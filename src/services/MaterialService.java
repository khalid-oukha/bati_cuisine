package services;

import entities.Component;
import entities.Material;
import entities.Project;
import enums.ComponentType;
import repositories.material.MaterialRepository;
import repositories.material.MaterialRepositoryImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public List<Material> getAllMaterials(Project project) {
        return materialRepository.getAll(project);
    }

    public double calculateTotalCost(Material material) {
        return ((material.getUnitCost() * material.getQuantity()) + material.getTransportCost()) * material.getQualityCoefficient();
    }

    public double calculateTotalCostWithVat(Material material) {
        return calculateTotalCost(material) * (1 + material.getVatRate());
    }

    public Map<String, Double> calculateTotalCostForAllMaterials(Project project) {
        double allMaterialtotalCost = 0;
        double AllMaterialstotalCostWithVat = 0;
        for (Material material : materialRepository.getAll(project)) {
            allMaterialtotalCost += calculateTotalCost(material);
            AllMaterialstotalCostWithVat += calculateTotalCostWithVat(material);
        }
        Map<String, Double> result = new HashMap<>();
        result.put("TotalCostWithoutVAT", allMaterialtotalCost);
        result.put("TotalCostWithVAT", AllMaterialstotalCostWithVat);
        return result;
    }

    public boolean updateMaterial(int id, String name, Project project, double unitCost, double quantity, double transportCost, double qualityCoefficient) {
        Component component = componentService.findById(id, project);
        if (component == null) {
            return false;
        }

        Material material = new Material(
                component.getId(),
                component.getName(),
                component.getComponentType(),
                component.getVatRate(),
                component.getProject(),
                unitCost,
                quantity,
                transportCost,
                qualityCoefficient
        );

        component.setName(name);
        if (componentService.updateComponent(component, project)) {
            return materialRepository.update(material);
        }
        return false;
    }
}
