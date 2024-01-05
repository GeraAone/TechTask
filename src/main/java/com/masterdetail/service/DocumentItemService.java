package com.masterdetail.service;

import com.masterdetail.model.DocumentMaster;
import com.masterdetail.model.ItemDetail;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
@Transactional
public class DocumentItemService {
    @Autowired
    private DocumentService docService;
    @Autowired
    private ItemService itemService;

    public ItemDetail addItem(String docNumber, ItemDetail item) {
        DocumentMaster document = docService.getDocByNumber(docNumber);

        if (document != null && item.getDocument().getDocNumber().equals(docNumber)) {
            item.setDocument(document);
            return itemService.addItem(item);
        } else {
            throw new RuntimeException("Item does not belong to document or not found");
        }
    }
    public void deleteItem(String docNumber, Long itemId)
    {
        DocumentMaster document = docService.getDocByNumber(docNumber);
        if (document != null) {
            document.getItems().remove(itemService.getItemById(itemId));
            itemService.deleteItem(itemId);
        } else {
            throw new RuntimeException("Item not found");
        }

    }

}
