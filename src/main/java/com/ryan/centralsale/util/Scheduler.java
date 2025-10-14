package com.ryan.centralsale.util;

import com.ryan.centralsale.model.Product;
import com.ryan.centralsale.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class Scheduler {

    private final ProductService productService;

    private List<Product> getProductsToUpdate() {
        return productService.findAllUncheckedProducts();
    }

    // Testing Purposes
    private List<Product> getAllProducts() {
        return productService.findAllProducts();
    }

    @Scheduled(cron = "0 */30 * * * *")
    //@Scheduled(cron = "*/50 * * * * *")
    public void updateProductPrices() {
        //List<Product> productsToUpdate = getProductsToUpdate();
        List<Product> productsToUpdate = getAllProducts();
        for (Product product : productsToUpdate) {
            productService.updateProduct(product.getAsin());
            try {
                Thread.sleep(3000); // 3 seconds delay between requests
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @Scheduled(cron = "0 30 2 * * *")
    public void checkAndRemoveUnusedProducts() {
        List<Product> productsToRemove = productService.findUnusedProducts();
        for (Product product : productsToRemove) {
            productService.removeProduct(product);
        }
    }
}
