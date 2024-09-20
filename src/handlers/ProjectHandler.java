package handlers;

import entities.Client;
import entities.Project;
import services.ProjectService;

import java.util.Scanner;

public class ProjectHandler {
    private Scanner scanner = new Scanner(System.in);
    private final ProjectService projectService;

    public ProjectHandler() {
        this.projectService = new ProjectService();
    }

    public Project createProject(Client client) {
        System.out.println("Enter project name: ");
        String projectName = scanner.next();
        System.out.println("client : " + client.getId());
//        System.out.println("Enter surface area(/m2) : ");
//        double surfaceArea = scanner.nextDouble();
        return projectService.createProject(projectName, client);
    }

    public void displayProjectByName() {
        System.out.println("Enter project name: ");
        String projectName = scanner.next();
        Project project = projectService.getProjectByName(projectName);
        if (project != null) {
            System.out.println(project.toString());

        } else {
            System.out.println("Project not found");
        }
    }

}
