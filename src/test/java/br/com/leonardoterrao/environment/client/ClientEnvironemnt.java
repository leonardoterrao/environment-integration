package br.com.leonardoterrao.environment.client;

import br.com.lemao.environment.Environment;
import br.com.lemao.environment.annotation.GivenEnvironment;
import br.com.leonardoterrao.environment.product.ProductEnvironment;
import br.com.leonardoterrao.model.Client;
import br.com.leonardoterrao.model.Gender;
import br.com.leonardoterrao.repository.ClientRepository;

public class ClientEnvironemnt extends Environment {

    private ClientRepository clientRepository = ClientRepository.getInstance();

    public static final String CLIENTE_LEONARDO = "Leonardo";

    @Override
    @GivenEnvironment(ProductEnvironment.class)
    public void run() {
        clientRepository.persist(buildClient(CLIENTE_LEONARDO, "leonardoterrao@gmail.com", Gender.MALE));
        clientRepository.persist(buildClient("Shiba Safadão", "shiba_safadao_show_2013@bol.com", Gender.MALE));
    }

    public void thirdPerson() {
        clientRepository.persist(buildClient("Jurislvalda", "jurislvalda@hotmail.com", Gender.FEMALE));
    }

    private Client buildClient(String name, String email, Gender gender) {
        return Client.builder().name(name).email(email).gender(gender).build();
    }

}
