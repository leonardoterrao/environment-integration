package br.com.leonardoterrao.environment.configuration;

import br.com.lemao.environment.junit.EnvironmentStatement;
import br.com.leonardoterrao.configuration.HibernateUtil;
import org.hibernate.Transaction;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class TransactionalStatement extends EnvironmentStatement {

    private Transaction transaction = HibernateUtil.getCurrentSession().getTransaction();

    public TransactionalStatement(Statement statement, Description description) {
        super(statement, description);
    }

    @Override
    public void before() {
        transaction.begin();
        super.before();
    }

    @Override
    public void after() {
        transaction.rollback();
        HibernateUtil.getCurrentSession().disconnect();
    }

}
