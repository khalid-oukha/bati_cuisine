package services;

import entities.Client;
import entities.Project;
import enums.project_status;
import repositories.project.ProjectRepository;
import repositories.project.ProjectRepositoryImpl;

import java.util.Map;

public class ProjectService {
    private final ProjectRepository projectRepository;
    private final MaterialService materialService;
    private final LaborService laborService;

    public ProjectService() {
        this.projectRepository = new ProjectRepositoryImpl();
        this.materialService = new MaterialService();
        this.laborService = new LaborService();
    }

    public Project createProject(String name, Client client) {
        Project project = new Project(name, client);
        project.setStatus(project_status.IN_PROGRESS);
        if (projectRepository.createProject(project)) {
            return project;
        }
        return null;
    }

    public Project getProjectByName(String name) {
        return projectRepository.findByName(name);
    }

    public Project updateProject(Project project) {
        Map<String, Double> materialsTotalCost = materialService.calculateTotalCostForAllMaterials(project);
        Map<String, Double> laborTotalCost = laborService.calculateAllLaborsCost(project);

        double totalCost = materialsTotalCost.get("TotalCostWithoutVAT") + laborTotalCost.get("TotalCostWithoutVAT");
        double totalCostWithVat = materialsTotalCost.get("TotalCostWithVAT") + laborTotalCost.get("TotalCostWithVAT");
        double profitMargin = project.getProfitMargin();
        double totalCostWithProfitMargin = totalCostWithVat + (totalCostWithVat * profitMargin / 100);
        project.setTotalCost(totalCostWithVat);
        project.setTotalCostWithProfitMargin(totalCostWithProfitMargin);
        return projectRepository.updateProject(project);
    }
}
