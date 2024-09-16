package repositories.client;

import config.DatabaseConfig;
import entities.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
    public boolean create(Client client) {
        String sql = "INSERT INTO clients (name, address,phone,isprofessional) VALUES (?, ?,?,?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, client.getName());
            statement.setString(2, client.getAddress());
            statement.setString(3, client.getPhone());
            statement.setBoolean(4, client.getIsProfessional());

            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            System.out.println("Error adding client: " + e.getMessage());
        }
        return false;
    }

    @Override
    public List<Client> findByName(String name) {
        String sql = "SELECT * FROM clients WHERE name = ?";
        List<Client> clients = null;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String clientName = resultSet.getString("name");
                String address = resultSet.getString("address");
                String phone = resultSet.getString("phone");
                boolean isProfessional = resultSet.getBoolean("isprofessional");
                clients.add(new Client(id, clientName, address, phone, isProfessional));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching clients: " + e.getMessage());

        }
        return clients;
    }

}
