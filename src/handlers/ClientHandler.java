package handlers;

import services.ClientService;
import helpers.InputValidator;

import java.util.Scanner;

public class ClientHandler {
    private ClientService clientService;
    private Scanner scanner = new Scanner(System.in);

    public ClientHandler() {
        this.clientService = new ClientService();
    }

    public void addClient() {
        String name;
        String address;
        String phone;

        do {
            System.out.println("  Enter client name (3-50 characters): ");
            name = scanner.nextLine();
            if (!InputValidator.isValidStringLength(name, 3, 50)) {
                System.out.println("Error: Client name must be between 3 and 50 characters.");
            }
        } while (!InputValidator.isValidStringLength(name, 3, 50));

        do {
            System.out.println("  Enter client address (3-100 characters): ");
            address = scanner.nextLine();
            if (!InputValidator.isValidStringLength(address, 3, 100)) {
                System.out.println("Error: Client address must be between 3 and 100 characters.");
            }
        } while (!InputValidator.isValidStringLength(address, 3, 100));

        do {
            System.out.println("  Enter client phone (10 digits): ");
            phone = scanner.nextLine();
            if (!InputValidator.isPhoneNumber(phone)) {
                System.out.println("Error: Client phone must be exactly 10 digits.");
            }
        } while (!InputValidator.isPhoneNumber(phone));

        boolean isProfessional;
        String professionalInput;
        do {
            System.out.println("  Is the client a professional? (Y:YES/N:No): ");
            professionalInput = scanner.nextLine();
            isProfessional = professionalInput.equalsIgnoreCase("Y");
            if (!professionalInput.equalsIgnoreCase("Y") && !professionalInput.equalsIgnoreCase("N")) {
                System.out.println("Error: Please enter 'Y' for Yes or 'N' for No.");
            }
        } while (!professionalInput.equalsIgnoreCase("Y") && !professionalInput.equalsIgnoreCase("N"));

        clientService.addClient(name, address, phone, isProfessional);
    }
}
