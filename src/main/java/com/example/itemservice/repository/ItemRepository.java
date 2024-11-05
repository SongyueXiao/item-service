package com.example.itemservice.repository;

import com.example.itemservice.model.Item;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ItemRepository extends MongoRepository<Item, String> {

    // Find items by exact name
    List<Item> findByName(String name);

    // Find items containing a keyword in the name (case-insensitive)
    List<Item> findByNameContainingIgnoreCase(String keyword);

    // Find items within a price range
    List<Item> findByPriceBetween(double minPrice, double maxPrice);

    // Find items by Universal Product Code (UPC)
    Item findByUpc(String upc);

    // Find items with inventory count greater than a specified value
    List<Item> findByInventoryCountGreaterThan(int count);

    // Custom query using @Query annotation (e.g., find items by description)
    // @Query("{ 'description' : { $regex: ?0, $options: 'i' } }")
    // List<Item> findByDescriptionRegex(String regex);
}



