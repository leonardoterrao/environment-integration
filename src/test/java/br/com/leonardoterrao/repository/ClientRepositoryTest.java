package br.com.leonardoterrao.repository;

import br.com.lemao.environment.annotation.GivenEnvironment;
import br.com.lemao.environment.annotation.IgnoreEnvironment;
import br.com.leonardoterrao.model.Client;
import br.com.leonardoterrao.environment.client.ClientEnvironemnt;
import br.com.leonardoterrao.model.Gender;
import br.com.leonardoterrao.repository.br.com.leonardoterrao.junit.TransactionRule;
import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@GivenEnvironment(ClientEnvironemnt.class)
public class ClientRepositoryTest {

    @Rule
    public TransactionRule ruleTransaction = new TransactionRule();

    private ClientRepository clientRepository = ClientRepository.getInstance();

    @Test
    @IgnoreEnvironment
    public void findAllWithoutEnvironment() throws Exception {
        List<Client> clients = clientRepository.findAll();
        assertThat(clients, is(Matchers.empty()));
    }

    @Test
    public void findAllWihEnvironment() throws Exception {
        List<Client> clients = clientRepository.findAll();
        assertThat(clients.size(), is(2));
    }

    @Test
    public void findByName() throws Exception {
        Client leonardo = clientRepository.find("Leonardo");

        assertThat(leonardo.getName(), is("Leonardo"));
        assertThat(leonardo.getEmail(), is("leonardoterrao@gmail.com"));

        Client jurislvalda = clientRepository.find("Jurislvalda");
        assertThat(jurislvalda, is(Matchers.nullValue()));
    }

    @Test
    @GivenEnvironment(value = ClientEnvironemnt.class, environmentName = "thirdPerson")
    public void findByNameInOtherEnvironment() throws Exception {
        Client client = clientRepository.find("Jurislvalda");

        assertThat(client.getName(), is("Jurislvalda"));
        assertThat(client.getEmail(), is("jurislvalda@hotmail.com"));
        assertThat(client.getGender(), is(Gender.FEMALE));
    }

}
