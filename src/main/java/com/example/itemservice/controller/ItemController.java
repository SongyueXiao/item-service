package com.example.itemservice.controller;

import com.example.itemservice.model.Item;
import com.example.itemservice.repository.ItemRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/items")
@Tag(name = "Item Service", description = "APIs for managing items")
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping
    @Operation(summary = "Get all items")
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get an item by ID")
    public ResponseEntity<Item> getItemById(@PathVariable String id) {
        Optional<Item> item = itemRepository.findById(id);
        return item.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Create a new item")
    public Item createItem(@RequestBody Item item) {
        return itemRepository.save(item);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing item")
    public ResponseEntity<Item> updateItem(@PathVariable String id, @RequestBody Item itemDetails) {
        Optional<Item> optionalItem = itemRepository.findById(id);
        if (optionalItem.isPresent()) {
            Item item = optionalItem.get();
            item.setName(itemDetails.getName());
            item.setDescription(itemDetails.getDescription());
            item.setPrice(itemDetails.getPrice());
            item.setImageUrl(itemDetails.getImageUrl());
            item.setUpc(itemDetails.getUpc());
            item.setInventoryCount(itemDetails.getInventoryCount());
            Item updatedItem = itemRepository.save(item);
            return ResponseEntity.ok(updatedItem);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an item")
    public ResponseEntity<Void> deleteItem(@PathVariable String id) {
        if (itemRepository.existsById(id)) {
            itemRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/inventory")
    @Operation(summary = "Get inventory count for an item")
    public ResponseEntity<Integer> getInventoryCount(@PathVariable String id) {
        Optional<Item> item = itemRepository.findById(id);
        return item.map(i -> ResponseEntity.ok(i.getInventoryCount()))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/inventory")
    @Operation(summary = "Update inventory count for an item")
    public ResponseEntity<Item> updateInventoryCount(@PathVariable String id, @RequestParam int count) {
        Optional<Item> optionalItem = itemRepository.findById(id);
        if (optionalItem.isPresent()) {
            Item item = optionalItem.get();
            item.setInventoryCount(count);
            Item updatedItem = itemRepository.save(item);
            return ResponseEntity.ok(updatedItem);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
