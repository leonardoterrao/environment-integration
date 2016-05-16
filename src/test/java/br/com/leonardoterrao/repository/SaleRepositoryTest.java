package br.com.leonardoterrao.repository;

import br.com.lemao.environment.annotation.GivenEnvironment;
import br.com.leonardoterrao.environment.client.ClientEnvironemnt;
import br.com.leonardoterrao.environment.product.ProductEnvironment;
import br.com.leonardoterrao.environment.sale.SaleEnvironment;
import br.com.leonardoterrao.model.Product;
import br.com.leonardoterrao.model.Sale;
import br.com.leonardoterrao.repository.br.com.leonardoterrao.junit.TransactionRule;
import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@GivenEnvironment(SaleEnvironment.class)
public class SaleRepositoryTest {

    @Rule
    public TransactionRule ruleTransaction = new TransactionRule();

    SaleRepository saleRepository = SaleRepository.getInstance();
    ProductRepository productRepository = ProductRepository.getInstance();

    @Test
    public void findAllWihEnvironment() throws Exception {
        List<Sale> sales = saleRepository.findAll();
        assertThat(sales.size(), is(1));
    }

    @Test
    public void valideSale() throws Exception {
        Sale sale = saleRepository.findAll().get(0);
        assertThat(sale, is(Matchers.notNullValue()));
        assertThat(sale.getItems().size(), is(2));
        assertThat(sale.getClient().getName(), is(ClientEnvironemnt.CLIENTE_LEONARDO));

        final Product productTwo = productRepository.find(ProductEnvironment.PRODUCT_TWO);
        final Product productThree = productRepository.find(ProductEnvironment.PRODUCT_THREE);

        assertThat(productTwo.getStockLevel(), is(8));
        assertThat(productThree.getStockLevel(), is(6));
    }

    @Test
    public void valideStockLevelAfterSale() throws Exception {
        final Product productTwo = productRepository.find(ProductEnvironment.PRODUCT_TWO);
        final Product productThree = productRepository.find(ProductEnvironment.PRODUCT_THREE);

        assertThat(productTwo.getStockLevel(), is(8));
        assertThat(productThree.getStockLevel(), is(6));
    }

    @Test
    @GivenEnvironment(ProductEnvironment.class)
    public void valideStockLevelBeforeSale() throws Exception {
        final Product productTwo = productRepository.find(ProductEnvironment.PRODUCT_TWO);
        final Product productThree = productRepository.find(ProductEnvironment.PRODUCT_THREE);

        assertThat(productTwo.getStockLevel(), is(12));
        assertThat(productThree.getStockLevel(), is(7));
    }

}
