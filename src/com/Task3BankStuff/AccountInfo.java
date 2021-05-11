package com.Task3BankStuff;

public class AccountInfo{
    public String bankAccountId;
    public int money;

    public AccountInfo(String bankAccountId, int money) {
        this.bankAccountId = bankAccountId;
        this.money = money;
    }

    public String getBankAccountId() {
        return bankAccountId;
    }

    public void setBankAccountId(String bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "\n" + bankAccountId + "|" + money;
    }
}
