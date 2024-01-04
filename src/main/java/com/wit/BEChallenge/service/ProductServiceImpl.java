package com.wit.BEChallenge.service;

import com.wit.BEChallenge.dto.ProductResponse;
import com.wit.BEChallenge.entity.Product;
import com.wit.BEChallenge.exceptions.CommerceException;
import com.wit.BEChallenge.repository.ProductRepository;
import com.wit.BEChallenge.util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductResponse> findAll() {
        return Converter.ProductListConverter(productRepository.findAll());
    }

    @Override
    public ProductResponse findById(Long id) {
        Optional<Product> optional = productRepository.findById(id);
        if (optional.isPresent()) {
            Product found = optional.get();
            return new ProductResponse(found.getId(), found.getName(), found.getDescription(), found.getPrice(), found.getStock(), found.getRating(), found.getImage());
        }
        throw new CommerceException("Product with given id is not found " + id, HttpStatus.NOT_FOUND);
    }

    @Override
    public ProductResponse save(Product product) {
        Product saved= productRepository.save(product);
        return new ProductResponse(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getStock(), product.getRating(), product.getImage());
    }

    @Override
    public ProductResponse delete(Long id) {
        ProductResponse toBeRemoved=findById(id);
        productRepository.deleteById(toBeRemoved.id());
        return new ProductResponse(toBeRemoved.id(), toBeRemoved.name(), toBeRemoved.description(), toBeRemoved.price(), toBeRemoved.stock(), toBeRemoved.rating(), toBeRemoved.image());
    }

    //TODO ÇALIŞANA KADAR DEVAM
    @Override
    public List<ProductResponse> findByCategoryId(Integer categoryId) {
        return productRepository.findByCategoryId(categoryId);

    }

    @Override
    public List<Product> saveAll(List<Product> products) {
        return productRepository.saveAll(products);
    }
}
