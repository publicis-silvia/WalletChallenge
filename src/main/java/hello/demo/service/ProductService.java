package hello.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import hello.demo.entity.Availability;
import hello.demo.entity.Product;
import hello.demo.repository.ProductRepository;
import hello.demo.repository.TransactionRepository;

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

	public Product getProductById(Long productId) {
		Optional<Product> product = repository.findById(productId);
		return product.get();
	}

	public long addProduct(Product newProduct) {
		Product product = repository.save(newProduct);
		return product.getId();
	}

	public void deleteProductById(Long productId) {
		repository.deleteById(productId);
	}

	public void updateProduct(long productId, Product product) {
		repository.deleteById(productId);
		repository.save(product);
	}

}
