package services;

import entities.Component;
import entities.Material;
import entities.Project;
import enums.ComponentType;
import repositories.material.MaterialRepository;
import repositories.material.MaterialRepositoryImpl;

public class MaterialService {
    private final MaterialRepository materialRepository;
    private final ComponentService componentService;

    public MaterialService() {
        this.materialRepository = new MaterialRepositoryImpl();
        this.componentService = new ComponentService();
    }

    public Material createMaterial(String name, double quantity, double unitCost, double transportCost, double qualityCoefficient, Project project) {

        Component component = componentService.createComponent(name, ComponentType.MATERIAL, project);

        if (component != null) {
            Material material = new Material(
                    component.getId(),
                    component.getName(),
                    ComponentType.MATERIAL,
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
}
