package views;

import entities.Client;
import handlers.ClientHandler;

import java.util.Scanner;

public class ClientManagementMenu {

    private ClientHandler clientHandler = new ClientHandler();
    private Scanner scanner = new Scanner(System.in);
    private int choice;

    public Client showMenu() {
        Client selectedClient = null;

        do {
            System.out.println("================================================================================================");
            System.out.println("=                                 Clients Management Menu                                       =");
            System.out.println("================================================================================================");
            System.out.println("=    1. Add a New Client                                                                        =");
            System.out.println("=    2. find clients by name                                                                       =");
            System.out.println("=    3. find clients by name                                                                     =");
            System.out.println("=    4. Update Client Information                                                               =");
            System.out.println("=    0. Exit                                                                                    =");
            System.out.println("================================================================================================");
            System.out.print("=    Please Enter Your Option: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    selectedClient = clientHandler.addClient();
                    break;
                case 2:
                    selectedClient = clientHandler.findClientByName();
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 0:
                    System.out.println("Exiting the system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        } while (choice != 0 && selectedClient == null);
        return selectedClient;
    }
}
