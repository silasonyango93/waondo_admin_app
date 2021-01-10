package com.silasonyango.waondo.services.transactionservice.dto;

public class TransactionsByDateRangeRequestDto {
    private String transactionsStartDate;
    private String transactionsEndDate;

    public TransactionsByDateRangeRequestDto(String transactionsStartDate, String transactionsEndDate) {
        this.transactionsStartDate = transactionsStartDate;
        this.transactionsEndDate = transactionsEndDate;
    }

    public String getTransactionsStartDate() {
        return transactionsStartDate;
    }

    public void setTransactionsStartDate(String transactionsStartDate) {
        this.transactionsStartDate = transactionsStartDate;
    }

    public String getTransactionsEndDate() {
        return transactionsEndDate;
    }

    public void setTransactionsEndDate(String transactionsEndDate) {
        this.transactionsEndDate = transactionsEndDate;
    }
}
