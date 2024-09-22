package services;

import entities.Project;
import entities.Quote;
import repositories.Quote.QuoteRepository;
import repositories.Quote.QuoteRepositoryImpl;

import java.time.LocalDate;

public class QuoteService {
    private final QuoteRepository quoteRepository;
    private final ProjectService projectService = new ProjectService();

    public QuoteService() {
        this.quoteRepository = new QuoteRepositoryImpl();
    }

    public boolean addQuote(LocalDate issueDate, LocalDate validityDate, int projectId) {

        Project project = projectService.getProjectById(projectId);
        System.out.println(project.toString());
        double estimatedAmount = projectService.calculateTotalCostWithProfitMargin(project);

        Quote quote = new Quote(estimatedAmount, issueDate, validityDate, project);
        return quoteRepository.createQuote(quote);
    }
}
