package services;

import entities.Client;
import repositories.client.ClientRepository;
import repositories.client.ClientRepositoryImpl;

public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(){
        this.clientRepository = new ClientRepositoryImpl();
    }

    public void addClient(String name,String address,String phone,boolean isProfessional){
        Client client = new Client(name,address,phone,isProfessional);
        clientRepository.create(client);
    }
}
