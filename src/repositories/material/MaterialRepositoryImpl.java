package repositories.material;

import config.DatabaseConfig;
import entities.Material;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MaterialRepositoryImpl implements MaterialRepository {

    private Connection connection;

    public MaterialRepositoryImpl() {
        try {
            this.connection = DatabaseConfig.getInstance().getConnection();
        } catch (Exception e) {
            System.out.println("Error establishing connection: " + e.getMessage());
        }
    }

    @Override
    public boolean create(Material material) {
        String sql = "Insert into materials (id, unitcost, quantity,transportcost,qualitycoefficient) values (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDouble(1, material.getId());
            statement.setDouble(2, material.getUnitCost());
            statement.setDouble(3, material.getQuantity());
            statement.setDouble(4, material.getTransportCost());
            statement.setDouble(5, material.getQualityCoefficient());

            if (statement.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("error creating material : " + e.getMessage());
        }
        return false;
    }

}
