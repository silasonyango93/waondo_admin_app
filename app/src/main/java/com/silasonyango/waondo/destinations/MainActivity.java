package com.silasonyango.waondo.destinations;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.silasonyango.waondo.Repository;
import com.silasonyango.waondo.services.retrofit.RetrofitClientInstance;
import com.silasonyango.waondo.SuccessfulInsertionResponse;
import com.silasonyango.waondo.config.EndPoints;
import com.silasonyango.waondo.services.transactionservice.TransactionsRepository;
import com.silasonyango.waondo.services.transactionservice.dto.TransactionsByDateRangeRequestDto;
import com.silasonyango.waondo.services.transactionservice.dto.TransactionsByDateRequestDto;
import com.silasonyango.waondo.services.transactionservice.dto.TransactionsResponseDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        btnSubmit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                submitData(etFirstName.getText().toString(),
//                        etMiddleName.getText().toString(),
//                        etSurname.getText().toString(),
//                        etEmail.getText().toString()
//                );
//            }
//        });
        fetchTransactionsByDateRange("2020-06-15 18:59:12", "2021-01-07 09:20:22");
    }

    public void fetchTransactionsByDate(String transactionsDate) {
        TransactionsRepository transactionsRepository = RetrofitClientInstance.getRetrofitInstance(EndPoints.BASE_URL).create(TransactionsRepository.class);
        Call<List<TransactionsResponseDto>> call = transactionsRepository.fetchTransactionsByDate(new TransactionsByDateRequestDto(transactionsDate));
        call.enqueue(new Callback<List<TransactionsResponseDto>>() {
            @Override
            public void onResponse(Call<List<TransactionsResponseDto>> call, retrofit2.Response<List<TransactionsResponseDto>> response) {
                System.out.println();
            }

            @Override
            public void onFailure(Call<List<TransactionsResponseDto>> call, Throwable t) {
                System.out.println();
            }
        });
    }

    public void fetchTransactionsByDateRange(String transactionsStartDate, String transactionsEndDate) {
        TransactionsRepository transactionsRepository = RetrofitClientInstance.getRetrofitInstance(EndPoints.BASE_URL).create(TransactionsRepository.class);
        Call<List<TransactionsResponseDto>> call = transactionsRepository.fetchTransactionsByDateRange(new TransactionsByDateRangeRequestDto(transactionsStartDate,transactionsEndDate));
        call.enqueue(new Callback<List<TransactionsResponseDto>>() {
            @Override
            public void onResponse(Call<List<TransactionsResponseDto>> call, retrofit2.Response<List<TransactionsResponseDto>> response) {
                System.out.println();
            }

            @Override
            public void onFailure(Call<List<TransactionsResponseDto>> call, Throwable t) {
                System.out.println();
            }
        });
    }
}