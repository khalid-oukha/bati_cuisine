package handlers;

import entities.Material;
import entities.Project;
import services.MaterialService;

import java.util.Scanner;

public class MaterialHandler {

    private Scanner scanner = new Scanner(System.in);
    private final MaterialService materialService = new MaterialService();

    public Material createMaterial(Project project) {
        System.out.println("================================================================================================");
        System.out.println("    Enter material name: ");
        String name = scanner.nextLine();
        System.out.println("Enter material Quantity (/m2): ");
        double quantity = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Enter material unit cost (DH): ");
        double unitCost = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Enter material transport cost (DH): ");
        double transportCost = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Enter material quality coefficient (5 = standard - 10 best quality): ");
        double qualityCoefficient = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("================================================================================================");

        return materialService.createMaterial(name, quantity, unitCost, transportCost, qualityCoefficient, project);
    }
}
