package com.example.itemservice.service;

import com.example.itemservice.model.Item;
import com.example.itemservice.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    /**
     * Retrieve all items.
     * @return a list of all items.
     */
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    /**
     * Retrieve an item by ID.
     * @param id the ID of the item to retrieve.
     * @return an Optional containing the item if found, otherwise empty.
     */
    public Optional<Item> getItemById(String id) {
        return itemRepository.findById(id);
    }

    /**
     * Create a new item.
     * @param item the item to create.
     * @return the saved item.
     */
    public Item createItem(Item item) {
        return itemRepository.save(item);
    }

    /**
     * Update an existing item.
     * @param id the ID of the item to update.
     * @param itemDetails the updated item details.
     * @return an Optional containing the updated item if found, otherwise empty.
     */
    public Optional<Item> updateItem(String id, Item itemDetails) {
        return itemRepository.findById(id).map(item -> {
            item.setName(itemDetails.getName());
            item.setDescription(itemDetails.getDescription());
            item.setPrice(itemDetails.getPrice());
            item.setImageUrl(itemDetails.getImageUrl());
            item.setUpc(itemDetails.getUpc());
            item.setInventoryCount(itemDetails.getInventoryCount());
            return itemRepository.save(item);
        });
    }

    /**
     * Delete an item by ID.
     * @param id the ID of the item to delete.
     * @return true if the item was deleted, false if the item was not found.
     */
    public boolean deleteItem(String id) {
        if (itemRepository.existsById(id)) {
            itemRepository.deleteById(id);
            return true;
        }
        return false;
    }

    /**
     * Update inventory count for an item.
     * @param id the ID of the item to update.
     * @param count the new inventory count.
     * @return an Optional containing the updated item if found, otherwise empty.
     */
    public Optional<Item> updateInventoryCount(String id, int count) {
        return itemRepository.findById(id).map(item -> {
            item.setInventoryCount(count);
            return itemRepository.save(item);
        });
    }

    /**
     * Search items by name keyword (case-insensitive).
     * @param keyword the keyword to search for in item names.
     * @return a list of items matching the keyword.
     */
    public List<Item> searchItemsByName(String keyword) {
        return itemRepository.findByNameContainingIgnoreCase(keyword);
    }

    /**
     * Retrieve items within a specified price range.
     * @param minPrice the minimum price.
     * @param maxPrice the maximum price.
     * @return a list of items within the price range.
     */
    public List<Item> getItemsByPriceRange(double minPrice, double maxPrice) {
        return itemRepository.findByPriceBetween(minPrice, maxPrice);
    }

    /**
     * Retrieve an item by UPC.
     * @param upc the Universal Product Code of the item.
     * @return an Optional containing the item if found, otherwise empty.
     */
    public Optional<Item> getItemByUpc(String upc) {
        Item item = itemRepository.findByUpc(upc);
        return Optional.ofNullable(item);
    }

    /**
     * Retrieve items with inventory count above a certain threshold.
     * @param count the minimum inventory count.
     * @return a list of items with inventory count greater than the specified value.
     */
    public List<Item> getItemsWithInventoryAbove(int count) {
        return itemRepository.findByInventoryCountGreaterThan(count);
    }
}
