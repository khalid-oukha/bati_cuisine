package repositories.project;

import config.DatabaseConfig;
import entities.Client;
import entities.Component;
import entities.Project;
import enums.ComponentType;
import enums.project_status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<Project> findByProjectId(int id) {
        String sqlProject = "SELECT p.id AS project_id, p.name AS project_name, p.profitmargin, p.totalcost, p.projectstatus, " +
                "c.id AS client_id, c.name AS client_name, c.address, c.phone, c.isprofessional " +
                "FROM projects p " +
                "INNER JOIN clients c ON p.client_id = c.id " +
                "WHERE p.id = ?";

        String sqlComponents = "SELECT id, name, \"componentType\", vatrate FROM components WHERE project_id = ?";

        try (PreparedStatement statementProject = connection.prepareStatement(sqlProject);
             PreparedStatement statementComponents = connection.prepareStatement(sqlComponents)) {

            statementProject.setInt(1, id);
            ResultSet resultSetProject = statementProject.executeQuery();

            if (resultSetProject.next()) {
                int projectId = resultSetProject.getInt("project_id");
                String projectName = resultSetProject.getString("project_name");
                double profitMargin = resultSetProject.getDouble("profitmargin");
                double totalCost = resultSetProject.getDouble("totalcost");
                project_status projectStatus = project_status.valueOf(resultSetProject.getString("projectstatus"));

                int clientId = resultSetProject.getInt("client_id");
                String clientName = resultSetProject.getString("client_name");
                String address = resultSetProject.getString("address");
                String phone = resultSetProject.getString("phone");
                boolean isProfessional = resultSetProject.getBoolean("isprofessional");

                Client client = new Client(clientId, clientName, address, phone, isProfessional);

                Project project = new Project(projectId, projectName, profitMargin, totalCost, projectStatus, client);

                statementComponents.setInt(1, projectId);
                ResultSet resultSetComponents = statementComponents.executeQuery();

                List<Component> components = new ArrayList<>();

                while (resultSetComponents.next()) {
                    int componentId = resultSetComponents.getInt("id");
                    String componentName = resultSetComponents.getString("name");
                    ComponentType componentType = ComponentType.valueOf(resultSetComponents.getString("componenttype"));
                    double vatRate = resultSetComponents.getDouble("vatrate");

                    Component component = new Component(componentId, componentName, componentType, vatRate, project);
                    components.add(component);
                }

                project.setComponents(components);

                return Optional.of(project);
            }

        } catch (SQLException e) {
            System.out.println("Error fetching project: " + e.getMessage());
        }
        return Optional.empty();
    }

    public Project findByName(String name) {
        String sql = "SELECT p.id AS project_id, p.name AS project_name, p.profitmargin, p.totalcost, p.projectstatus, " +
                "c.id AS client_id, c.name AS client_name, c.address, c.phone, c.isprofessional " +
                "FROM projects p " +
                "INNER JOIN clients c ON p.client_id = c.id " +
                "WHERE p.name = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int projectId = resultSet.getInt("project_id");
                String projectName = resultSet.getString("project_name");
                double profitMargin = resultSet.getDouble("profitmargin");
                double totalCost = resultSet.getDouble("totalcost");
                project_status projectStatus = project_status.valueOf(resultSet.getString("projectstatus"));

                int clientId = resultSet.getInt("client_id");
                String clientName = resultSet.getString("client_name");
                String address = resultSet.getString("address");
                String phone = resultSet.getString("phone");
                boolean isProfessional = resultSet.getBoolean("isprofessional");

                Client client = new Client(clientId, clientName, address, phone, isProfessional);
                return new Project(projectId, projectName, profitMargin, totalCost, projectStatus, client);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching project: " + e.getMessage());
        }
        return null;
    }


    @Override
    public Project updateProject(Project project) {
        String sql = "UPDATE projects SET name = ?, profitmargin = ?, totalcost = ?, client_id = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, project.getName());
            statement.setDouble(2, project.getProfitMargin());
            statement.setDouble(3, project.getTotalCost());
            statement.setInt(4, project.getClient().getId());
            statement.setInt(5, project.getId());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                return project;
            }
        } catch (SQLException e) {
            System.out.println("Error updating project: " + e.getMessage());
        }
        return null;
    }
}
