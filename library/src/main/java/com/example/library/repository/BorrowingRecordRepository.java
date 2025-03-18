package com.example.library.repository;

import com.example.library.entities.BorrowingRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord,Long> {
    BorrowingRecord findByBookIdAndPatronId(long bookId, long patronId);
}
