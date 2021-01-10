package com.silasonyango.waondo.services.transactionservice.dto;

import com.google.gson.annotations.SerializedName;

public class TransactionsResponseDto {
    @SerializedName("transactionId")
    private int transactionId;

    @SerializedName("studentId")
    private int studentId;

    @SerializedName("transactionDescription")
    private String transactionDescription;

    @SerializedName("previousTermBalance")
    private double previousTermBalance;

    @SerializedName("previousAnnualBalance")
    private double previousAnnualBalance;

    @SerializedName("previousTotal")
    private double previousTotal;

    @SerializedName("nextTermBalance")
    private double nextTermBalance;

    @SerializedName("nextAnnualBalance")
    private double nextAnnualBalance;

    @SerializedName("nextTotal")
    private double nextTotal;

    @SerializedName("transactionDate")
    private String transactionDate;

    @SerializedName("staff")
    private String staff;

    @SerializedName("studentName")
    private String studentName;

    @SerializedName("installmentAmount")
    private double installmentAmount;

    @SerializedName("carryForwardAmount")
    private double carryForwardAmount;

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getTransactionDescription() {
        return transactionDescription;
    }

    public void setTransactionDescription(String transactionDescription) {
        this.transactionDescription = transactionDescription;
    }

    public double getPreviousTermBalance() {
        return previousTermBalance;
    }

    public void setPreviousTermBalance(double previousTermBalance) {
        this.previousTermBalance = previousTermBalance;
    }

    public double getPreviousAnnualBalance() {
        return previousAnnualBalance;
    }

    public void setPreviousAnnualBalance(double previousAnnualBalance) {
        this.previousAnnualBalance = previousAnnualBalance;
    }

    public double getPreviousTotal() {
        return previousTotal;
    }

    public void setPreviousTotal(double previousTotal) {
        this.previousTotal = previousTotal;
    }

    public double getNextTermBalance() {
        return nextTermBalance;
    }

    public void setNextTermBalance(double nextTermBalance) {
        this.nextTermBalance = nextTermBalance;
    }

    public double getNextAnnualBalance() {
        return nextAnnualBalance;
    }

    public void setNextAnnualBalance(double nextAnnualBalance) {
        this.nextAnnualBalance = nextAnnualBalance;
    }

    public double getNextTotal() {
        return nextTotal;
    }

    public void setNextTotal(double nextTotal) {
        this.nextTotal = nextTotal;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getStaff() {
        return staff;
    }

    public void setStaff(String staff) {
        this.staff = staff;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public double getInstallmentAmount() {
        return installmentAmount;
    }

    public void setInstallmentAmount(double installmentAmount) {
        this.installmentAmount = installmentAmount;
    }

    public double getCarryForwardAmount() {
        return carryForwardAmount;
    }

    public void setCarryForwardAmount(double carryForwardAmount) {
        this.carryForwardAmount = carryForwardAmount;
    }
}
