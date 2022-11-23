package com.order.ecommerce.integrationtest;

import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@AutoConfigureEmbeddedDatabase
@AutoConfigureMockMvc
@Sql({"/product/insert_prerequisite_records.sql"})
public final class ProductIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @SneakyThrows
    public void testGetProduct() {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/products/106"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().json("{\n  \"productId\": \"106\",\n  \"sku\": \"1006\",\n  \"title\": \"SoftDrink\",\n  \"description\": \"Coke\",\n  \"price\": 5.99,\n  \"createdAt\": \"2022-10-17\"\n}"));

    }
}
