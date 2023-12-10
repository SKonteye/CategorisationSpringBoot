package com.isi.categorisationproject.controller;
import com.isi.categorisationproject.model.Product;
import com.isi.categorisationproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

@Autowired
private ProductService productService;

// Create a new product
@ResponseBody
@PostMapping
public Product createProduct(@RequestBody Product product) {
    return productService.createProduct(product);
}

// Get all products
@GetMapping
public List<Product> getAllProducts() {
    return productService.getAllProducts();
}

@GetMapping("/{id}")
public ResponseEntity<Product> getProductById(@PathVariable Long id) {
    return productService.getProductById(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
}
    // Update a product
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
        Optional<Product> updatedProduct = Optional.ofNullable(productService.updateProduct(id, productDetails));
        if (updatedProduct.isPresent()) {
            return ResponseEntity.ok(updatedProduct.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a product
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        if (productService.deleteProduct(id)){
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
