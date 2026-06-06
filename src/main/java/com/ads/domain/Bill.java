package com.ads.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class Bill {
    private Long billId;
    private double amount;
    private boolean isPaid;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate issueDate;

    public Bill() {
    }

    public Bill(Long billId, double amount, boolean isPaid, LocalDate issueDate) {
        this.billId = billId;
        this.amount = amount;
        this.isPaid = isPaid;
        this.issueDate = issueDate;
    }

    public boolean isOverdueMoreThanTwelveMonths(LocalDate currentDate) {
        return !isPaid && issueDate != null && issueDate.plusMonths(12).isBefore(currentDate);
    }

    // region Getters and Setters

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    // endregion Getters and Setters

}