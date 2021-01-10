package com.silasonyango.waondo.ui.destinations;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

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
    private AlertDialog adMenuDialog;
    private AlertDialog adSelectDate;
    private AlertDialog adSelectDateRange;
    private AlertDialog adTransactionDetails;
    private int mYear, mMonth, mDay;
    private String selectedTransactionDate = null;
    private String startDate = null;
    private String endDate = null;
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
                inflateMenuModal();
            }
        });
        fetchTransactionsByDate(Util.getToday());
    }

    public void fetchTransactionsByDate(String transactionsDate) {
        transactionsListView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        noTransactionsPlaceholder.setVisibility(View.GONE);
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
                                    transactionsResponseDto.getCarryForwardAmount(),
                                    transactionsResponseDto.getAdmissionNo(),
                                    transactionsResponseDto.getAcademicClassLevelName(),
                                    transactionsResponseDto.getClassStreamName()
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
        transactionsListView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        noTransactionsPlaceholder.setVisibility(View.GONE);
        TransactionsRepository transactionsRepository = RetrofitClientInstance.getRetrofitInstance(EndPoints.BASE_URL).create(TransactionsRepository.class);
        Call<List<TransactionsResponseDto>> call = transactionsRepository.fetchTransactionsByDateRange(new TransactionsByDateRangeRequestDto(transactionsStartDate,transactionsEndDate));
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
                                    transactionsResponseDto.getCarryForwardAmount(),
                                    transactionsResponseDto.getAdmissionNo(),
                                    transactionsResponseDto.getAcademicClassLevelName(),
                                    transactionsResponseDto.getClassStreamName()
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

    public void inflateMenuModal() {
        LayoutInflater inflater = (LayoutInflater)getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View inflatedView = inflater.inflate(R.layout.query_criteria_menu_layout, null);
        View selectDateViewItem = inflatedView.findViewById(R.id.select_date);
        View selectDateRangeViewItem = inflatedView.findViewById(R.id.select_date_range);

        selectDateViewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adMenuDialog.cancel();
                inflateSelectDateModal();
            }
        });

        selectDateRangeViewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adMenuDialog.cancel();
                inflateSelectDateRangeModal();
            }
        });
        openMenuModal(inflatedView);
    }

    public void openMenuModal(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setView(v);
        builder.setCancelable(true);
        adMenuDialog = builder.create();
        adMenuDialog.setCancelable(true);
        adMenuDialog.setCanceledOnTouchOutside(true);
        adMenuDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogBottom(adMenuDialog);
        adMenuDialog.show();
        Window window = adMenuDialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
    }

    public void dialogBottom(Dialog dialog){
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.BOTTOM;
        window.setAttributes(wlp);
    }


    public void inflateSelectDateModal() {
        LayoutInflater inflater = (LayoutInflater)getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View inflatedView = inflater.inflate(R.layout.select_date_configurer_layout, null);
        View dateSection = inflatedView.findViewById(R.id.calendar_section);
        TextView tvTransactionDate = inflatedView.findViewById(R.id.transaction_date);
        TextView tvSubmitButton = inflatedView.findViewById(R.id.btn_submit);

        dateSection.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                selectedTransactionDate = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                                tvTransactionDate.setText(Util.convertToUserFriendlyDate(selectedTransactionDate,"yyyy-MM-dd"));
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        tvSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adSelectDate.cancel();
                fetchTransactionsByDate(selectedTransactionDate);
            }
        });
        openSelectDateModal(inflatedView);
    }

    public void openSelectDateModal(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setView(v);
        builder.setCancelable(true);
        adSelectDate = builder.create();
        adSelectDate.setCancelable(true);
        adSelectDate.setCanceledOnTouchOutside(true);
        adSelectDate.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        adSelectDate.show();
        Window window = adSelectDate.getWindow();
        window.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
    }


    public void inflateSelectDateRangeModal() {
        LayoutInflater inflater = (LayoutInflater)getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View inflatedView = inflater.inflate(R.layout.date_range_configurer_layout, null);
        View startDateSection = inflatedView.findViewById(R.id.start_date_section);
        TextView tvStartDate = inflatedView.findViewById(R.id.start_date);

        View endDateSection = inflatedView.findViewById(R.id.end_date_section);
        TextView tvEndDate = inflatedView.findViewById(R.id.end_date);
        TextView tvSubmitButton = inflatedView.findViewById(R.id.btn_submit);

        startDateSection.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                startDate = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                                tvStartDate.setText(Util.convertToUserFriendlyDate(startDate,"yyyy-MM-dd"));
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });


        endDateSection.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                endDate = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                                tvEndDate.setText(Util.convertToUserFriendlyDate(endDate,"yyyy-MM-dd"));
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        tvSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adSelectDateRange.cancel();
                fetchTransactionsByDateRange(startDate,endDate);
            }
        });
        openSelectDateRangeModal(inflatedView);
    }

    public void openSelectDateRangeModal(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setView(v);
        builder.setCancelable(true);
        adSelectDateRange = builder.create();
        adSelectDateRange.setCancelable(true);
        adSelectDateRange.setCanceledOnTouchOutside(true);
        adSelectDateRange.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        adSelectDateRange.show();
        Window window = adSelectDateRange.getWindow();
        window.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onTransactionItemClicked(TransactionsItemModel transactionsItemModel) {
        inflateTransactionDetailsModal(transactionsItemModel);
    }

    public void inflateTransactionDetailsModal(TransactionsItemModel transactionsItemModel) {
        LayoutInflater inflater = (LayoutInflater)getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View inflatedView = inflater.inflate(R.layout.transaction_details_layout, null);

        TextView tvStudentName = inflatedView.findViewById(R.id.tv_student_name);
        TextView tvAdmissionNumber = inflatedView.findViewById(R.id.tv_admission_number);
        TextView tvClass = inflatedView.findViewById(R.id.tv_class);
        TextView tvInstallmentAmount = inflatedView.findViewById(R.id.tv_installment_amount);
        TextView tvStaff = inflatedView.findViewById(R.id.tv_staff);
        TextView tvDescription = inflatedView.findViewById(R.id.tv_description);
        TextView tvTransactionDate = inflatedView.findViewById(R.id.tv_transaction_date);
        TextView tvPrevTermBalance = inflatedView.findViewById(R.id.tv_prev_term_balance);
        TextView tvPrevAnnualBalance = inflatedView.findViewById(R.id.tv_prev_annual_balance);
        TextView tvPrevTotal = inflatedView.findViewById(R.id.tv_prev_total);
        TextView tvNextTermBalance = inflatedView.findViewById(R.id.tv_next_term_balance);
        TextView tvNextAnnualBalance = inflatedView.findViewById(R.id.tv_next_annual_balance);
        TextView tvNextTotal = inflatedView.findViewById(R.id.tv_next_total);

        tvStudentName.setText(transactionsItemModel.getStudentName());
        tvInstallmentAmount.setText(String.valueOf("KES " + Util.formatToCommaSeperatedValue(transactionsItemModel.getInstallmentAmount())));
        tvStaff.setText(transactionsItemModel.getStaff());
        tvDescription.setText(transactionsItemModel.getTransactionDescription());
        tvTransactionDate.setText(Util.convertToUserFriendlyDate(transactionsItemModel.getTransactionDate(),"yyyy-MM-dd"));
        tvPrevTermBalance.setText(String.valueOf(transactionsItemModel.getPreviousTermBalance()));
        tvPrevAnnualBalance.setText(String.valueOf(transactionsItemModel.getPreviousAnnualBalance()));
        tvPrevTotal.setText(String.valueOf(transactionsItemModel.getPreviousTotal()));
        tvNextTermBalance.setText(String.valueOf(transactionsItemModel.getNextTermBalance()));
        tvNextAnnualBalance.setText(String.valueOf(transactionsItemModel.getNextAnnualBalance()));
        tvNextTotal.setText(String.valueOf(transactionsItemModel.getNextTotal()));
        tvAdmissionNumber.setText(transactionsItemModel.getAdmissionNo());
        tvClass.setText(transactionsItemModel.getAcademicClassLevelName() + " " + transactionsItemModel.getClassStreamName());

        openTransactionDetailsModal(inflatedView);
    }

    public void openTransactionDetailsModal(View v) {
        int height = (int) (getResources().getDisplayMetrics().heightPixels * 0.75);
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setView(v);
        builder.setCancelable(true);
        adTransactionDetails = builder.create();
        adTransactionDetails.setCancelable(true);
        adTransactionDetails.setCanceledOnTouchOutside(true);
        adTransactionDetails.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogBottom(adTransactionDetails);
        adTransactionDetails.show();
        Window window = adTransactionDetails.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, height);
    }
}