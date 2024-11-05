package com.example.itemservice.controller;

import com.example.itemservice.model.Item;
import com.example.itemservice.service.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/items")
@Tag(name = "Item Service", description = "APIs for managing items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping
    @Operation(summary = "Get all items")
    public ResponseEntity<List<Item>> getAllItems() {
        List<Item> items = itemService.getAllItems();
        return ResponseEntity.ok(items);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get an item by ID")
    public ResponseEntity<Item> getItemById(@PathVariable String id) {
        return itemService.getItemById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @PostMapping
    @Operation(summary = "Create a new item")
    public ResponseEntity<Item> createItem(@Valid @RequestBody Item item) {
        Item savedItem = itemService.createItem(item);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedItem);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing item")
    public ResponseEntity<Item> updateItem(@PathVariable String id, @Valid @RequestBody Item itemDetails) {
        return itemService.updateItem(id, itemDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an item")
    public ResponseEntity<Void> deleteItem(@PathVariable String id) {
        if (itemService.deleteItem(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{id}/inventory")
    @Operation(summary = "Get inventory count for an item")
    public ResponseEntity<Integer> getInventoryCount(@PathVariable String id) {
        return itemService.getItemById(id)
                .map(item -> ResponseEntity.ok(item.getInventoryCount()))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @PostMapping("/{id}/inventory")
    @Operation(summary = "Update inventory count for an item")
    public ResponseEntity<Item> updateInventoryCount(@PathVariable String id, @RequestParam int count) {
        return itemService.updateInventoryCount(id, count)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @GetMapping("/search")
    @Operation(summary = "Search items by name keyword")
    public ResponseEntity<List<Item>> searchItemsByName(@RequestParam String keyword) {
        List<Item> items = itemService.searchItemsByName(keyword);
        return ResponseEntity.ok(items);
    }

    @GetMapping("/price-range")
    @Operation(summary = "Get items within a price range")
    public ResponseEntity<List<Item>> getItemsByPriceRange(@RequestParam double minPrice, @RequestParam double maxPrice) {
        List<Item> items = itemService.getItemsByPriceRange(minPrice, maxPrice);
        return ResponseEntity.ok(items);
    }

    @GetMapping("/upc/{upc}")
    @Operation(summary = "Get an item by UPC")
    public ResponseEntity<Item> getItemByUpc(@PathVariable String upc) {
        return itemService.getItemByUpc(upc)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @GetMapping("/inventory/above/{count}")
    @Operation(summary = "Get items with inventory count above a certain value")
    public ResponseEntity<List<Item>> getItemsWithInventoryAbove(@PathVariable int count) {
        List<Item> items = itemService.getItemsWithInventoryAbove(count);
        return ResponseEntity.ok(items);
    }
}

