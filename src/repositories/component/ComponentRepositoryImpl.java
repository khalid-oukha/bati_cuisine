package repositories.component;

import config.DatabaseConfig;
import entities.Component;
import entities.Project;
import enums.ComponentType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ComponentRepositoryImpl implements ComponentRepository {
    private Connection connection;

    public ComponentRepositoryImpl() {
        try {
            this.connection = DatabaseConfig.getInstance().getConnection();
        } catch (Exception e) {
            System.out.println("Error establishing connection: " + e.getMessage());
        }
    }

    @Override
    public boolean create(Component component) {
        String sql = "INSERT INTO components (name, \"componentType\",vatrate, project_id) VALUES (?, ?, ?,?) RETURNING id";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, component.getName());
            statement.setString(2, component.getComponentType().toString());
            statement.setDouble(3, component.getVatRate());
            statement.setInt(4, component.getProject().getId());

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int generatedId = resultSet.getInt("id");
                component.setId(generatedId);
                return true;
            }

        } catch (Exception e) {
            System.out.println("Error creating component: " + e.getMessage());
        }
        return false;
    }

    @Override
    public Component findById(int id, Project project) {
        String sql = "SELECT * FROM components WHERE id = ?";
        Component component = null;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            var resultSet = statement.executeQuery();
            if (resultSet.next()) {
                component = new Component(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        ComponentType.valueOf(resultSet.getString("componentType")),
                        resultSet.getDouble("vatrate"),
                        project
                );
            }
        } catch (Exception e) {
            System.out.println("Error finding component: " + e.getMessage());
        }
        return component;
    }

    @Override
    public boolean update(Component component) {
        String sql = "UPDATE components SET name = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, component.getName());
            statement.setInt(2, component.getId());

            return statement.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error updating component: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(Component component) {
        String sql = "DELETE FROM components WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, component.getId());
            return statement.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error deleting component: " + e.getMessage());
        }
        return false;
    }
}
