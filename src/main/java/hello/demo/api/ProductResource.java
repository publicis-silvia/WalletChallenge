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

	@RequestMapping("api/products/v1/get")
	public List<Product> getAllProducts(Model model) {
		List<Product> products = service.getProducts();
		return products;
	}

	@RequestMapping("api/products/v1/get/{ProductId}")
	public Product getProductById(@PathVariable Long productId) {
		Product product = service.getProductById(productId);

		if (product == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);

		return product;
	}

	@RequestMapping("api/products/v1/get/owner/{OwnerId}")
	public Product getProductsByOwner(@PathVariable Long ownerId) {
		// TODO: Implement get products by owner
		Product product = service.getProductById(ownerId);

		if (product == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);

		return product;
	}

	@RequestMapping(value = "api/products/v1/create", method = RequestMethod.POST)
	public ResponseEntity<Object> addProduct(@RequestBody Product newProduct) {
		long productId = service.addProduct(newProduct);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{ProductId}").buildAndExpand(productId)
				.toUri();

		return ResponseEntity.created(location).build();
	}

	@RequestMapping(value = "api/products/v1/delete/{ProductId}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteProductById(@PathVariable Long productId) {
		service.deleteProductById(productId);

		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "api/products/v1/update/{ProductId}", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateProduct(@PathVariable long productId, @RequestBody Product product) {
		service.updateProduct(productId, product);

		return ResponseEntity.noContent().build();
	}
}
