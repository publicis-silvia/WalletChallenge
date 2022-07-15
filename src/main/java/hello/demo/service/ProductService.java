package hello.demo.service;

import hello.demo.entity.Product;
import hello.demo.frontend.Page;
import hello.demo.frontend.Paged;
import hello.demo.frontend.Paging;
import hello.demo.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Paged<Product> getProducts(int pageNumber, int size) {

        List<Product> products =  repository.findAll();

        List<Product> paged = products.stream()
                .skip(pageNumber)
                .limit(size)
                .collect(Collectors.toList());

        int totalPages = products.size() / size;
        return new Paged<>(new Page<>(paged, totalPages), Paging.of(totalPages, pageNumber, size));

    }

}
