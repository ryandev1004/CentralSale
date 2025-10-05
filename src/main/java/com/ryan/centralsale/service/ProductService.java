package com.ryan.centralsale.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ryan.centralsale.mapper.ProductMapper;
import com.ryan.centralsale.model.Product;
import com.ryan.centralsale.model.dto.ProductCreateDTO;
import com.ryan.centralsale.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ObjectMapper objectMapper; // used for parsing our JSON response

    @Value("${rainforest.api.key}")
    private String rainforestApiKey; // API key for authenticating requests to Rainforest API
    private WebClient webClient;

    private WebClient getWebClient() {
        if (webClient == null) {
            webClient = WebClient.builder()
                    .baseUrl("https://api.rainforestapi.com")
                    .build();
        }
        return webClient;
    }

    public Product fetchProduct(String asin) {
        Optional<Product> existingProduct = productRepository.findByAsin(asin);
        if (existingProduct.isPresent()) {
            return existingProduct.get();
        }
        ProductCreateDTO productCreateDTO = fetchProductData(asin);
        if (productCreateDTO == null) {
            return null;
        }
        return productRepository.save(productMapper.toEntity(productCreateDTO));
    }

    public ProductCreateDTO fetchProductData(String asin) {
        try {
            String response = getWebClient().get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/request")
                            .queryParam("api_key", rainforestApiKey)
                            .queryParam("amazon_domain", "amazon.com")
                            .queryParam("asin", asin)
                            .queryParam("type", "product")
                            .build())
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            return parseRainforestResponse(response);
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch product data for ASIN: " + asin, e);
        }
    }

    private ProductCreateDTO parseRainforestResponse(String response) {
        try {
            JsonNode root = objectMapper.readTree(response);
            // Check if request was successful
            if (!root.path("request_info").path("success").asBoolean()) {
                throw new RuntimeException("API request failed");
            }
            JsonNode product = root.path("product");
            if (product.isMissingNode()) {
                return null;
            }
            ProductCreateDTO dto = new ProductCreateDTO();
            dto.setAsin(product.path("asin").asText());
            dto.setTitle(product.path("title").asText());
            dto.setProductUrl(product.path("link").asText());
            // Image
            JsonNode mainImage = product.path("main_image");
            if (!mainImage.isMissingNode()) {
                dto.setImageUrl(mainImage.path("link").asText());
            }
            // Get price from buybox_winner
            JsonNode buybox = product.path("buybox_winner");
            if (!buybox.isMissingNode()) {
                JsonNode priceNode = buybox.path("price");
                if (priceNode.has("value")) {
                    dto.setCurrentPrice(priceNode.path("value").asDouble());
                }
            }
            return dto;
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse product response", e);
        }
    }

    public Optional<String> extractAsinFromUrl(String url) {
        Pattern pattern = Pattern.compile("/(?:dp|gp/product)/([A-Z0-9]{10})");
        Matcher matcher = pattern.matcher(url);

        if (matcher.find()) {
            return Optional.of(matcher.group(1));
        }
        if (url.matches("[A-Z0-9]{10}")) {
            return Optional.of(url);
        }

        return Optional.empty();
    }

    public Product findEntityById(UUID productId) {
        return productRepository.findById(productId).orElse(null);
    }
}
