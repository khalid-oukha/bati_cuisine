package repositories.Quote;

import config.DatabaseConfig;
import entities.Project;
import entities.Quote;

import java.sql.*;
import java.util.List;

public class QuoteRepositoryImpl implements QuoteRepository {
    private Connection connection;

    public QuoteRepositoryImpl() {
        try {
            this.connection = DatabaseConfig.getInstance().getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error establishing connection: " + e.getMessage());
        }
    }

    @Override
    public boolean createQuote(Quote quote) {
        String sql = "INSERT INTO quotes (estimatedamount, issuedate, validitydate, accepted,project_id) VALUES (?, ?, ?, ?, ?) RETURNING id";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDouble(1, quote.getProject().getTotalCostWithProfitMargin());
            statement.setDate(2, Date.valueOf(quote.getIssueDate()));
            statement.setDate(3, Date.valueOf(quote.getValidityDate()));
            statement.setBoolean(4, quote.getIsAccepted());
            statement.setInt(5, quote.getProject().getId());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int generatedId = resultSet.getInt("id");
                quote.setId(generatedId);
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error creating quote: " + e.getMessage());
        }
        return false;
    }

    @Override
    public List<Quote> findQuoteByProjectId(Project project) {
        return null;
    }
}
