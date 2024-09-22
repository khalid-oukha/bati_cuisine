package services;

import entities.Client;
import repositories.client.ClientRepository;
import repositories.client.ClientRepositoryImpl;

import java.util.List;

public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService() {
        this.clientRepository = new ClientRepositoryImpl();
    }

    public Client addClient(String name, String address, String phone, boolean isProfessional) {
        Client client = new Client(name, address, phone, isProfessional);
        if (clientRepository.create(client)) {
            return client;
        }
        return null;
    }

    public Client findClientById(int id) {
        return clientRepository.findById(id);
    }

    public List<Client> findClientByName(String name) {
        return clientRepository.findByName(name);
    }

    public boolean deleteClient(int id) {
        Client client = clientRepository.findById(id);
        if (client != null) {
            return clientRepository.Delete(client);
        }
        return false;
    }

    public Boolean updateClient(Client client) {
        return clientRepository.update(client) != null;
    }

}
