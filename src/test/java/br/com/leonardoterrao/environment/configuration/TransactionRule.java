package br.com.leonardoterrao.environment.configuration;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class TransactionRule implements TestRule {

    public Statement apply(Statement base, Description description) {
        return new TransactionalStatement(base, description);
    }

}
