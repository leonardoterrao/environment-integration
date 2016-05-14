package br.com.leonardoterrao.repository;

import br.com.leonardoterrao.configuration.HibernateUtil;
import br.com.leonardoterrao.model.Client;
import br.com.leonardoterrao.model.Product;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

public class ProductRepository extends Repository<Product> {

    private static ProductRepository productRepository;

    private ProductRepository() {
        super(Product.class);
    }

    public static ProductRepository getInstance() {
        if (productRepository == null) {
            productRepository = new ProductRepository();
        }

        return productRepository;
    }

    public Client find(String description) {
        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(Product.class);
        criteria.add(Restrictions.eq("description", description));
        return (Client) criteria.uniqueResult();
    }

}
