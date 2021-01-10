package com.silasonyango.waondo.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.silasonyango.waondo.R;
import com.silasonyango.waondo.ui.models.TransactionsItemModel;
import com.silasonyango.waondo.util.Util;

import java.util.List;

public class TransactionsListAdapter extends ArrayAdapter<TransactionsItemModel> {
    LayoutInflater inflater;
    List<TransactionsItemModel> transactionsItemModelList;
    Context context;
    int layoutResourceId;

    private final TransactionsListAdapter.TransactionsListAdapterCallback transactionsListAdapterCallback;

    public interface TransactionsListAdapterCallback{
        void onTransactionItemClicked(TransactionsItemModel transactionsItemModel);
    }

    public TransactionsListAdapter(Context context, int layoutResourceId, List<TransactionsItemModel> transactionsItemModelList, TransactionsListAdapter.TransactionsListAdapterCallback transactionsListAdapterCallback) {
        super(context, layoutResourceId, transactionsItemModelList);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.transactionsItemModelList = transactionsItemModelList;
        this.transactionsListAdapterCallback = transactionsListAdapterCallback;
        inflater = (LayoutInflater) this.context.getSystemService(this.context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(layoutResourceId, null, false);
        TransactionsItemModel selectedTransaction = transactionsItemModelList.get(position);
        TextView tvStudentName = view.findViewById(R.id.student_name);
        TextView tvTransactionDescription = view.findViewById(R.id.transaction_description);
        TextView tvTransactionAmount = view.findViewById(R.id.transaction_amount);

        tvStudentName.setText(selectedTransaction.getStudentName());
        tvTransactionDescription.setText(selectedTransaction.getTransactionDescription());
        tvTransactionAmount.setText("KES "+ Util.formatToCommaSeperatedValue(selectedTransaction.getInstallmentAmount()));

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                transactionsListAdapterCallback.onTransactionItemClicked(selectedTransaction);
            }
        });

        return view;
    }
}
