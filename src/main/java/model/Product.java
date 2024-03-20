package model;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class Product {
    private String id;
    private String name;
    private String description;
    private double price;

    public Product(String id, String name, String description, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public static Product fromMap(Map<String, String> map) {
        return new Product(
                map.get("id"),
                map.get("name"),
                map.get("description"),
                Double.parseDouble(map.get("price"))
        );
    }

    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        map.put("name", name);
        map.put("description", description);
        map.put("price", String.valueOf(price));
        return map;
    }
}
