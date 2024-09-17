package views;

import entities.Material;
import entities.Project;
import handlers.MaterialHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MaterialManagementMenu {
    private Scanner scanner = new Scanner(System.in);
    private int choice;
    private MaterialHandler materialHandler = new MaterialHandler();


    public List<Material> showMenu(Project project) {
        List<Material> selectedMaterials = new ArrayList<>();

        do {
            materialHandler.createMaterial(project);
            System.out.println("Do you want to add another material? (Y:YES/N:No): ");
            String addAnotherMaterial = scanner.nextLine();

            if (addAnotherMaterial.equalsIgnoreCase("N")) {
                choice = 0;
            }

        } while (choice != 0);
        return selectedMaterials;
    }
}
