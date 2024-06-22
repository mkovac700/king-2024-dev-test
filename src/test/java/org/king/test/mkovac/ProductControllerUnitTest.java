package org.king.test.mkovac;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.king.test.mkovac.controller.ProductController;
import org.king.test.mkovac.entity.Product;
import org.king.test.mkovac.service.ProductService;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest(ProductController.class)
public class ProductControllerUnitTest {
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private ProductService productService;

  private Product product;

  @BeforeEach
  public void setup() {
    product = new Product(1,
        "https://cdn.dummyjson.com/products/images/beauty/Essence%20Mascara%20Lash%20Princess/thumbnail.png",
        "Essence Mascara Lash Princess", 9.99f,
        "The Essence Mascara Lash Princess is a popular mascara known for its volumizing and lengthening effe");
  }

  @Test
  public void testGetProductById() throws Exception {
    Mockito.when(productService.getProductById(1)).thenReturn(product);

    mockMvc.perform(get("/api/products/1")).andDo(print()).andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.price", is(9.99))).andExpect(jsonPath("$.id", is(1)))
        .andExpect(jsonPath("$").isNotEmpty());
  }

  @Test
  public void testGetAllProducts() throws Exception {
    Mockito.when(productService.getAllProducts(0, 5))
        .thenReturn(Collections.singletonList(product));

    mockMvc.perform(get("/api/products?page=0&size=5")).andDo(print()).andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$", hasSize(1))).andExpect(jsonPath("$").isArray());
  }

  @Test
  public void testGetProductsByFilter() throws Exception {
    Mockito.when(productService.getProductsByFilter("beauty", 8.99f, 12.99f))
        .thenReturn(Collections.singletonList(product));

    mockMvc.perform(get("/api/products/filter?category=beauty&priceMin=8.99&priceMax=12.99"))
        .andDo(print()).andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$", hasSize(1))).andExpect(jsonPath("$").isArray());
  }

  @Test
  public void testGetProductsByName() throws Exception {
    Mockito.when(productService.getProductsByName("calvin"))
        .thenReturn(Collections.singletonList(product));

    mockMvc.perform(get("/api/products/search?name=calvin")).andDo(print())
        .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$", hasSize(1))).andExpect(jsonPath("$").isArray());
  }
}
