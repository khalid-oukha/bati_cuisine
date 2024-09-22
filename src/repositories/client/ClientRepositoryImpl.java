package repositories.client;

import config.DatabaseConfig;
import entities.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
        String sql = "INSERT INTO clients (name, address,phone,isprofessional) VALUES (?, ?,?,?) returning id";
        ;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, client.getName());
            statement.setString(2, client.getAddress());
            statement.setString(3, client.getPhone());
            statement.setBoolean(4, client.getIsProfessional());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int generatedId = resultSet.getInt("id");
                client.setId(generatedId);
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error adding client: " + e.getMessage());
        }
        return false;
    }

    @Override
    public List<Client> findByName(String name) {
        String sql = "SELECT * FROM clients WHERE name like ?";
        List<Client> clients = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, "%" + name + "%");
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

    @Override
    public boolean Delete(Client client) {
        String sql = "DELETE FROM clients WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, client.getId());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error deleting client: " + e.getMessage());
        }
        return false;
    }

    @Override
    public Client findById(int id) {
        String sql = "SELECT * FROM clients WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                String phone = resultSet.getString("phone");
                boolean isProfessional = resultSet.getBoolean("isprofessional");
                return new Client(id, name, address, phone, isProfessional);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching client: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Client update(Client client) {
        String sql = "UPDATE clients SET name = ?, address = ?, phone = ?, isprofessional = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, client.getName());
            statement.setString(2, client.getAddress());
            statement.setString(3, client.getPhone());
            statement.setBoolean(4, client.getIsProfessional());
            statement.setInt(5, client.getId());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                return client;
            }
        } catch (SQLException e) {
            System.out.println("Error updating client: " + e.getMessage());
        }
        return null;
    }
}
