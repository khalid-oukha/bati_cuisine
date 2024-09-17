package handlers;

import entities.Labor;
import entities.Project;
import services.LaborService;

import java.util.Scanner;

public class LaborHandler {
    private Scanner scanner = new Scanner(System.in);
    private final LaborService laborService = new LaborService();

    public Labor addLabor(Project project) {
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

//        return laborService.createLabor(name, hourlyRate, workingHours, workerProductivity, project);
    }
}
