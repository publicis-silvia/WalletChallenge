package hello.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import hello.demo.entity.Product;
import hello.demo.repository.ProductRepository;
import hello.demo.service.ProductService;

@Controller
public class ProductController {

    private final ProductRepository repository;
    private final ProductService service;


    ProductController(ProductRepository repository, ProductService service) {
        this.repository = repository;
        this.service = service;
    }

    @GetMapping("/getProducts")
    public String list(Model model) {
		model.addAttribute("products", service.getAll());

        return "products";
    }

    @GetMapping("/redirectAddProductPage")
    public String list2(Model model) {
        model.addAttribute("product", new Product());
        return "addProduct";
    }


    @PostMapping("/postProduct")
    public String addProduct(@ModelAttribute Product newProduct, Model model) {
        repository.save(newProduct);
		model.addAttribute("products", service.getAll());

        return "redirect:/getProducts";
    }



    // Single item

//    @GetMapping("/getProduct")
//    public Product getProduct(@RequestParam Long id) {
//        return repository.findById(id)
//                .orElseThrow(() -> new ProductNotFoundException(id));
//    }

    @GetMapping("/getProduct")
    public ModelAndView getProduct(@RequestParam Long productId) {
        ModelAndView mav = new ModelAndView("showProduct");
        Product product = repository.findById(productId).get();
        mav.addObject("product", product);
        return mav;
    }

    @GetMapping("/putProduct")
    public String replaceProduct(@ModelAttribute Product updatedProduct, Model model) {

        repository.findById(updatedProduct.getId())
                .map(product -> {
                    product.setName(updatedProduct.getName());
                    product.setPrice(updatedProduct.getPrice());
                    return repository.save(product);
                });

		model.addAttribute("products", service.getAll());
        return "redirect:/getProducts";
    }
    @GetMapping("/showUpdateFormProduct")
    public ModelAndView showUpdateFormProduct(@RequestParam Long productId) {
        ModelAndView mav = new ModelAndView("updateProduct");
        Product product = repository.findById(productId).get();
        mav.addObject("product", product);
        return mav;
    }

    @GetMapping("/deleteProduct")
    public String deleteProduct(@RequestParam Long productId, Model model) {

        repository.deleteById(productId);
		model.addAttribute("products", service.getAll());

        return "redirect:/getProducts";
    }
}
