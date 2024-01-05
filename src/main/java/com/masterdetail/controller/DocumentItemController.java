package com.masterdetail.controller;

import com.masterdetail.model.ItemDetail;
import com.masterdetail.model.DocumentMaster;
import com.masterdetail.service.DocumentItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.masterdetail.service.DocumentService;
import com.masterdetail.service.ItemService;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/documents")
@CrossOrigin(origins = "http://localhost:4200")
public class DocumentItemController
{
    private DocumentService documentService;
    private ItemService itemService;

    private DocumentItemService docItemService;
    public DocumentItemController(DocumentService documentService, ItemService itemService, DocumentItemService docItemService) {
        this.documentService = documentService;
        this.itemService = itemService;
        this.docItemService = docItemService;
    }

    @GetMapping
    public ResponseEntity<List<DocumentMaster>> getAllDocs()
    {
        List<DocumentMaster> docs = documentService.getAllDocs();
        return ResponseEntity.ok(docs);
    }
    @GetMapping("/{docNumber}")
    public ResponseEntity<DocumentMaster> getDocByNumber(@PathVariable("docNumber") String docNumber)
    {
        DocumentMaster doc = documentService.getDocByNumber(docNumber);
        if(doc != null) {
            return ResponseEntity.ok(doc);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<DocumentMaster> createDoc(@RequestBody DocumentMaster master) {
        DocumentMaster createdDoc = documentService.addDoc(master);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDoc);
    }

    @PutMapping("/{docNumber}")
    public ResponseEntity<DocumentMaster> updateDoc(@PathVariable("docNumber") String docNumber, @RequestBody DocumentMaster master) {
        DocumentMaster updatedDoc = documentService.updateDocument(docNumber, master);
        if (updatedDoc!=null) {
            return ResponseEntity.ok(updatedDoc);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{docNumber}")
    public ResponseEntity<Void> deleteDoc(@PathVariable("docNumber")  String docNumber) {
        documentService.deleteDocument(docNumber);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{docNumber}/items")
    public ResponseEntity<List<ItemDetail>> getItemsByDocNumber(@PathVariable("docNumber")  String docNumber) {
        List<ItemDetail> items = itemService.getAllItems(docNumber);
        return ResponseEntity.ok(items);
    }

    @PostMapping("/{docNumber}/items")
    public ResponseEntity<ItemDetail> createItem(@PathVariable("docNumber")  String docNumber, @RequestBody ItemDetail item) {
        ItemDetail createdItem = docItemService.addItem(docNumber,item);
        return ResponseEntity.ok(createdItem);
    }

    @GetMapping("/{docNumber}/items/{itemId}")
    public ResponseEntity<ItemDetail> getItemById(@PathVariable("docNumber")  String docNumber, @PathVariable("itemId")  Long itemId)
    {
        ItemDetail item = itemService.getItemById(itemId);
        if(Objects.equals(item.getDocument().getDocNumber(), docNumber))
        {
            return ResponseEntity.ok(item);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{docNumber}/items/{itemId}")
    public ResponseEntity<Void> deleteItem(@PathVariable("docNumber")  String docNumber, @PathVariable("itemId") Long itemId)
    {
        ItemDetail item = itemService.getItemById(itemId);
        if(Objects.equals(item.getDocument().getDocNumber(), docNumber))
        {
            docItemService.deleteItem(docNumber,itemId);
            return ResponseEntity.noContent().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{docNumber}/items/{itemId}")
    public ResponseEntity<ItemDetail> updateItem(@PathVariable("docNumber")  String docNumber, @PathVariable("itemId") Long itemId, @RequestBody ItemDetail item) {
        ItemDetail updatedItem = itemService.updateItem(item, itemId);
        if (updatedItem != null && updatedItem.getDocument().getDocNumber().equals(docNumber)) {
            return ResponseEntity.ok(updatedItem);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
