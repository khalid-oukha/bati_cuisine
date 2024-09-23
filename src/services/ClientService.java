package services;

import entities.Client;
import repositories.client.ClientRepository;
import repositories.client.ClientRepositoryImpl;

import java.util.List;
import java.util.Optional;

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

    public Optional<Client> findClientById(int id) {
        return clientRepository.findById(id);
    }

    public List<Client> findClientByName(String name) {
        return clientRepository.findByName(name);
    }

    public boolean deleteClient(int id) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isPresent()) {
            Client client = optionalClient.get();
            return clientRepository.Delete(client);
        }
        return false;
    }

    public Boolean updateClient(Client client) {
        return clientRepository.update(client) != null;
    }

}
