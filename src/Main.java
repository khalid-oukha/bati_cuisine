import entities.Client;
import entities.Material;
import entities.Project;
import handlers.MaterialHandler;
import handlers.ProjectHandler;
import views.ClientManagementMenu;
import views.LaborManagementMenu;
import views.MaterialManagementMenu;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ProjectHandler projectHandler = new ProjectHandler();
        MaterialHandler materialHandler = new MaterialHandler();
        ClientManagementMenu clientManagementMenu = new ClientManagementMenu();
        MaterialManagementMenu materialManagementMenu = new MaterialManagementMenu();
        LaborManagementMenu laborManagementMenu = new LaborManagementMenu();
        int choice;

        do {
            System.out.println("================================================================================================");
            System.out.println("=                                  Project Management Menu                                       ");
            System.out.println("================================================================================================");
            System.out.println("=    1. Create a New Project                                                                    ");
            System.out.println("=    2. display project by name                                     ");
            System.out.println("=    3. Calculate Project Cost                                                                 ");
            System.out.println("=    0. Exit                                                                 ");
            System.out.println("================================================================================================");
            System.out.print("=    Please Enter Your Option: ");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    Client selectedClient = clientManagementMenu.showMenu();
                    Project project = projectHandler.createProject(selectedClient);

                    System.out.println("Do you want to apply tax for the project? (YES:1 / NO:2): ");
                    int taxChoice = scanner.nextInt();
                    scanner.nextLine();
                    double taxRate = 0;
                    if (taxChoice == 1) {
                        System.out.println("Enter the tax rate (20%) : ");
                        taxRate = scanner.nextDouble() / 100;
                    }


                    List<Material> materials = materialManagementMenu.showMenu(project, taxRate);
                    laborManagementMenu.showMenu(project, taxRate);

                    double profitMargin = 0;
                    System.out.println("Do you want to add profit margin tax for the project? (YES:1 / NO:2): ");
                    int profitChoice = scanner.nextInt();
                    scanner.nextLine();

                    if (profitChoice == 1) {
                        System.out.println("Enter the profit margin (20%) : ");
                        profitMargin = scanner.nextDouble();
                    }

                    System.out.println(project.toString());

                    materialHandler.displayMaterialDetails(project);
                    laborManagementMenu.displayLabors(project);
                    break;
                case 2:

                    break;
                case 3:
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