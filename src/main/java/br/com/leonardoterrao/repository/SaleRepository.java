package br.com.leonardoterrao.repository;

import br.com.leonardoterrao.model.Sale;

public class SaleRepository extends Repository<Sale> {

    private static SaleRepository saleRepository;

    public static SaleRepository getInstance() {
        if (saleRepository == null) {
            saleRepository = new SaleRepository();
        }

        return saleRepository;
    }

}
