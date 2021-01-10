package com.silasonyango.waondo.services.transactionservice;

import com.silasonyango.waondo.services.transactionservice.dto.TransactionsByDateRangeRequestDto;
import com.silasonyango.waondo.services.transactionservice.dto.TransactionsByDateRequestDto;
import com.silasonyango.waondo.services.transactionservice.dto.TransactionsResponseDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface TransactionsRepository {
    @POST("transactions/transactions_by_date")
    Call<List<TransactionsResponseDto>> fetchTransactionsByDate(@Body TransactionsByDateRequestDto transactionsByDateRequestDto);

    @POST("transactions/transactions_by_date_range")
    Call<List<TransactionsResponseDto>> fetchTransactionsByDateRange(@Body TransactionsByDateRangeRequestDto transactionsByDateRangeRequestDto);

}
