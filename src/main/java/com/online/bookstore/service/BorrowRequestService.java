package com.online.bookstore.service;

import com.online.bookstore.model.BorrowRequest;
import com.online.bookstore.repository.BorrowRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BorrowRequestService {
    private BorrowRequestRepository borrowRequestRepository;
    @Autowired
    public BorrowRequestService(BorrowRequestRepository borrowRequestRepository){
        this.borrowRequestRepository=borrowRequestRepository;
    }
    public List<BorrowRequest> getAllBorrowRequests() {
        return borrowRequestRepository.findAll();
    }

    public BorrowRequest getBorrowRequestById(Long requestId) {
        return borrowRequestRepository.findById(requestId).orElse(null);
    }

    public BorrowRequest saveBorrowRequest(BorrowRequest borrowRequest) {
        return borrowRequestRepository.save(borrowRequest);
    }

    public void deleteBorrowRequest(Long requestId) {
        borrowRequestRepository.deleteById(requestId);
    }

}
