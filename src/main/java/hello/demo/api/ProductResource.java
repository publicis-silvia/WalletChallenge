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
import hello.demo.service.DataAccessService;

@RestController
public class ProductResource {

	private final DataAccessService<Product> service;

	ProductResource(DataAccessService<Product> service) {
		this.service = service;
	}

	@RequestMapping("api/v1/products")
	public List<Product> getAllProducts(Model model) {
		List<Product> products = service.getAll();
		return products;
	}

	@RequestMapping("api/v1/products/{productId}")
	public Product getProductById(@PathVariable Long productId) {
		Product product = service.getById(productId);

		if (product == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);

		return product;
	}

	@RequestMapping("api/v1/products/owner/{ownerId}")
	public Product getProductsByOwner(@PathVariable Long ownerId) {
		// TODO: Implement get products by owner
		Product product = service.getById(ownerId);

		if (product == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);

		return product;
	}

	@RequestMapping(value = "api/v1/products", method = RequestMethod.POST)
	public ResponseEntity<Object> addProduct(@RequestBody Product newProduct) {
		long productId = service.add(newProduct);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{productId}").buildAndExpand(productId)
				.toUri();

		return ResponseEntity.created(location).build();
	}

	@RequestMapping(value = "api/v1/products/{productId}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteProductById(@PathVariable Long productId) {
		service.deleteById(productId);

		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "api/v1/products/{productId}", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateProduct(@PathVariable long productId, @RequestBody Product product) {
		service.update(productId, product);

		return ResponseEntity.noContent().build();
	}
}
