package br.com.leonardoterrao.repository.br.com.leonardoterrao.junit;

import br.com.leonardoterrao.configuration.HibernateUtil;
import br.com.leonardoterrao.junit.EnvironmentStatement;
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
