package handlers;

import entities.Project;
import entities.Quote;
import enums.project_status;
import helpers.InputValidator;
import services.QuoteService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class QuoteHandler {
    private final QuoteService quoteService;
    private final Scanner scanner = new Scanner(System.in);

    public QuoteHandler() {
        this.quoteService = new QuoteService();
    }

    public void addQuote() {
        System.out.println("Do you want to Save Quote for project? (Y/N)");
        String answer = scanner.nextLine();
        if (!answer.equalsIgnoreCase("Y")) {
            return;
        }

        System.out.println("Enter the project ID: ");
        int projectId = scanner.nextInt();
        scanner.nextLine();

        LocalDate issueDate = null;
        LocalDate validityDate = null;

        do {
            System.out.println("Enter the issue date (yyyy-mm-dd): ");
            String issueDateInput = scanner.nextLine();
            System.out.println("Enter the validity date (yyyy-mm-dd): ");
            String validityDateInput = scanner.nextLine();

            if (InputValidator.isDate(issueDateInput) && InputValidator.isDate(validityDateInput)) {

                if (InputValidator.dateRange(issueDateInput, validityDateInput)) {
                    issueDate = LocalDate.parse(issueDateInput);
                    validityDate = LocalDate.parse(validityDateInput);
                } else {
                    System.out.println("Invalid date range. The issue date must be today or in the future, and before the validity date.");
                }
            } else {
                System.out.println("Invalid date format. Please try again.");
            }
        } while (issueDate == null || validityDate == null);

        if (quoteService.addQuote(issueDate, validityDate, projectId)) {
            System.out.println("Quote added successfully");
        } else {
            System.out.println("Failed to add quote");
        }
    }

    public void getAllQuotes(Project project) {
        List<Quote> quotes = quoteService.getAllQuotes(project);

        for (Quote quote : quotes) {
            System.out.println(quote.toString());
        }
    }

    public void acceptQuote(Project project) {

        if (project.getStatus().equals(project_status.COMPLETED)) {
            System.out.println("THIS project is already completed");
            return;
        }

        System.out.println("Enter the quote ID: ");
        int quoteId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Do you want to accept a quote? (Y/N)");
        String answer = scanner.nextLine();
        if (!answer.equalsIgnoreCase("Y")) {
            return;
        }

        Quote quote = quoteService.acceptQuote(project, quoteId);

        if (quote != null) {
            System.out.println(" ********************* Project quote accepted successfully ********************* ");
            System.out.println(quote.toString());
        }
        ;

    }
}
