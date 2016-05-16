package br.com.leonardoterrao.environment.sale;

import br.com.lemao.environment.Environment;
import br.com.lemao.environment.annotation.GivenEnvironment;
import br.com.leonardoterrao.environment.client.ClientEnvironemnt;
import br.com.leonardoterrao.environment.product.ProductEnvironment;
import br.com.leonardoterrao.model.Client;
import br.com.leonardoterrao.model.Item;
import br.com.leonardoterrao.model.Product;
import br.com.leonardoterrao.model.Sale;
import br.com.leonardoterrao.repository.ClientRepository;
import br.com.leonardoterrao.repository.ProductRepository;
import br.com.leonardoterrao.repository.SaleRepository;

import java.util.Arrays;

public class SaleEnvironment extends Environment {

    public SaleRepository saleRepository = SaleRepository.getInstance();
    public ClientRepository clientRepository = ClientRepository.getInstance();
    public ProductRepository productRepository = ProductRepository.getInstance();

    @Override
    @GivenEnvironment(ClientEnvironemnt.class)
    public void run() {
        final Product productTwo = productRepository.find(ProductEnvironment.PRODUCT_TWO);
        final Product productThree = productRepository.find(ProductEnvironment.PRODUCT_THREE);

        Item itemOne = Item.builder().product(productTwo).quantity(4).build();
        Item itemTwo = Item.builder().product(productThree).quantity(1).build();

        final Client client = clientRepository.find(ClientEnvironemnt.CLIENTE_LEONARDO);

        Sale sale = Sale.builder().client(client).items(Arrays.asList(itemOne, itemTwo)).build();

        saleRepository.persist(sale);
    }

}
