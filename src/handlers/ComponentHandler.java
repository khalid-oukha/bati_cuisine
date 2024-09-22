package handlers;

import entities.Component;
import entities.Project;

import java.util.List;
import java.util.Scanner;

public class ComponentHandler {

    public double getVatRateFromComponent(Project project) {
        List<Component> components = project.getComponents();
        Scanner scanner = new Scanner(System.in);

        if (!components.isEmpty()) {
            return components.get(0).getVatRate();
        } else {
            System.out.println("No components found in the project.");
            System.out.print("Do you want to apply a VAT rate for the project? (y/n): ");
            String userResponse = scanner.nextLine();

            if (userResponse.equalsIgnoreCase("y")) {
                System.out.print("Please enter the VAT rate percentage (e.g., 15 for 15%): ");
                double vatRate = scanner.nextDouble();
                scanner.nextLine();
                return vatRate / 100;
            } else {
                System.out.println("No VAT rate will be applied. Defaulting to 0.");
                return 0.0;
            }
        }
    }
}
