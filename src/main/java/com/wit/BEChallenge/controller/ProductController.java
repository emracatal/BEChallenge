package com.wit.BEChallenge.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.wit.BEChallenge.dto.ProductResponse;
import com.wit.BEChallenge.entity.Category;
import com.wit.BEChallenge.entity.Product;
import com.wit.BEChallenge.exceptions.CommerceException;
import com.wit.BEChallenge.service.CategoryService;
import com.wit.BEChallenge.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin("*")
public class ProductController {
    private ProductService productService;
    private CategoryService categoryService;
    private RestTemplateBuilder restTemplateBuilder;

    @Autowired
    public ProductController(ProductService productService, CategoryService categoryService, RestTemplateBuilder restTemplateBuilder) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @GetMapping("")
    public List<ProductResponse> findAll() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public ProductResponse findById(@PathVariable Long id) {
        return productService.findById(id);
    }

    //TODO PostMapping("/") ÇALIŞMIYOR
    @PostMapping("/")
    public ProductResponse save(@RequestBody Product product) {
        return productService.save(product);
    }

    @PostMapping("/{categoryId}")
    public ProductResponse save(@RequestBody Product product, @PathVariable Long categoryId) {
        Category category = categoryService.findCategoryById(categoryId);
        category.getProductList().add(product);
        product.setCategory(category);
        productService.save(product);
        return new ProductResponse(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getStock(), category.getRating(), product.getImage());
    }

    private static final String GET_ALL_PRODUCTS = "https://workintech-fe-ecommerce.onrender.com/products/?offset=0&limit=1000";

    @DeleteMapping("/{id}")
    public ProductResponse delete(@PathVariable Long id) {
        return productService.delete(id);
    }

    //TODO hatalı post olunca id ndn increment ediyor anla
    @PostMapping("/all")
    public String saveAll() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<JsonNode> productResponses =
                restTemplate.getForEntity(GET_ALL_PRODUCTS, JsonNode.class);

        List<Product> products = new ArrayList<>();
        for (JsonNode node : productResponses.getBody().get("products")) {
            Product product = new Product();
            product.setName(node.get("name").asText());
            product.setDescription(node.get("description").asText());
            product.setPrice(node.get("price").asDouble());
            product.setStock(node.get("stock").asInt());
            product.setRating(node.get("rating").asDouble());
            product.setSellCount(node.get("sell_count").asInt());
            product.setCategory(categoryService.findCategoryById(node.get("category_id").asLong()));
            product.setImage(node.get("images").get(0).get("url").asText());
            products.add(product);
        }
        productService.saveAll(products);
        return "Completed";
    }

    //TODO DOĞRU ÇALIŞANA KADAR DEVAM
    @GetMapping("/")
    public List<ProductResponse> findByParams(@RequestParam(name = "limit", required = false) Long limit,
                                              @RequestParam(name = "offset", required = false) Long offset,
                                              @RequestParam(name = "category", required = false) Integer categoryId,
                                              @RequestParam(name = "filter", required = false) String filter,
                                              @RequestParam(name = "sort", required = false) String sort) {


        return productService.findByRequestParams(categoryId,sort,filter,limit,offset);
    }

    /**
     * This method used in ecommerce website homepage
     * @return 8 bestseller products list
     */
    @GetMapping("/bestseller8")
    public List<ProductResponse> bestSeller8() {
        return productService.bestSeller8();
    }

    }
