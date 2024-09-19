package repositories.Labor;

import config.DatabaseConfig;
import entities.Labor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
