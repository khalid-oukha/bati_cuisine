package repositories.Labor;

import config.DatabaseConfig;
import entities.Labor;
import entities.Project;
import enums.ComponentType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LaborRepositoryImpl implements LaborRepository {
    private Connection connection;

    public LaborRepositoryImpl() {
        try {
            this.connection = DatabaseConfig.getInstance().getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error establishing connection: " + e.getMessage());
        }
    }

    @Override
    public boolean create(Labor labor) {
        String sql = "INSERT INTO labors (id, hourlyrate, workinghours, workerproductivity) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, labor.getId());
            statement.setDouble(2, labor.getHourlyRate());
            statement.setDouble(3, labor.getWorkingHours());
            statement.setDouble(4, labor.getWorkerProductivity());

            if (statement.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error creating labor: " + e.getMessage());
        }
        return false;
    }

    public List<Labor> getAll(Project project) {
        List<Labor> labors = new ArrayList<>();
        String sql = "SELECT * FROM components INNER JOIN labors ON components.id = labors.id WHERE project_id = ? AND \"componentType\" = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, project.getId());
            statement.setString(2, ComponentType.LABOUR.toString());

            var result = statement.executeQuery();

            while (result.next()) {
                Labor labor = new Labor(
                        result.getInt("id"),
                        result.getString("name"),
                        ComponentType.valueOf(result.getString("componentType")),
                        result.getDouble("vatrate"),
                        result.getDouble("hourlyrate"),
                        result.getDouble("workinghours"),
                        result.getDouble("workerproductivity"),
                        project
                );
                labors.add(labor);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching labors: " + e.getMessage());
        }
        return labors;
    }
}
