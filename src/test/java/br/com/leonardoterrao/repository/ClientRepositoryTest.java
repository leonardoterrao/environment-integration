package br.com.leonardoterrao.repository;

import br.com.leonardoterrao.model.Client;
import br.com.leonardoterrao.repository.br.com.leonardoterrao.junit.TransactionRule;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ClientRepositoryTest {

    @Rule
    private TransactionRule ruleTransaction = new TransactionRule();

    private ClientRepository clientRepository = ClientRepository.getInstance();

    @Test
    public void findAll() throws Exception {
        List<Client> clients = clientRepository.findAll();
        assertThat(0, is(clients.size()));
    }

    @Test
    public void findByName() throws Exception {
        Client client = clientRepository.find("Leonardo");

        assertThat("Leonardo", is(client.getName()));
        assertThat("leonardoterrao@gmail.com", is(client.getEmail()));
    }

}