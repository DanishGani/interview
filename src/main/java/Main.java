import cache.ProductCache;
import model.Product;

public class Main {
    public static void main(String[] args) {
        ProductCache productCache = new ProductCache();
        String productId = "12345";

        productCache.fetchProductAndUpdateCache(productId);

        // Retrieve product from cache
        Product product = productCache.getProduct(productId);
        System.out.println(product);
    }
}
