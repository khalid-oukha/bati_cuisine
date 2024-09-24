package handlers;

import entities.Client;
import entities.Project;
import services.ProjectService;

import java.util.Optional;
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

    public double applyTax() {
        System.out.println("Do you want to apply tax for the project? (YES:1 / NO:2): ");
        int taxChoice = scanner.nextInt();
        scanner.nextLine();
        double taxRate = 0;


        if (taxChoice == 1) {
            System.out.println("Enter the tax rate (20%) : ");
            taxRate = scanner.nextDouble() / 100;
        }
        return taxRate;
    }

    public double applyProfitMargin() {
        double profitMargin = 0;
        System.out.println("Do you want to add profit margin tax for the project? (YES:1 / NO:2): ");
        int profitChoice = scanner.nextInt();
        scanner.nextLine();

        if (profitChoice == 1) {
            System.out.println("Enter the profit margin (20%) : ");
            profitMargin = scanner.nextDouble();
        }
        return profitMargin;
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

    public Project updateProject(Project project) {
        return projectService.updateProject(project);
    }

    public Optional<Project> getProjectById(int id) {
        return projectService.getProjectById(id);
    }
}
