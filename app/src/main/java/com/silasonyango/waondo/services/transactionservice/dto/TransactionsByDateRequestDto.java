package com.silasonyango.waondo.services.transactionservice.dto;

public class TransactionsByDateRequestDto {
    private String transactionDate;

    public TransactionsByDateRequestDto(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }
}
