package services;

import entities.Component;
import entities.Labor;
import entities.Project;
import enums.ComponentType;
import repositories.Labor.LaborRepository;
import repositories.Labor.LaborRepositoryImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public List<Labor> getAllLabors(Project project) {
        return laborRepository.getAll(project);
    }

    public double calculateLaborCost(Labor labor) {
        return labor.getHourlyRate() * labor.getWorkingHours() * labor.getWorkerProductivity();
    }

    public Map<String, Double> calculateAllLaborsCost(Project project) {
        double allLaborsCost = 0;
        double allLaborsCostWithVat = 0;

        for (Labor labor : laborRepository.getAll(project)) {
            allLaborsCost += calculateLaborCost(labor);
            allLaborsCostWithVat += calculateLaborCost(labor) * (1 + labor.getVatRate());
        }

        Map<String, Double> result = new HashMap<>();
        result.put("TotalCostWithoutVAT", allLaborsCost);
        result.put("TotalCostWithVAT", allLaborsCostWithVat);

        return result;
    }

    public boolean updateLabor(int id, String name, Project project, double hourlyRate, double workingHours, double workerProductivity) {
        Component component = componentService.findById(id, project);
        if (component == null) {
            return false;
        }

        Labor labor = new Labor(
                component.getId(),
                name,
                ComponentType.LABOUR,
                component.getVatRate(),
                hourlyRate,
                workingHours,
                workerProductivity,
                project
        );

        component.setName(name);
        if (componentService.updateComponent(component, project)) {
            return laborRepository.update(labor);
        }
        return false;
    }

    public Labor getLaborById(int id, Project project) {
        return laborRepository.findById(id, project);
    }

    public boolean deleteLabor(int id, Project project) {
        Component component = componentService.findById(id, project);
        if (component == null) {
            return false;
        }
        return componentService.deleteComponent(component);
    }
}
