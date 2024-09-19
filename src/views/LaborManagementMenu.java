package views;

import entities.Labor;
import entities.Project;
import handlers.LaborHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LaborManagementMenu {
    private Scanner scanner = new Scanner(System.in);
    private final LaborHandler laborHandler;

    public LaborManagementMenu() {
        this.laborHandler = new LaborHandler();
    }

    public List<Labor> showMenu(Project project) {
        List<Labor> addedLabors = new ArrayList<>();
        int choice;

        do {
            Labor labor = laborHandler.addLabor(project);

            if (labor != null) {
                System.out.println("Labor : " + labor.getName() + " added to the project." + project.getName());
                addedLabors.add(labor);
            }

            System.out.println("Do you want to add another material? (YES:1 / NO:2): ");
            choice = scanner.nextInt();
            scanner.nextLine();

        } while (choice == 1);

        return addedLabors;
    }
}
