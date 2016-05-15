package br.com.leonardoterrao.junit;

import br.com.lemao.environment.Environment;
import br.com.lemao.environment.annotation.GivenEnvironment;
import br.com.lemao.environment.annotation.IgnoreEnvironment;
import br.com.lemao.environment.exception.AfterEnvironmentException;
import br.com.lemao.environment.exception.EnvironmentException;
import br.com.lemao.environment.exception.EnvironmentNotImplementedException;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import java.lang.reflect.Method;

public class EnvironmentStatement extends Statement {

    private Description description;
    private Statement statement;

    public EnvironmentStatement(Statement statement, Description description) {
        this.statement = statement;
        this.description = description;
    }

    protected void before() {
        GivenEnvironment givenEnvironment = getGivenEnvironmentAnnotation();

        if (givenEnvironment == null || getIgnoreEnvironmentAnnotation() != null) {
            return;
        }

        runEnvironment(givenEnvironment);
    }

    protected void after() {
        // do nothing here
    }

    private void runEnvironment(GivenEnvironment givenEnvironment) {
        Class<? extends Environment> environmentClass = givenEnvironment.value();
        try {
            Method environmentMethod = environmentClass.getMethod(givenEnvironment.environmentName());
            GivenEnvironment environmentFather = environmentMethod.getAnnotation(GivenEnvironment.class);
            if (environmentFather != null) {
                runEnvironment(environmentFather);
            }

            beforeRun(givenEnvironment);

            environmentMethod.invoke(getEnvironmentInstance(environmentClass));
        } catch (NoSuchMethodException e) {
            throw new EnvironmentNotImplementedException(environmentClass, givenEnvironment.environmentName(), e);
        } catch (Exception e) {
            throw new EnvironmentException(environmentClass, givenEnvironment.environmentName(), e);
        } finally {
            afterRun(givenEnvironment);
        }
    }

    private void beforeRun(GivenEnvironment givenEnvironment) throws Exception {
        Class<? extends Environment> environmentClass = givenEnvironment.value();
        environmentClass.newInstance().beforeRun();
    }

    private void afterRun(GivenEnvironment givenEnvironment) {
        Class<? extends Environment> environmentClass = givenEnvironment.value();
        try {
            environmentClass.newInstance().afterRun();
        } catch (Exception e) {
            new AfterEnvironmentException(environmentClass, givenEnvironment.environmentName(), e);
        }
    }

    private Environment getEnvironmentInstance(Class<? extends Environment> environmentClass) throws Exception {
        return environmentClass.newInstance();
    }

    public IgnoreEnvironment getIgnoreEnvironmentAnnotation() {
        return description.getAnnotation(IgnoreEnvironment.class);
    }

    private GivenEnvironment getGivenEnvironmentAnnotation() {
        GivenEnvironment givenEnvironmentMethodAnnotation = description.getAnnotation(GivenEnvironment.class);

        if (givenEnvironmentMethodAnnotation != null) {
            return givenEnvironmentMethodAnnotation;
        }

        return description.getTestClass().getAnnotation(GivenEnvironment.class);
    }

    @Override
    public void evaluate() throws Throwable {
        try {
            before();
            statement.evaluate();
            after();
        } catch (Exception e) {
            throw new EnvironmentException(getGivenEnvironmentAnnotation().getClass(),
                    getGivenEnvironmentAnnotation().environmentName(), e);
        }

    }

}
