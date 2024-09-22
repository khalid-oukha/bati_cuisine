package handlers;

import entities.Labor;
import entities.Project;
import services.LaborService;

import java.util.Map;
import java.util.Scanner;

public class LaborHandler {
    private Scanner scanner = new Scanner(System.in);
    private final LaborService laborService = new LaborService();

    public Labor addLabor(Project project, double taxRate) {
        System.out.println("================================================================================================");
        System.out.println("Enter labor name: ");
        String name = scanner.nextLine();
        System.out.println("Enter labor hourly rate (? DH per 1h): ");
        double hourlyRate = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Enter labor working hours : ");
        double workingHours = scanner.nextDouble();
        System.out.println("Enter labor worker productivity : ");
        double workerProductivity = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("================================================================================================");

        return laborService.createLabor(name, hourlyRate, workingHours, workerProductivity, project, taxRate);
    }

    public void displayLaborDetails(Project project) {
        for (Labor labor : laborService.getAllLabors(project)) {
            System.out.println("[" + labor.getName() + "]" + "\t\t Hourly Rate : " + labor.getHourlyRate() + "\t\t Working Hours : " + labor.getWorkingHours() + "\t\t Worker Productivity : " + labor.getWorkerProductivity() + "\n" +
                    "Total Cost: " + laborService.calculateLaborCost(labor) + "\n" +
                    "Total Cost with VAT: " + laborService.calculateLaborCost(labor) * (1 + labor.getVatRate()) + "\n");
            System.out.println("------------------------------------------------------------------------------------------------");
        }

        Map<String, Double> laborCosts = laborService.calculateAllLaborsCost(project);

        System.out.println("Total Cost for all labors without VAT: " + laborCosts.get("TotalCostWithoutVAT"));
        System.out.println("Total Cost for all labors with VAT: " + laborCosts.get("TotalCostWithVAT"));

    }

}
