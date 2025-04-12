package com.udea.bancoudea.controller;

import com.udea.bancoudea.DTO.TransactionDTO;
import com.udea.bancoudea.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    //para realizar una transferencia
    @PostMapping("/transfer")
    public ResponseEntity<TransactionDTO> transferMoney(@RequestBody TransactionDTO transactionDTO) {
        transactionDTO.setTimestamp(LocalDateTime.now()); // <-- AGREGAR ESTO AQUÍ
        TransactionDTO transaction = transactionService.transferMoney(transactionDTO);
        return ResponseEntity.ok(transaction);
    }

    //consultar el historial de transacciones por número de cuenta
    @GetMapping("/history/{accountNumber}")
    public ResponseEntity<List<TransactionDTO>> getTransactionHistory(@PathVariable String accountNumber) {
        List<TransactionDTO> transactions = transactionService.getTransactionsForAccount(accountNumber);
        return ResponseEntity.ok(transactions);
    }
}