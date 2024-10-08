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
import java.util.Optional;

public class LaborService {
    private final LaborRepository laborRepository;
    private final ComponentService componentService;

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

//    public Map<String, Double> calculateAllLaborsCost(Project project) {
//        double allLaborsCost = 0;
//        double allLaborsCostWithVat = 0;
//
//        for (Labor labor : laborRepository.getAll(project)) {
//            allLaborsCost += calculateLaborCost(labor);
//            allLaborsCostWithVat += calculateLaborCost(labor) * (1 + labor.getVatRate());
//        }
//
//        Map<String, Double> result = new HashMap<>();
//        result.put("TotalCostWithoutVAT", allLaborsCost);
//        result.put("TotalCostWithVAT", allLaborsCostWithVat);
//
//        return result;
//    }

    public Map<String, Double> calculateAllLaborsCost(Project project) {

        List<Labor> labors = laborRepository.getAll(project);
        double allLaborsCost = labors.stream().mapToDouble(this::calculateLaborCost).sum();
        double allLaborsCostWithVat = labors.stream().mapToDouble(labor -> calculateLaborCost(labor) * (1 + labor.getVatRate())).sum();

        Map<String, Double> result = new HashMap<>();
        result.put("TotalCostWithoutVAT", allLaborsCost);
        result.put("TotalCostWithVAT", allLaborsCostWithVat);

        return result;
    }

    public boolean updateLabor(int id, String name, Project project, double hourlyRate, double workingHours, double workerProductivity) {
        Optional<Component> componentOptional = componentService.findById(id, project);
        if (componentOptional.isEmpty()) {
            return false;
        }

        Component component = componentOptional.get();
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
        Optional<Labor> optionalLabor = laborRepository.findById(id, project);
        return optionalLabor.orElse(null);
    }

    public boolean deleteLabor(int id, Project project) {
        Optional<Component> componentOptional = componentService.findById(id, project);
        return componentOptional.map(componentService::deleteComponent).orElse(false);
    }
}
