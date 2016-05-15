package br.com.leonardoterrao.repository;

import br.com.leonardoterrao.configuration.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.lang.reflect.ParameterizedType;
import java.util.List;

public class Repository<T> {

    private final Class<T> type;

    Repository() {
        this.type = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    private Class<T> getMyType() {
        return this.type;
    }

    public void persist(T object) {
        HibernateUtil.getCurrentSession().persist(object);
    }

    public void remove(T object) {
        HibernateUtil.getCurrentSession().delete(object);
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
