package br.com.leonardoterrao.environment.product;

import br.com.lemao.environment.Environment;
import br.com.leonardoterrao.model.Product;
import br.com.leonardoterrao.repository.ProductRepository;

import java.math.BigDecimal;

public class ProductEnvironment extends Environment {

    private ProductRepository productRepository = ProductRepository.getInstance();

    public static final String PRODUCT_ONE = "Product One";
    public static final String PRODUCT_TWO = "Product Two";
    public static final String PRODUCT_THREE = "Product Three";

    @Override
    public void run() {
        Product productOne = Product.builder().description(PRODUCT_ONE).price(new BigDecimal(520.00)).stockLevel(20).build();
        Product productTwo = Product.builder().description(PRODUCT_TWO).price(new BigDecimal(100.00)).stockLevel(12).build();
        Product productThree = Product.builder().description(PRODUCT_THREE).price(new BigDecimal(145.50)).stockLevel(7).build();

        productRepository.persist(productOne);
        productRepository.persist(productTwo);
        productRepository.persist(productThree);
    }

}
