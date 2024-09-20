package views;

import entities.Material;
import entities.Project;
import handlers.MaterialHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MaterialManagementMenu {
    private Scanner scanner = new Scanner(System.in);
    private MaterialHandler materialHandler = new MaterialHandler();

    public List<Material> showMenu(Project project, double taxRate) {
        List<Material> selectedMaterials = new ArrayList<>();
        int choice;

        do {
            Material material = materialHandler.createMaterial(project, taxRate);

            if (material != null) {
                System.out.println("Material : " + material.getName() + " added to the project." + project.getName());
                selectedMaterials.add(material);
            }

            System.out.println("Do you want to add another material? (YES:1 / NO:2): ");
            choice = scanner.nextInt();
            scanner.nextLine();

        } while (choice == 1);

        return selectedMaterials;
    }

    public void displayMaterialDetails(List<Material> materials) {
        materialHandler.displayMaterialDetails(materials);
    }
}
