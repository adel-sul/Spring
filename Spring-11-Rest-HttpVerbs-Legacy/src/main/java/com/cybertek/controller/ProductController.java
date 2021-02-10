package com.cybertek.controller;

import com.cybertek.entity.Product;
import com.cybertek.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET) // by default method = RequestMethod.GET, no need to specify
    public @ResponseBody Product getProduct(@PathVariable("id") Integer id) {
        return productService.getProduct(id);
    }

    @RequestMapping(value = "/products")
    public @ResponseBody List<Product> getProducts() {
        return productService.getProducts();
    }

    // TODO: CREATE
    @RequestMapping(value = "/products",method = RequestMethod.POST)
    public @ResponseBody List<Product> createProduct(@RequestBody Product product){
        return productService.create(product);
    }

    // TODO: DELETE
    @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
    public @ResponseBody List<Product> deleteProduct(@PathVariable("id") Integer id) {
        return productService.delete(id);
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
    public @ResponseBody List<Product> updateProduct(@PathVariable("id") Integer id, @RequestBody Product product) {
        return productService.update(id, product);
    }
}
