package views;

import entities.Project;
import handlers.*;

import java.util.Optional;
import java.util.Scanner;

public class ProjectManagmentMenu {
    private final Scanner scanner = new Scanner(System.in);
    private final ProjectHandler projectHandler;
    private final MaterialHandler materialHandler = new MaterialHandler();
    private final LaborHandler laborHandler = new LaborHandler();
    ComponentHandler componentHandler = new ComponentHandler();
    private QuoteHandler quoteHandler = new QuoteHandler();

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
            System.out.println("=    6. Add labor for project                                                                    ");
            System.out.println("=    7. Update labors in project                                                                 ");
            System.out.println("=    8. Delete labors in project                                                                 ");
            System.out.println("=    9. View All qoutes for this project                                                         ");
            System.out.println("=    10. Accept or refuse quote for project                                                      ");
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
                    materialHandler.deleteMaterial(project);
                    break;
                case 5:
                    laborHandler.displayLaborDetails(project);
                    break;
                case 6:
                    laborHandler.addLabor(project, vatRate);
                    break;
                case 7:
                    laborHandler.updateLabor(project);
                    break;
                case 8:
                    laborHandler.deleteLabor(project);
                    break;
                case 9:
                    quoteHandler.getAllQuotes(project);
                    break;
                case 10:
                    quoteHandler.acceptQuote(project);
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
        Optional<Project> optionalProject = projectHandler.getProjectById(projectId);

        if (optionalProject.isPresent()) {
            return optionalProject.get();
        }
        System.out.println("Project not found with ID: " + projectId);
        return null;
    }
}
