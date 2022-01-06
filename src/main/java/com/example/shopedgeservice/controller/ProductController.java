package com.example.shopedgeservice.controller;

import com.example.shopedgeservice.model.Product;
import com.example.shopedgeservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${productservice.baseurl}")
    private String productServiceBaseUrl;

    @GetMapping("/products")
    public List<Product> getUsers(){
        return restTemplate.exchange("http://" + productServiceBaseUrl +"/products", HttpMethod.GET, null, new ParameterizedTypeReference<List<Product>>(){}).getBody();
    }

    @GetMapping("/products/product/{id}")
    public Product getproduct(@PathVariable String id){
        return restTemplate.getForObject("http://" + productServiceBaseUrl +"/products/product/"+id, Product.class);
    }

    @PostMapping("/Products/product")
    public Product newProduct(@RequestBody Product newProduct){
        Product product = restTemplate.postForObject("http://" + productServiceBaseUrl +"/products/product", newProduct,Product.class);
        return product;
    }

    @DeleteMapping("/products/product/{id}")
    public ResponseEntity deleteProduct(@PathVariable String id){
        restTemplate.delete("http://" + productServiceBaseUrl +"/products/product/"+id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/products/product/{id}")
    public Product updateUser(@PathVariable String id, @RequestBody Product newProduct){
        ResponseEntity<Product> productResponseEntity = restTemplate.exchange("http://" + productServiceBaseUrl +"/products/product/"+id, HttpMethod.PUT, new HttpEntity<>(newProduct), Product.class);
        Product retrievedProduct = productResponseEntity.getBody();
        return retrievedProduct;
    }
}
