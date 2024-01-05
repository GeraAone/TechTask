package com.masterdetail.service;

import com.masterdetail.model.DocumentMaster;
import com.masterdetail.Repository.DocumentRepository;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
@Transactional
public class DocumentService
{

    @Autowired
    private DocumentRepository repository;

    public List<DocumentMaster> getAllDocs()
    {
        List<DocumentMaster> documentList= repository.findAll();
        return documentList;
    }
    public DocumentMaster addDoc(DocumentMaster document)
    {
        return repository.save(document);
    }

    public void deleteDocument(String docNumber)
    {
        repository.deleteDocumentMasterByDocNumber(docNumber);
    }

    public DocumentMaster getDocByNumber(String docNumber)
    {
        return repository.findDocumentMasterByDocNumber(docNumber).get();
    }

    public DocumentMaster updateDocument(String docNumber,DocumentMaster newDocument)
    {
        DocumentMaster document = repository.findDocumentMasterByDocNumber(docNumber).get();
        if(document!=null) {
            document.setDocNumber(newDocument.getDocNumber());
            document.setDate(newDocument.getDate());
            document.setNotes(newDocument.getNotes());
        }
        return repository.save(document);
    }

}