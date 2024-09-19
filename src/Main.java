import entities.Client;
import entities.Project;
import handlers.ProjectHandler;
import views.ClientManagementMenu;
import views.MaterialManagementMenu;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ProjectHandler projectHandler = new ProjectHandler();
        ClientManagementMenu clientManagementMenu = new ClientManagementMenu();
        MaterialManagementMenu materialManagementMenu = new MaterialManagementMenu();
        int choice;

        do {
            System.out.println("================================================================================================");
            System.out.println("=                                  Project Management Menu                                       ");
            System.out.println("================================================================================================");
            System.out.println("=    1. Create a New Project                                                                    ");
            System.out.println("=    2. Show All Projects                                                                       ");
            System.out.println("=    3. Calculate Project Cost                                                                 ");
            System.out.println("=    0. Exit                                                                 ");
            System.out.println("================================================================================================");
            System.out.print("=    Please Enter Your Option: ");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    Client selectedClient = clientManagementMenu.showMenu();
                    Project project = projectHandler.createProject(selectedClient);

                    System.out.println("project name : " + project.getName());
                    materialManagementMenu.showMenu(project);
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