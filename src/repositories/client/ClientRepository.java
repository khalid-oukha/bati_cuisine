package repositories.client;

import entities.Client;

import java.util.List;

public interface ClientRepository {
    boolean create(Client client);

    List<Client> findByName(String name);

    Client findById(int id);

    boolean Delete(Client client);

    Client update(Client client);
}
