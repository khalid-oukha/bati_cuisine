package repositories.Quote;

import entities.Project;
import entities.Quote;

import java.util.List;

public interface QuoteRepository {

    boolean createQuote(Quote quote);

    List<Quote> findQuoteByProjectId(Project project);

    boolean updateQuoteStatus(Quote quote);
}
