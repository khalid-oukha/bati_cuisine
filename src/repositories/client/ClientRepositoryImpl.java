package repositories.client;

import config.DatabaseConfig;
import entities.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClientRepositoryImpl implements ClientRepository {

    private Connection connection;

    public ClientRepositoryImpl() {
        try {
            this.connection = DatabaseConfig.getInstance().getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error establishing connection: " + e.getMessage());
        }
    }
    @Override
    public void create(Client client) {
        String sql = "INSERT INTO clients (name, address,phone,isprofessional) VALUES (?, ?,?,?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, client.getName());
            statement.setString(2, client.getAddress());
            statement.setString(3, client.getPhone());
            statement.setBoolean(4, client.getIsProfessional());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error adding client: " + e.getMessage());
        }
    }
}
