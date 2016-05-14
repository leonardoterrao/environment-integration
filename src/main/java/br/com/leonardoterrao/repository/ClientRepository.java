package br.com.leonardoterrao.repository;

import br.com.leonardoterrao.configuration.HibernateUtil;
import br.com.leonardoterrao.model.Client;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

public class ClientRepository extends Repository<Client> {

    private static ClientRepository clientRepository;

    private ClientRepository() {
        super(Client.class);
    }

    public static ClientRepository getInstance() {
        if (clientRepository == null) {
            clientRepository = new ClientRepository();
        }

        return clientRepository;
    }

    public Client find(String name) {
        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(Client.class);
        criteria.add(Restrictions.eq("name", name));
        return (Client) criteria.uniqueResult();
    }

}
