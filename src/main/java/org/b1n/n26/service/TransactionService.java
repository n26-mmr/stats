package org.b1n.n26.service;

import org.b1n.n26.model.TransactionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    @Autowired
    private StorageService storage;

    public boolean addTransaction(TransactionModel t) {
        if (t.isTooOld()) {
            return false;
        }
        storage.add(t);
        return true;
    }
}
