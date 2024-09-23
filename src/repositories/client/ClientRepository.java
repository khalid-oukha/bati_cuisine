package repositories.client;

import entities.Client;

import java.util.List;
import java.util.Optional;

public interface ClientRepository {
    boolean create(Client client);

    List<Client> findByName(String name);

    Optional<Client> findById(int id);

    boolean Delete(Client client);

    Client update(Client client);
}
