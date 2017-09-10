package org.b1n.n26.controller;

import org.b1n.n26.model.TransactionModel;
import org.b1n.n26.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {

    @Autowired
    private TransactionService service;

    private static final ResponseEntity<Void> CREATED = ResponseEntity.status(HttpStatus.CREATED).build();

    private static final ResponseEntity<Void> NO_CONTENT = ResponseEntity.noContent().build();

    static final String URI_TRANSACTIONS = "/transactions";

    @PostMapping(path = URI_TRANSACTIONS)
    public ResponseEntity<Void> addTransaction(@RequestBody TransactionModel transaction) {
        if (service.addTransaction(transaction)) {
            return CREATED;
        }
        return NO_CONTENT;
    }
}
