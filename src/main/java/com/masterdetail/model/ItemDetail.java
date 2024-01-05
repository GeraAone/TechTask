package com.masterdetail.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "items")
public class ItemDetail {
    @Id
    @Column(name = "item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;
    @ManyToOne()
    @JsonBackReference
    @JoinColumn(name = "document_number",columnDefinition = "varchar(255)"
            ,referencedColumnName = "document_number",nullable = false)
    private DocumentMaster document;
    @Column(name = "item_sum")
    private Long itemSum;
    @Column(name = "name")
    private String name;

    public ItemDetail(DocumentMaster document, Long itemSum, String name)
    {
        this.document = document;
        this.itemSum = itemSum;
        this.name = name;
    }

}
