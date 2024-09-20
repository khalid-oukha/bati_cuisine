package handlers;

import entities.Material;
import entities.Project;
import services.MaterialService;

import java.util.Map;
import java.util.Scanner;

public class MaterialHandler {

    private Scanner scanner = new Scanner(System.in);
    private final MaterialService materialService = new MaterialService();

    public Material createMaterial(Project project, double taxRate) {
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

        return materialService.createMaterial(name, quantity, unitCost, transportCost, qualityCoefficient, project, taxRate);
    }

    public void displayMaterialDetails(Project project) {
        System.out.println("================================================================================================");
        System.out.println("=                                     Material Details                                            ");
        System.out.println("================================================================================================");
        System.out.println("------------------------------------------------------------------------------------------------");

        for (Material material : materialService.getAllMaterials(project)) {
            System.out.println("[" + material.getName() + "]" + "\t\t Quantity : " + material.getQuantity() + "\t\tUnit Cost : " + material.getUnitCost() + "\t\t Transport Cost : " + material.getTransportCost() + "\t\t Quality Coefficient : " + material.getQualityCoefficient() + "\n" +
                    "Total Cost: " + materialService.calculateTotalCost(material) + "\n" +
                    "Total Cost with VAT: " + materialService.calculateTotalCostWithVat(material) + "\n");
            System.out.println("------------------------------------------------------------------------------------------------");

        }
        Map<String, Double> materialsCost = materialService.calculateTotalCostForAllMaterials(project);
        System.out.println("Total Cost for all materials without VAT: " + materialsCost.get("TotalCostWithoutVAT"));
        System.out.println("Total Cost for all materials with VAT: " + materialsCost.get("TotalCostWithVAT"));
        System.out.println("================================================================================================");
    }
}
