package services;

import entities.Client;
import entities.Project;
import repositories.project.ProjectRepository;
import repositories.project.ProjectRepositoryImpl;

public class ProjectService {
    private final ProjectRepository projectRepository;

    public ProjectService() {
        this.projectRepository = new ProjectRepositoryImpl();
    }

    public Project createProject(String name, Client client) {
        Project project = new Project(name, client);
        if (projectRepository.createProject(project)) {
            return project;
        }
        return null;
    }
}
