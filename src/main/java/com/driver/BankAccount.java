package com.driver;

import java.util.Random;

public class BankAccount {

    private String name;
    private double balance;
    private double minBalance;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    public double getMinBalance() {
        return minBalance;
    }
    public void setMinBalance(double minBalance) {
        this.minBalance = minBalance;
    }
    public BankAccount(String name, double balance, double minBalance) {
        this.name=name;
        this.balance=balance;
        this.minBalance=minBalance;
    }

    public String generateAccountNumber(int digits, int sum) throws Exception{
        //Each digit of an account number can lie between 0 and 9 (both inclusive)
        //Generate account number having given number of 'digits' such that the sum of digits is equal to 'sum'
        //If it is not possible, throw "Account Number can not be generated" exception
        StringBuilder sb = new StringBuilder("");
        if(digits<= 0 || sum < 0 || digits*9 < sum){
            throw new Exception("Account Number can not be generated");
        }
        Random rd = new Random();
        for(int i=0 ;i<digits-1 ; i++){
            int maxDigit = Math.min(9, sum - (digits-1-i));
            int digit = rd.nextInt(maxDigit+1);
            sb.append(digit);
            sum -= digit;
        }
        if(sum>9){
            throw new Exception("Account Number can not be generated");
        }
        sb.append(sum);
        return sb.toString();
    }

    public void deposit(double amount) {
        //add amount to balance
        this.balance += amount;
    }

    public void withdraw(double amount) throws Exception {
        // Remember to throw "Insufficient Balance" exception, if the remaining amount would be less than minimum balance
        if(this.balance - amount < this.minBalance ){
            throw new Exception("Insufficient Balance");
        }else{
            this.balance = this.balance - amount;
        }
    }

}