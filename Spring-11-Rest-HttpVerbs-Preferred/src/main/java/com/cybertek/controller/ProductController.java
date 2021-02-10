package com.cybertek.controller;

import com.cybertek.entity.Product;
import com.cybertek.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
/*
    @RestController annotation was introduced in Spring 4.0 to simplify the creation of RESTful web services.
    It is a convenience annotation that combines @Controller and @ResponseBody
    which eliminates the need to annotate every request handling method of the controller class with the @ResponseBody
 */
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/{id}")
    public Product getProduct(@PathVariable("id") Integer id) {
        return productService.getProduct(id);
    }
    /*
        @PathVariable is a Spring annotation which indicates that a method parameter should be bound to a URI template variable.
        It has the following optional elements
            * name - name of the path variable to bind to
            * required - tells whether the path variable is required
            * value - alias for name
     */

    @GetMapping
    /*
        @GetMapping maps HTTP GET requests onto specific handler methods.
        It is a composed annotation that acts as a shortcut for @RequestMapping(method = RequestMethod.GET)
     */
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    @PostMapping
     /*
        @PostMapping is specialized version of @RequestMapping that acts as a shortcut for @RequestMapping(method = RequestMethod.POST)
        @PostMapping methods handle the HTTP POST requests matched with given URI expression.
     */
    public List<Product> createProduct(@RequestBody Product product){
        return productService.create(product);
    }
    /*
        @RequestBody binds the HTTPRequest body to the domain object.
        Spring framework automatically deserializes incoming HTTPRequest to the Java object using Http Message Converters.
        We pass the body of the request through a HttpMessageConverter to resolve the method argument depending on the content type of the request
     */

    @DeleteMapping(value = "/{id}")
    /*
        @DeleteMapping for mapping HTTP DELETE requests onto specific handler methods
        Specifically, @DeleteMapping is a composed annotation that acts as a shortcut for@RequestMapping(method = RequestMethod.DELETE)
     */
    public List<Product> deleteProduct(@PathVariable("id") Integer id) {
        return productService.delete(id);
    }

    @PutMapping(value = "/{id}")
     /*
        @PutMapping for mapping HTTP PUT requests onto specific handler methods
        Specifically, @PutMapping is a composed annotation that acts as a shortcut for @RequestMapping(method = RequestMethod.PUT)
     */
    public List<Product> updateProduct(@PathVariable("id") Integer id, @RequestBody Product product) {
        return productService.update(id, product);
    }
}
