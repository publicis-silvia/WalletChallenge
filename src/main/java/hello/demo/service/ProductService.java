package hello.demo.service;

import hello.demo.entity.Availability;
import hello.demo.entity.Product;
import hello.demo.repository.ProductRepository;
import hello.demo.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository repository;
    private final TransactionRepository transactionRepository;

    public ProductService(ProductRepository repository, TransactionRepository transactionRepository) {
        this.repository = repository;
        this.transactionRepository = transactionRepository;
    }

    public List<Product> getProducts() {
        List<Product> products = repository.findAll();

        List<Product> finalProducts = new ArrayList<>();
        for (Product p: products) {
            if (!transactionRepository.existsByProductId(p)){
                p.setAvailable(Availability.AVAILABLE);
            }else{
                p.setAvailable(Availability.NOT_AVAILABLE);
            }

            finalProducts.add(p);
        }

        return finalProducts;
    }





}
