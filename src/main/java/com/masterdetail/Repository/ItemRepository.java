package com.masterdetail.Repository;

import com.masterdetail.model.ItemDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ItemRepository extends JpaRepository<ItemDetail,Long> {
    @Query("SELECT i FROM ItemDetail i WHERE i.document.docNumber = ?1")
    List<ItemDetail> findByDocumentNumber(String docNumber);
}