package cache;

import model.Product;
import redis.clients.jedis.Jedis;

public class ProductCache {
    private Jedis jedis;
    private int expirationTime = 3600; // expiration time in seconds

    public ProductCache() {
        jedis = new Jedis("localhost", 6200);
    }

    public void fetchProductAndUpdateCache(String productId) {
        String productKey = "product:" + productId;
        if (!jedis.exists(productKey)) {
            Product product = fetchProductFromDatabase(productId);
            jedis.hmset(productKey, product.toMap());
            jedis.expire(productKey, expirationTime);
        }
    }

    public Product getProduct(String productId) {
        String productKey = "product:" + productId;
        if (jedis.exists(productKey)) {
            return Product.fromMap(jedis.hgetAll(productKey));
        } else {
            Product product = fetchProductFromDatabase(productId);
            jedis.hmset(productKey, product.toMap());
            jedis.expire(productKey, expirationTime);
            return product;
        }
    }

    private Product fetchProductFromDatabase(String productId) {
        return new Product(productId, "Product Name", "Product Description", 10.99);
    }

    public void invalidateCache(String productId) {
        jedis.del("product:" + productId);
    }
}
