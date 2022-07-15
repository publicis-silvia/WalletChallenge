package hello.demo.controller;

import hello.demo.entity.Product;
import hello.demo.exceptions.ProductNotFoundException;
import hello.demo.repository.ProductRepository;
import hello.demo.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController {

    private final ProductRepository repository;
    private final ProductService service;


    ProductController(ProductRepository repository, ProductService service) {
        this.repository = repository;
        this.service = service;
    }

    @GetMapping("/getProducts")
    public String list(@RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
                       @RequestParam(value = "size", required = false, defaultValue = "10") int size, Model model) {
        model.addAttribute("products", service.getProducts(pageNumber, size));

        return "products";
    }


//    @GetMapping("/redirectAddProductPage")
//    public String addProductPage(){
//        return "addProduct";
//    }

//    @RequestMapping(value = "/redirectAddProductPage", method = RequestMethod.GET)
//    public String redirect() {
//        return "addProduct";
//    }
//    @RequestMapping(value = "/addProductPage", method = RequestMethod.GET)
//    public String finalPage() {
//        return "addProduct";
//    }

    @GetMapping("/redirectAddProductPage")
    public String list2() {
        return "addProduct";
    }


    @PostMapping("/postProduct")
    Product addProduct(@RequestBody Product newProduct) {
        return repository.save(newProduct);
    }

    // Single item

    @GetMapping("/getProduct/{id}")
    Product one(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    @PutMapping("/putProduct/{id}")
    Product replaceProduct(@RequestBody Product newProduct, @PathVariable Long id) {

        return repository.findById(id)
                .map(product -> {
                    product.setName(newProduct.getName());
                    return repository.save(product);
                })
                .orElseGet(() -> {
                    newProduct.setId(id);
                    return repository.save(newProduct);
                });
    }

    @DeleteMapping("/deleteProduct")
    String deleteProduct(@ModelAttribute(value="product") Product product, @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
                      @RequestParam(value = "size", required = false, defaultValue = "10") int size, Model model) {

        repository.deleteById(product.getId());
        model.addAttribute("products", service.getProducts(pageNumber, size));


        return "redirect:products";
    }
}
