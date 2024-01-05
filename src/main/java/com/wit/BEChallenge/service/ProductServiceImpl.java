package com.wit.BEChallenge.service;

import com.wit.BEChallenge.dto.ProductResponse;
import com.wit.BEChallenge.entity.Product;
import com.wit.BEChallenge.exceptions.CommerceException;
import com.wit.BEChallenge.repository.ProductRepository;
import com.wit.BEChallenge.util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        Product saved = productRepository.save(product);
        return new ProductResponse(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getStock(), product.getRating(), product.getImage());
    }

    @Override
    public ProductResponse delete(Long id) {
        ProductResponse toBeRemoved = findById(id);
        productRepository.deleteById(toBeRemoved.id());
        return new ProductResponse(toBeRemoved.id(), toBeRemoved.name(), toBeRemoved.description(), toBeRemoved.price(), toBeRemoved.stock(), toBeRemoved.rating(), toBeRemoved.image());
    }

    //TODO ÇALIŞANA KADAR DEVAM
    @Override
    public List<ProductResponse> findByCategoryId(Integer categoryId) {
        return Converter.ProductListConverter(productRepository.findByCategoryId(categoryId));

    }

    @Override
    public List<Product> saveAll(List<Product> products) {
        return productRepository.saveAll(products);
    }

    //    @Override
//    public List<ProductResponse> findByRequestParams(Integer categoryId,String sort,String filter,Long limit,Long offset) {
//        String sortBy = sort.split(":")[0]; //returns price or rating
//        String orderBy = sort.split(":")[1]; //returns asc or desc
//        return Converter.ProductListConverter(productRepository.findByRequestParams(categoryId,sortBy,orderBy,filter,limit,offset)));
//    }

    @Override
    public List<ProductResponse> findByRequestParams(Integer categoryId, String sort, String filter, Long limit, Long offset) {
        List<Product> productList;

        if (categoryId != null) {
            productList = productRepository.findByCategoryId(categoryId);
        } else {
            productList = productRepository.findAll();
        }

        if (filter != null) {
            productList = productList.stream()
                    .filter(product -> product.getName().toLowerCase().contains(filter.toLowerCase()))
                    .collect(Collectors.toList());
        }

        if (sort != null) {
            String sortBy = sort.split(":")[0];
            String orderBy = sort.split(":")[1];

            Sort.Direction sortDirection = orderBy.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;

            if (sortBy.equalsIgnoreCase("price")) {
                productList.sort((p1, p2) -> {
                    if (sortDirection == Sort.Direction.ASC) {
                        return Double.compare(p1.getPrice(), p2.getPrice());
                    } else {
                        return Double.compare(p2.getPrice(), p1.getPrice());
                    }
                });
            } else if (sortBy.equalsIgnoreCase("rating")) {
                productList.sort((p1, p2) -> {
                    if (sortDirection == Sort.Direction.ASC) {
                        return Double.compare(p1.getRating(), p2.getRating());
                    } else {
                        return Double.compare(p2.getRating(), p1.getRating());
                    }
                });
            }
        }

        if (offset < 0 || offset >= productList.size()) {
            throw new IllegalArgumentException("Invalid index value");
        }

        Long toIndex = Math.min(offset + limit, productList.size());
        productList = productList.subList(Math.toIntExact(offset), Math.toIntExact(toIndex));

        return productList.stream()
                .map(product ->
                        new ProductResponse(product.getId(), product.getName(), product.getDescription(),
                                product.getPrice(), product.getStock(), product.getRating(), product.getImage()))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductResponse> bestSeller8() {
        return Converter.ProductListConverter(productRepository.bestSeller8());
    }
}
