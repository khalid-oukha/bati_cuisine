package views;

import entities.Project;
import handlers.ComponentHandler;
import handlers.MaterialHandler;
import handlers.ProjectHandler;

import java.util.Scanner;

public class ProjectManagmentMenu {
    private final Scanner scanner = new Scanner(System.in);
    private final ProjectHandler projectHandler;
    private final MaterialHandler materialHandler = new MaterialHandler();
    ComponentHandler componentHandler = new ComponentHandler();

    public ProjectManagmentMenu() {
        this.projectHandler = new ProjectHandler();
    }

    public void showMenu(Project project) {
        int choice;
        double vatRate = componentHandler.getVatRateFromComponent(project);

        do {
            System.out.println("\n\n================================================================================================");
            System.out.println("=                       Project : " + project.getName() + "\t Client : " + project.getClient().getName() + "           ");
            System.out.println("================================================================================================");
            System.out.println("=    1. View material in project                                                                 ");
            System.out.println("=    2. Add material to project                                                                  ");
            System.out.println("=    3. Update material in project                                                               ");
            System.out.println("=    4. Delete material from project                                                             ");
            System.out.println("=    5. View labors in project                                                                   ");
            System.out.println("=    6. Delete labor from project                                                                ");
            System.out.println("=    7. Update labors in project                                                                 ");
            System.out.println("=    0. Exit                                                                                     ");
            System.out.println("================================================================================================");
            System.out.print("=    Please Enter Your Option: ");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    materialHandler.displayMaterialDetails(project);
                    break;
                case 2:
                    materialHandler.createMaterial(project, vatRate);
                    projectHandler.updateProject(project);
                    break;
                case 3:
                    materialHandler.updateMaterial(project);
                    projectHandler.updateProject(project);
                    break;
                case 4:
                    // Delete material from project
                    break;
                case 5:
                    // View labors in project
                    break;
                case 6:
                    // Delete labor from project
                    break;
                case 7:
                    // Update labor in project
                    break;
                case 0:
                    System.out.println("Exiting project management.");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        } while (choice != 0);
    }

    public Project selectProjectById() {
        System.out.print("Enter the project ID you want to manage: ");
        int projectId = scanner.nextInt();
        scanner.nextLine();
        Project project = projectHandler.getProjectById(projectId);

        if (project == null) {
            System.out.println("Project not found with ID: " + projectId);
        }

        return project;
    }
}
