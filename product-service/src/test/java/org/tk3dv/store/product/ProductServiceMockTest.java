package org.tk3dv.store.product;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.tk3dv.store.product.entity.Category;
import org.tk3dv.store.product.entity.Product;
import org.tk3dv.store.product.repository.ProductRepository;
import org.tk3dv.store.product.service.ProductService;
import org.tk3dv.store.product.service.ProductServiceImpl;

import java.util.Date;
import java.util.Optional;

@SpringBootTest
public class ProductServiceMockTest {

    @Mock
    private ProductRepository repository;

    private ProductService service;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
        service = new ProductServiceImpl(repository);

        Product computer = Product.builder()
                .id(1L)
                .name("computer")
                .category(Category.builder().id(1L).build())
                .price(Double.parseDouble("12.5"))
                .stock(Double.parseDouble("5"))
                .build();

        Mockito.when(repository.findById(1L))
                .thenReturn(Optional.of(computer));

        Mockito.when(repository.save(computer))
                .thenReturn(computer);
    }

    @Test
    public void whenValidGetId_thenReturnProduct(){
        Product found = service.getProduct(1L);

        Assertions.assertThat(found.getName()).isEqualTo("computer");
    }

    @Test
    public void whenValidUpdateStock_thenReturnNewStock(){
        Product newStock = service.UpdateStock(1L,Double.parseDouble("7"));

        Assertions.assertThat(newStock.getStock()).isEqualTo(12);
    }












}
