package repositories.material;

import config.DatabaseConfig;
import entities.Material;
import entities.Project;
import enums.ComponentType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public List<Material> getAll(Project project) {
        List<Material> materials = new ArrayList<>();
        String sql = "SELECT * FROM components INNER JOIN materials ON components.id = materials.id WHERE project_id = ? AND \"componentType\" = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, project.getId());
            statement.setString(2, ComponentType.MATERIAL.toString());

            var result = statement.executeQuery();

            while (result.next()) {
                Material material = new Material(
                        result.getInt("id"),
                        result.getString("name"),
                        ComponentType.valueOf(result.getString("componentType")),
                        result.getDouble("vatrate"),
                        project,
                        result.getDouble("unitcost"),
                        result.getDouble("quantity"),
                        result.getDouble("transportcost"),
                        result.getDouble("qualitycoefficient")
                );
                materials.add(material);
            }
        } catch (SQLException e) {
            System.out.println("Error getting materials: " + e.getMessage());
        }
        return materials;
    }

}
