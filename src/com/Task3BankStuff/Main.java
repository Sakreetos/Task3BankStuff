package com.Task3BankStuff;

import java.io.*;
import java.util.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {

    private static final List<AccountInfo> bankAccountsInfo = new ArrayList<AccountInfo>();

    public static void main(String[] args) throws IOException {

        ConvertToArrayList();
        ProcessTransaction();
        Collections.sort(bankAccountsInfo,new MySalaryComp());
        TransactionResultsToFile();
    }

    public static void ConvertToArrayList() throws IOException {

        File sourceOfBankAccounts = new File("D:\\JavaProjects\\Task3BankStuff\\src\\com\\Task3BankStuff\\sourceFiles\\initMoney.txt");
        BufferedReader br = new BufferedReader(new FileReader(sourceOfBankAccounts));

        try {
            br = new BufferedReader(new FileReader(sourceOfBankAccounts));
            String line;
            while ((line = br.readLine()) != null) {
                String[] array = line.split("\\|");
                Main.bankAccountsInfo.add(new AccountInfo(array[0], Integer.parseInt(array[1])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            br.close();
        }
    }

    public static void ProcessTransaction() throws IOException {

        File transactions = new File("D:\\JavaProjects\\Task3BankStuff\\src\\com\\Task3BankStuff\\sourceFiles\\transactions.txt");
        BufferedReader br = new BufferedReader(new FileReader(transactions));

        try {
            br = new BufferedReader(new FileReader(transactions));
            String line;
            int transfer;
            while ((line = br.readLine()) != null) {
                String[] array = line.split("\\|");
                transfer =(int) Double.parseDouble(array[2]);

                for (AccountInfo fromAccount: bankAccountsInfo){
                    if (fromAccount.bankAccountId.equals(array[0])){
                        fromAccount.money -= transfer;
                        }
                    }
                for (AccountInfo toAccount : bankAccountsInfo){
                    if (toAccount.bankAccountId.equals(array[1])){
                        toAccount.money += transfer;
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            br.close();
        }
    }

    public static void TransactionResultsToFile() {
            try {
                FileWriter transactionResults = new FileWriter("D:\\JavaProjects\\Task3BankStuff\\src\\com\\Task3BankStuff\\sourceFiles\\result.txt");
                transactionResults.flush();
                for (AccountInfo info: bankAccountsInfo) {
                    transactionResults.write("\n" + info.bankAccountId + "|" + info.money);
                }
                transactionResults.close();
                System.out.println("Done");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
    }

    static class MySalaryComp implements Comparator<AccountInfo>{
        @Override
        public int compare(AccountInfo accountInfo, AccountInfo t1) {
            if (accountInfo.getMoney()> t1.money){
                return 1;
            }else return -1;
        }
    }

}
