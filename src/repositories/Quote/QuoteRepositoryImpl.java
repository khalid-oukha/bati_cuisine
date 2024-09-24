package repositories.Quote;

import config.DatabaseConfig;
import entities.Project;
import entities.Quote;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
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
        String sql = "SELECT * FROM quotes WHERE project_id = ?";
        List<Quote> quotes = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, project.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                double estimatedAmount = resultSet.getDouble("estimatedamount");
                java.sql.Date issueDateSql = resultSet.getDate("issuedate");
                java.sql.Date validityDateSql = resultSet.getDate("validitydate");
                int id = resultSet.getInt("id");

                LocalDate issueDate = issueDateSql.toLocalDate();
                LocalDate validityDate = validityDateSql.toLocalDate();

                Quote quote = new Quote(id, estimatedAmount, issueDate, validityDate, project);
                quotes.add(quote);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching quotes from database: " + e.getMessage());
        }
        return quotes;
    }


    @Override
    public boolean updateQuoteStatus(Quote quote) {
        String sql = "UPDATE quotes SET accepted = true WHERE id = ? AND project_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, quote.getId());
            statement.setInt(2, quote.getProject().getId());

            if (statement.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error updating quote: " + e.getMessage());
        }
        return false;
    }

}
