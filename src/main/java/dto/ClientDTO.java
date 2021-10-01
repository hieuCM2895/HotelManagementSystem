package dto;

import model.Client;

public class ClientDTO {

    public Client clientDTO(Client client) {

        Client clientDTO = new Client();

        clientDTO.setIdCard(client.getIdCard());
        clientDTO.setEmail(client.getEmail());
        clientDTO.setTell(client.getTell());
        clientDTO.setNote(client.getNote());
        clientDTO.setAddress(client.getAddress());
        clientDTO.setFullName(client.getFullName());
        clientDTO.setAddress("Test");
        return clientDTO;

    }
}
