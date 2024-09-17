package repositories.project;

import config.DatabaseConfig;
import entities.Project;

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

}
