package handlers;

import entities.Client;
import helpers.InputValidator;
import services.ClientService;

import java.util.List;
import java.util.Scanner;

public class ClientHandler {
    private ClientService clientService;
    private Scanner scanner = new Scanner(System.in);

    public ClientHandler() {
        this.clientService = new ClientService();
    }

    public Client addClient() {
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

        Client client = clientService.addClient(name, address, phone, isProfessional);

        if (client != null) {
            System.out.println("Client added successfully.");
            return client;
        } else {
            System.out.println("Error: Could not add client.");
        }
        return null;
    }

    public Client findClientByName() {
        System.out.println("  Enter client name (3-50 characters): ");
        String name = scanner.nextLine();

        List<Client> clients = clientService.findClientByName(name);

        if (clients.isEmpty()) {
            System.out.println("No clients found with the name: " + name);
            return null;
        }

        for (Client client : clients) {
            System.out.println("_____________________________________________________________________________________________");
            System.out.println("    Client ID: " + client.getId());
            System.out.println("    Client Name: " + client.getName());
            System.out.println("    Client Address: " + client.getAddress());
            System.out.println("    Client Phone: " + client.getPhone());
            System.out.println("    Is Professional: " + (client.getIsProfessional() ? "Yes" : "No"));
            System.out.println("_____________________________________________________________________________________________");
        }

        System.out.println("  Choose a client by ID to continue or enter 0 to cancel: ");
        int choice = scanner.nextInt();

        if (choice != 0) {
            Client selectedClient = clients.stream().filter(client -> client.getId() == choice).findFirst().orElse(null);
            if (selectedClient != null) {
                System.out.println("Selected client: " + selectedClient.getName());
                return selectedClient;
            } else {
                System.out.println("Invalid client ID.");
            }
        }
        return null;
    }

    public void deleteClient() {
        System.out.println("  Enter client ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean deleted = clientService.deleteClient(id);
        if (deleted) {
            System.out.println("Client deleted successfully.");
        } else {
            System.out.println("Error: Could not delete client.");
        }
    }

    public Client update() {
        System.out.println("  Enter client ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Client client = clientService.findClientById(id);
        if (client == null) {
            System.out.println("Error: Client not found.");
            return null;
        }

        System.out.println("  Enter new client name (3-50 characters): ");
        String name = scanner.nextLine();
        if (InputValidator.isValidStringLength(name, 3, 50)) {
            client.setName(name);
        } else {
            System.out.println("Error: Client name must be between 3 and 50 characters.");
            return null;
        }

        System.out.println("  Enter new client address (3-100 characters): ");
        String address = scanner.nextLine();
        if (InputValidator.isValidStringLength(address, 3, 100)) {
            client.setAddress(address);
        } else {
            System.out.println("Error: Client address must be between 3 and 100 characters.");
            return null;
        }

        System.out.println("  Enter new client phone (10 digits): ");
        String phone = scanner.nextLine();
        if (InputValidator.isPhoneNumber(phone)) {
            client.setPhone(phone);
        } else {
            System.out.println("Error: Client phone must be exactly 10 digits.");
            return null;
        }

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

        client.setIsProfessional(isProfessional);

        boolean updated = clientService.updateClient(client);
        if (updated) {
            System.out.println("Client updated successfully.");
            return client;
        } else {
            System.out.println("Error: Could not update client.");
        }
        return null;
    }

    public Client findClientById() {
        System.out.println("  Enter client ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Client client = clientService.findClientById(id);
        if (client != null) {
            System.out.println("Client found successfully.");
            System.out.println(client);

            System.out.println("Do you want to continue? (Y:YES/N:No): ");
            String continueInput = scanner.nextLine();
            if (continueInput.equalsIgnoreCase("Y")) {
                return client;
            }
        }
        return null;
    }
}
