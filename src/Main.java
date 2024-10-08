import entities.Client;
import entities.Project;
import handlers.MaterialHandler;
import handlers.ProjectHandler;
import handlers.QuoteHandler;
import views.ClientManagementMenu;
import views.LaborManagementMenu;
import views.MaterialManagementMenu;
import views.ProjectManagmentMenu;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ProjectHandler projectHandler = new ProjectHandler();
        MaterialHandler materialHandler = new MaterialHandler();
        QuoteHandler quoteHandler = new QuoteHandler();
        ClientManagementMenu clientManagementMenu = new ClientManagementMenu();
        MaterialManagementMenu materialManagementMenu = new MaterialManagementMenu();
        LaborManagementMenu laborManagementMenu = new LaborManagementMenu();
        ProjectManagmentMenu projectManagmentMenu = new ProjectManagmentMenu();
        int choice;

        do {
            System.out.println("================================================================================================");
            System.out.println("=                                  Project Management Menu                                       ");
            System.out.println("================================================================================================");
            System.out.println("=    1. Create a New Project                                                                    ");
            System.out.println("=    2. Generate Quote  for project                                                              ");
            System.out.println("=    3. Manage Existing Projects                                                                 ");
            System.out.println("=    0. Exit                                                                 ");
            System.out.println("================================================================================================");
            System.out.print("=    Please Enter Your Option: ");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    Client selectedClient = clientManagementMenu.showMenu();
                    Project project = projectHandler.createProject(selectedClient);

                    double taxRate = projectHandler.applyTax();
                    double profitMargin = projectHandler.applyProfitMargin();

                    project.setProfitMargin(profitMargin);

                    materialManagementMenu.showMenu(project, taxRate);
                    laborManagementMenu.showMenu(project, taxRate);


                    projectHandler.updateProject(project);
                    materialHandler.displayMaterialDetails(project);
                    laborManagementMenu.displayLabors(project);


                    System.out.println(project.toString());

                    break;
                case 2:
                    quoteHandler.addQuote();
                    break;
                case 3:
                    Project projectToManage = projectManagmentMenu.selectProjectById();
                    if (projectToManage != null) {
                        projectManagmentMenu.showMenu(projectToManage);

                    }
                    break;
                case 0:
                    System.out.println("Exiting the system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        } while (choice != 0);

    }
}