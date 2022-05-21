package com.project.ecommerce3.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.project.ecommerce3.dto.ProductDto;
import com.project.ecommerce3.model.Category;
import com.project.ecommerce3.model.Product;
import com.project.ecommerce3.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public void createProduct(ProductDto productDto, Category category) {
        Product product = new Product();
        product.setDescription(productDto.getDescription());
        product.setName(productDto.getName());
        product.setImageURL(productDto.getImageURL());
        product.setPrice(productDto.getPrice());
        product.setCategory(category);
        productRepository.save(product);
    }

    public ProductDto getProductDto(Product product){
        ProductDto productDto = new ProductDto();
        productDto.setDescription(product.getDescription());
        productDto.setName(product.getName());
        productDto.setImageURL(product.getImageURL());
        productDto.setCategoryId(product.getCategory().getId());
        productDto.setPrice(product.getPrice());
        // productDto.setId(product.getId());
        return productDto;
    }

    public List<ProductDto> getAllProducts(){
        List<Product> allProducts = productRepository.findAll();

        List<ProductDto> productDtos = new ArrayList<>();
        for (Product product: allProducts) {
            productDtos.add(getProductDto(product));
        }
        return productDtos;
    }

    public void updateProduct(ProductDto productDto,Integer productId) throws Exception{
        Optional<Product> optionalProduct = productRepository.findById(productId);
        // throw an exception if product does not exist
        if(!optionalProduct.isPresent()) {
            throw new Exception("Product not present");
        }
        Product product = optionalProduct.get();
        product.setDescription(productDto.getDescription());
        product.setName(productDto.getName());
        product.setImageURL(productDto.getImageURL());
        product.setPrice(productDto.getPrice());
        productRepository.save(product);
    }
}
