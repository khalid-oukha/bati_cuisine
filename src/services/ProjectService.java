package services;

import entities.Client;
import entities.Project;
import enums.project_status;
import repositories.project.ProjectRepository;
import repositories.project.ProjectRepositoryImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public Optional<Project> getProjectById(int id) {
        return projectRepository.findByProjectId(id);
    }

    public double calculateTotalCostWithProfitMargin(Project project) {
        Map<String, Double> materialsTotalCost = materialService.calculateTotalCostForAllMaterials(project);
        Map<String, Double> laborTotalCost = laborService.calculateAllLaborsCost(project);

        double totalCostWithVat = materialsTotalCost.get("TotalCostWithVAT") + laborTotalCost.get("TotalCostWithVAT");
        double profitMargin = project.getProfitMargin();
        double totalCostWithProfitMargin = totalCostWithVat + (totalCostWithVat * profitMargin / 100);

        project.setTotalCost(totalCostWithVat);
        project.setTotalCostWithProfitMargin(totalCostWithProfitMargin);
        return totalCostWithProfitMargin;
    }

    public Project updateProject(Project project) {
        double totalCostWithProfitMargin = calculateTotalCostWithProfitMargin(project);
        project.setTotalCostWithProfitMargin(totalCostWithProfitMargin);

        return projectRepository.updateProject(project);
    }

    public HashMap<Integer, Project> getCompleteProjects() {
        HashMap<Integer, Project> projects = projectRepository.findAllProjects();
        return projects.entrySet().stream().filter(project -> project.getValue().getStatus() == project_status.COMPLETED)
                .collect(Collectors.toMap(project -> project.getKey(), project -> project.getValue(), (e1, e2) -> e1, HashMap::new));
    }

}
