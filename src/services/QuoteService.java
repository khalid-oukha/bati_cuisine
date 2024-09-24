package services;

import entities.Project;
import entities.Quote;
import enums.project_status;
import repositories.Quote.QuoteRepository;
import repositories.Quote.QuoteRepositoryImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class QuoteService {
    private final QuoteRepository quoteRepository;
    private final ProjectService projectService = new ProjectService();

    public QuoteService() {
        this.quoteRepository = new QuoteRepositoryImpl();
    }

    public boolean addQuote(LocalDate issueDate, LocalDate validityDate, int projectId) {
        Optional<Project> optionalProject = projectService.getProjectById(projectId);

        if (optionalProject.isPresent()) {
            Project project = optionalProject.get();
            System.out.println(project.toString());

            double estimatedAmount = projectService.calculateTotalCostWithProfitMargin(project);
            Quote quote = new Quote(estimatedAmount, issueDate, validityDate, project);

            return quoteRepository.createQuote(quote);
        } else {
            System.out.println("Project not found with ID: " + projectId);
            return false;
        }
    }

    public List<Quote> getAllQuotes(Project project) {
        List<Quote> allQuotes = quoteRepository.findQuoteByProjectId(project);
        return allQuotes.stream().filter(quote -> quote.getValidityDate().isAfter(LocalDate.now()))
                .collect(Collectors.toList());
    }

    public Optional<Quote> getAvailableQuotesById(Project project, int quoteId) {
        List<Quote> allQuotes = this.getAllQuotes(project);
        return allQuotes.stream().filter(q -> q.getId() == quoteId).findFirst();
    }

    public Quote acceptQuote(Project project, int quoteId) {
        Optional<Quote> optionalQuote = getAvailableQuotesById(project, quoteId);
        if (optionalQuote.isPresent()) {
            Quote quote = optionalQuote.get();
            if (quoteRepository.updateQuoteStatus(quote)) {
                quote.setAccepted(true);
                project.setTotalCost(quote.getEstimatedAmount());
                project.setStatus(project_status.COMPLETED);

                projectService.updateProject(project);
            }
            return quote;
        }
        return null;
    }
}
