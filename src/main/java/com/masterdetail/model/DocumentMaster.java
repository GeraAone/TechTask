package com.masterdetail.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Entity
@Data
@NoArgsConstructor
@Table(name = "documents")
public class DocumentMaster {

    @Id
    @Column(name = "document_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long docId;
    @Column(name = "document_number", unique = true)
    private String docNumber;
    @Column(name = "date")
    private String date;
    //@JsonFormat(pattern = "dd/MM/yyyy") private LocalDate date;
    @OneToMany(mappedBy = "document", cascade = CascadeType.ALL,
            orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    List<ItemDetail> items = new ArrayList<>();
    @Column(name = "doc_sum")
    private Long docSum;
    @Column(name = "notes")
    private String notes;

    @PostLoad
    private void calculateDocSum() {
        AtomicReference<Long> sum = new AtomicReference<>(0L);
        if (!items.isEmpty()) {
            this.items.forEach(item -> {
                Long itemSum = item.getItemSum();
                if (itemSum != null) {
                    sum.updateAndGet(v -> v + itemSum);
                }
            });
            this.docSum = sum.get();
        } else {
            this.docSum = 0L;
        }
    }
    public DocumentMaster(String date, String docNumber, List<ItemDetail> itemList, String notes)
    {
        this.docNumber = docNumber;
        this.date = date;
        this.items = itemList;
        this.notes = notes;

    }

}
