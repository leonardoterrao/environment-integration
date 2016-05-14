package br.com.leonardoterrao.repository;

import br.com.leonardoterrao.configuration.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class Repository<T> {

    private final Class<T> type;

    protected Repository(Class<T> type) {
        this.type = type;
    }

    private Class<T> getMyType() {
        return this.type;
    }

    public void persist(T object) {
        HibernateUtil.getCurrentSession().persist(object);
    }

    public List<T> findAll() {
        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(getMyType());
        List<T> all = criteria.list();
        return all;
    }

    public T find(Long id) {
        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(getMyType());
        criteria.add(Restrictions.eq("id", id));
        return (T) criteria.uniqueResult();
    }

}
