package services;

import entities.Component;
import entities.Labor;
import entities.Project;
import enums.ComponentType;
import repositories.Labor.LaborRepository;
import repositories.Labor.LaborRepositoryImpl;

public class LaborService {
    private LaborRepository laborRepository;
    private ComponentService componentService;

    public LaborService() {
        this.laborRepository = new LaborRepositoryImpl();
        this.componentService = new ComponentService();
    }

    public Labor createLabor(String name, double hourlyRate, double workingHours, double workerProductivity, Project project, double taxRate) {
        Component component = componentService.createComponent(name, ComponentType.LABOUR, taxRate, project);
        if (component != null) {
            Labor labor = new Labor(
                    component.getId(),
                    component.getName(),
                    ComponentType.LABOUR,
                    component.getVatRate(),
                    hourlyRate,
                    workingHours,
                    workerProductivity,
                    component.getProject()
            );
            if (laborRepository.create(labor)) {
                return labor;
            }
        }
        return null;
    }
}
