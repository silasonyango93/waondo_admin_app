package com.silasonyango.waondo.ui.models;

import com.silasonyango.waondo.services.transactionservice.dto.TransactionsResponseDto;

public class TransactionsItemModel extends TransactionsResponseDto {
    private int iconResourceId;

    public TransactionsItemModel(int iconResourceId, int transactionId, int studentId, String transactionDescription, double previousTermBalance, double previousAnnualBalance, double previousTotal, double nextTermBalance, double nextAnnualBalance, double nextTotal, String transactionDate, String staff, String studentName, double installmentAmount, double carryForwardAmount, String admissionNo, String academicClassLevelName, String classStreamName) {
        super(transactionId, studentId, transactionDescription, previousTermBalance, previousAnnualBalance, previousTotal, nextTermBalance, nextAnnualBalance, nextTotal, transactionDate, staff, studentName, installmentAmount, carryForwardAmount,admissionNo,academicClassLevelName,classStreamName);
    }

    public int getIconResourceId() {
        return iconResourceId;
    }

    public void setIconResourceId(int iconResourceId) {
        this.iconResourceId = iconResourceId;
    }
}
