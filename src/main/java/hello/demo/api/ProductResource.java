package hello.demo.api;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import hello.demo.entity.Product;
import hello.demo.service.ProductService;

@RestController
public class ProductResource {

	private final ProductService service;

	ProductResource(ProductService service) {
		this.service = service;
	}

	@RequestMapping("api/v1/products")
	public List<Product> getAllProducts(Model model) {
		List<Product> products = service.getProducts();
		return products;
	}

	@RequestMapping("api/v1/products/{ProductId}")
	public Product getProductById(@PathVariable Long productId) {
		Product product = service.getProductById(productId);

		if (product == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);

		return product;
	}

	@RequestMapping(value = "api/v1/products", method = RequestMethod.POST)
	public ResponseEntity<Object> addProduct(@RequestBody Product newProduct) {
		long productId = service.addProduct(newProduct);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{ProductId}").buildAndExpand(productId)
				.toUri();

		return ResponseEntity.created(location).build();
	}

	@RequestMapping(value = "api/v1/products/{ProductId}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteProductById(@PathVariable Long productId) {
		service.deleteProductById(productId);

		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "api/v1/products/{ProductId}", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateProduct(@PathVariable long productId, @RequestBody Product product) {
		service.updateProduct(productId, product);

		return ResponseEntity.noContent().build();
	}
}
