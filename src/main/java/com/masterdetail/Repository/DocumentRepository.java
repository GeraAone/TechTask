package com.masterdetail.Repository;
import com.masterdetail.model.DocumentMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DocumentRepository extends JpaRepository<DocumentMaster, Long> {
    Optional<DocumentMaster> findDocumentMasterByDocNumber(String docNumber);
    Optional<DocumentMaster> deleteDocumentMasterByDocNumber(String docNumber);
    @Modifying
    @Query("update DocumentMaster d set d.docNumber = ?1, d.date = ?2, d.notes = ?3 where d.docNumber = ?4")
    void setDocById(String newDocNumber, String date, String notes, String oldDocNumber);// метод написанный с использованием Spring Data ,для метода update()
}
