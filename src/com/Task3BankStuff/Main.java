package com.Task3BankStuff;

import java.io.*;
import java.util.*;

public class Main {

    public static  final String localDir = System.getProperty("user.dir");
    private  static  final List<AccountInfo> backAccountsAfterTransaction = new ArrayList<>();
    private  static  final Map<String, Integer> bankAccountsInfo = new TreeMap<>();

    public static void main(String[] args) throws IOException {

        ConvertToTreeMap();
        ProcessTransaction();
        TreeMapToArrayList();
        TransactionResultsToFile();
    }

    public static void ConvertToTreeMap() throws IOException {

        File sourceOfBankAccounts = new File(localDir + "\\src\\com\\Task3BankStuff\\sourceFiles\\initMoney.txt");
        BufferedReader br = new BufferedReader(new FileReader(sourceOfBankAccounts));

        try {
            br = new BufferedReader(new FileReader(sourceOfBankAccounts));
            String line;
            while ((line = br.readLine()) != null) {
                String[] array = line.split("\\|");
               bankAccountsInfo.put(array[0], Integer.parseInt(array[1]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            br.close();
        }
    }

    public static void ProcessTransaction() throws IOException {

        File transactions = new File(localDir +"\\src\\com\\Task3BankStuff\\sourceFiles\\transactions.txt");
        BufferedReader br = new BufferedReader(new FileReader(transactions));

        try {
            br = new BufferedReader(new FileReader(transactions));
            String line;
            while ((line = br.readLine()) != null) {
                String[] array = line.split("\\|");

                double transfer = Double.parseDouble(array[2]);
                int moneyFrom = (int)(bankAccountsInfo.get(array[0]) - transfer);
                int moneyTo = (int) (bankAccountsInfo.get(array[1]) + transfer);

                bankAccountsInfo.put(array[0], moneyFrom);
                bankAccountsInfo.put(array[1], moneyTo);

                    }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            br.close();
        }
    }

    public static void TreeMapToArrayList(){

        for (Map.Entry<String, Integer> entry : bankAccountsInfo.entrySet()) {
            backAccountsAfterTransaction.add(new AccountInfo(entry.getKey(), entry.getValue()));
        }
    }

    public static void TransactionResultsToFile() {

        Collections.sort(backAccountsAfterTransaction);

            try {

                FileWriter transactionResults = new FileWriter("D:\\JavaProjects\\Task3BankStuff\\src\\com\\Task3BankStuff\\sourceFiles\\result.txt");
                transactionResults.flush();

                for (AccountInfo info: backAccountsAfterTransaction) {
                    transactionResults.write("\n" + info.bankAccountId + "|" + info.money);
                }

                transactionResults.close();
                System.out.println("Done");

            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
    }

}
