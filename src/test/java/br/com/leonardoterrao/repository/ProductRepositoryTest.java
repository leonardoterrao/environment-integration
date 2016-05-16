package br.com.leonardoterrao.repository;

import br.com.lemao.environment.annotation.GivenEnvironment;
import br.com.lemao.environment.annotation.IgnoreEnvironment;
import br.com.leonardoterrao.environment.product.ProductEnvironment;
import br.com.leonardoterrao.model.Product;
import br.com.leonardoterrao.repository.br.com.leonardoterrao.junit.TransactionRule;
import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

@GivenEnvironment(ProductEnvironment.class)
public class ProductRepositoryTest {

    @Rule
    public TransactionRule ruleTransaction = new TransactionRule();

    private ProductRepository productRepository = ProductRepository.getInstance();

    @Test
    @IgnoreEnvironment
    public void findAllWithoutEnvironment() throws Exception {
        List<Product> products = productRepository.findAll();
        assertThat(products, is(Matchers.empty()));
    }

    @Test
    public void remove() throws Exception {
        Product product = productRepository.find(ProductEnvironment.PRODUCT_ONE);
        productRepository.remove(product);

        final Product productNull = productRepository.find(ProductEnvironment.PRODUCT_ONE);
        assertThat(productNull, is(nullValue()));
    }

    @Test
    public void findAllWihEnvironment() throws Exception {
        List<Product> products = productRepository.findAll();
        assertThat(products.size(), is(3));
    }

    @Test
    public void findByName() throws Exception {
        Product product = productRepository.find(ProductEnvironment.PRODUCT_ONE);
        assertThat(product.getDescription(), is(ProductEnvironment.PRODUCT_ONE));
        assertThat(product.getStockLevel(), is(20));
        assertThat(product.getPrice(), is(new BigDecimal(520.00)));
    }

}
