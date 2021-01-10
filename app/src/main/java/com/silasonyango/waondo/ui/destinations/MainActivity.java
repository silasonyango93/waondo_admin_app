package com.silasonyango.waondo.ui.destinations;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.silasonyango.waondo.R;
import com.silasonyango.waondo.services.retrofit.RetrofitClientInstance;
import com.silasonyango.waondo.config.EndPoints;
import com.silasonyango.waondo.services.transactionservice.TransactionsRepository;
import com.silasonyango.waondo.services.transactionservice.dto.TransactionsByDateRangeRequestDto;
import com.silasonyango.waondo.services.transactionservice.dto.TransactionsByDateRequestDto;
import com.silasonyango.waondo.services.transactionservice.dto.TransactionsResponseDto;
import com.silasonyango.waondo.ui.adapters.TransactionsListAdapter;
import com.silasonyango.waondo.ui.models.TransactionsItemModel;
import com.silasonyango.waondo.util.Util;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity implements TransactionsListAdapter.TransactionsListAdapterCallback {
    ListView transactionsListView;
    ProgressBar progressBar;
    FloatingActionButton floatingActionButton;
    View noTransactionsPlaceholder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        transactionsListView = findViewById(R.id.transactions_listview);
        progressBar = findViewById(R.id.progress_bar);
        floatingActionButton = findViewById(R.id.fab);
        noTransactionsPlaceholder = findViewById(R.id.no_transactions_placeholder);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        fetchTransactionsByDate(Util.getToday());
    }

    public void fetchTransactionsByDate(String transactionsDate) {
        TransactionsRepository transactionsRepository = RetrofitClientInstance.getRetrofitInstance(EndPoints.BASE_URL).create(TransactionsRepository.class);
        Call<List<TransactionsResponseDto>> call = transactionsRepository.fetchTransactionsByDate(new TransactionsByDateRequestDto(transactionsDate));
        call.enqueue(new Callback<List<TransactionsResponseDto>>() {
            @Override
            public void onResponse(Call<List<TransactionsResponseDto>> call, retrofit2.Response<List<TransactionsResponseDto>> response) {
                if (response.isSuccessful() && response.code() == 200) {
                    if (response.body().size() > 0) {
                        List<TransactionsItemModel> transactionsItemModelList = new ArrayList<>();
                        for (TransactionsResponseDto transactionsResponseDto : response.body()) {
                            transactionsItemModelList.add(new TransactionsItemModel(
                                    R.drawable.ic_credit_icon,
                                    transactionsResponseDto.getTransactionId(),
                                    transactionsResponseDto.getStudentId(),
                                    transactionsResponseDto.getTransactionDescription(),
                                    transactionsResponseDto.getPreviousTermBalance(),
                                    transactionsResponseDto.getPreviousAnnualBalance(),
                                    transactionsResponseDto.getPreviousTotal(),
                                    transactionsResponseDto.getNextTermBalance(),
                                    transactionsResponseDto.getNextAnnualBalance(),
                                    transactionsResponseDto.getNextTotal(),
                                    transactionsResponseDto.getTransactionDate(),
                                    transactionsResponseDto.getStaff(),
                                    transactionsResponseDto.getStudentName(),
                                    transactionsResponseDto.getInstallmentAmount(),
                                    transactionsResponseDto.getCarryForwardAmount()
                            ));
                        }
                        TransactionsListAdapter transactionsListAdapter = new TransactionsListAdapter(getBaseContext(),R.layout.transaction_item,transactionsItemModelList, MainActivity.this);
                        transactionsListView.setAdapter(transactionsListAdapter);
                        transactionsListView.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);
                        noTransactionsPlaceholder.setVisibility(View.GONE);
                    } else {
                        transactionsListView.setVisibility(View.GONE);
                        progressBar.setVisibility(View.GONE);
                        noTransactionsPlaceholder.setVisibility(View.VISIBLE);
                    }
                }
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

    @Override
    public void onTransactionItemClicked(TransactionsItemModel transactionsItemModel) {

    }
}