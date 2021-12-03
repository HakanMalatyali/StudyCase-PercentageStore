package ngss.store.api.controller;


import ngss.store.business.abstracts.ProductService;
import ngss.store.entities.Product;
import ngss.store.entities.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/products")
public class ProductsController {

    private ProductService productService;


    @Autowired
    public ProductsController(ProductService productService) {
        super();
        this.productService = productService;
    }

    @PostMapping("/addProduct")
    public Product add(@RequestBody Product product){
        return productService.add(product);
    }

    @GetMapping("/getById")
    public Product findByPid(@RequestParam String pid){
        return productService.findByPid(pid);
    }

    @GetMapping("/findByProductType")
    public List<Product> findByProductType(@RequestParam String productType){
        return productService.findByProductType(productType);
    }

    @GetMapping("/findAll")
    public List<Product> findAll(){
        return productService.findAll();
    }

    @GetMapping("/getProductsByProductsId")
    public List<Product> getProductsByProductsId(@RequestParam List<String> pid){
        return productService.getProductsByProductsId(pid);
    }

    @PostMapping("/getCustomerAmount")
    public ProductResponse getCostumerAmount(@RequestParam List<String> pid){
        return productService.getCostumerAmount(pid);
    }


}

