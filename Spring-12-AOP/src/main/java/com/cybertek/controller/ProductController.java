package com.cybertek.controller;

import com.cybertek.entity.Product;
import com.cybertek.entity.ResponseWrapper;
import com.cybertek.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;
    //Logger logger = LoggerFactory.getLogger(ProductController.class);

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getProducts() {

        // logger.info("Before -> Controller: {} - Method: {} - Input Parameter: {}", "ProductController", "getProducts()");

        List<Product> products = productService.getProducts();

        // logger.info("After -> Controller: {} - Method: {} - Output Parameters: {}", "ProductController", "getProducts", products);

        return products;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(productService.getProduct(id));
    }

    @PostMapping
    public ResponseEntity<List<Product>> createProduct(@RequestBody Product product){
        List<Product> set = productService.create(product);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("Version", "Cybertek.v1")
                .header("Operation", "Create")
                .body(set);

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<List<Product>> deleteProduct(@PathVariable("id") Integer id) {
        // WAY 1
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Version", "Cybertek.v1");
        responseHeaders.set("Operation", "Delete");

        List<Product> list = productService.delete(id);

        return new ResponseEntity<>(list, responseHeaders, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<List<Product>> updateProduct(@PathVariable("id") Integer id, @RequestBody Product product) {
        // b. MultiValueMap
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Version", "Cybertek.v1");
        headers.add("Operation", "Create");

        List<Product> list = productService.update(id, product);

        return new ResponseEntity<>(list, headers, HttpStatus.OK);
    }

    @GetMapping("/read")
    public ResponseEntity<ResponseWrapper> readAllProducts() {
        return ResponseEntity
                .ok(new ResponseWrapper("PRODUCTS SUCCESSFULLY RETRIEVED", productService.getProducts()));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseWrapper> deleteProduct2(@PathVariable("id") Integer id) {
        // WAY 2 - using ResponseWrapper
        List<Product> list = productService.delete(id);
        return ResponseEntity.ok(new ResponseWrapper("PRODUCT SUCCESSFULLY DELETED"));
    }

    @DeleteMapping("/delete2/{id}")
    public ResponseEntity<ResponseWrapper> deleteProduct3(@PathVariable("id") Integer id) {
        // WAY 3 - using ResponseWrapper
        List<Product> list = productService.delete(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ResponseWrapper("PRODUCT SUCCESSFULLY DELETED"));
    }
}
