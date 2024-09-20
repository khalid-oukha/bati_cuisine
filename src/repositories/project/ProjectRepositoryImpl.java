package repositories.project;

import config.DatabaseConfig;
import entities.Client;
import entities.Project;
import enums.project_status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProjectRepositoryImpl implements ProjectRepository {
    private Connection connection;

    public ProjectRepositoryImpl() {
        try {
            this.connection = DatabaseConfig.getInstance().getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error establishing connection: " + e.getMessage());
        }
    }

    public boolean createProject(Project project) {
        String sql = "INSERT INTO projects (name, client_id) VALUES (?, ?) RETURNING id";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, project.getName());
            statement.setInt(2, project.getClient().getId());

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int generatedId = resultSet.getInt("id");
                project.setId(generatedId);
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error creating project: " + e.getMessage());
        }
        return false;
    }

    public Project findByProjectId(int id) {
        String sql = "SELECT * FROM projects p INNER JOIN clients c ON p.client_id = c.id WHERE p.id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int generatedId = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int clientId = resultSet.getInt("client_id");
                Client client = new Client(clientId, resultSet.getString("name"), resultSet.getString("email"), resultSet.getString("phone"), resultSet.getBoolean("isprofessional"));
                Project project = new Project(generatedId, name, resultSet.getDouble("profitmargin"), resultSet.getDouble("totalcost"), project_status.valueOf(resultSet.getString("projectstatus")), client);
                return project;
            }
        } catch (SQLException e) {
            System.out.println("Error fetching project: " + e.getMessage());
        }
        return null;
    }

    public Project findByName(String name) {
        String sql = "SELECT * FROM projects p INNER JOIN clients c ON p.client_id = c.id WHERE p.name = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int generatedId = resultSet.getInt("id");
                String projectName = resultSet.getString("name");
                int clientId = resultSet.getInt("client_id");
                Client client = new Client(clientId, resultSet.getString("name"), resultSet.getString("email"), resultSet.getString("phone"), resultSet.getBoolean("isprofessional"));
                Project project = new Project(generatedId, projectName, resultSet.getDouble("profitmargin"), resultSet.getDouble("totalcost"), project_status.valueOf(resultSet.getString("projectstatus")), client);
                return project;
            }
        } catch (SQLException e) {
            System.out.println("Error fetching project: " + e.getMessage());
        }
        return null;
    }
}
