package com.masterdetail.service;

import com.masterdetail.model.ItemDetail;
import com.masterdetail.Repository.ItemRepository;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
@Transactional
public class ItemService {
    @Autowired
    private ItemRepository repository;

    public List<ItemDetail> getAllItems(String docNumber) {
        List<ItemDetail> itemList = repository.findAll();
        List<ItemDetail> filteredList = itemList.stream()
                .filter(item -> item.getDocument().getDocNumber().equals(docNumber))
                .collect(Collectors.toList());
        return filteredList;
    }
    public ItemDetail addItem(ItemDetail item)
    {
        return repository.save(item);
    }

    public void deleteItem(Long itemId)
    {
        repository.deleteById(itemId);
    }
    public ItemDetail getItemById(Long itemId)
    {
        return repository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Document not found with id: " + itemId));
    }

    public ItemDetail updateItem(ItemDetail newItem, Long id)
    {
        ItemDetail item = getItemById(id);
        if(item!=null)
        {
            item.setItemId(newItem.getItemId());
            item.setName(newItem.getName());
            item.setItemSum(newItem.getItemSum());
        }
        return repository.save(item);
    }
}
