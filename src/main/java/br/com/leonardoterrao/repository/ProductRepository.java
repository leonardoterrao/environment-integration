package br.com.leonardoterrao.repository;

import br.com.leonardoterrao.configuration.HibernateUtil;
import br.com.leonardoterrao.model.Product;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

public class ProductRepository extends Repository<Product> {

    private static ProductRepository productRepository;

    public static ProductRepository getInstance() {
        if (productRepository == null) {
            productRepository = new ProductRepository();
        }

        return productRepository;
    }

    public Product find(String description) {
        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(Product.class);
        criteria.add(Restrictions.eq("description", description));
        return (Product) criteria.uniqueResult();
    }

}
