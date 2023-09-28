package com.driver;
public class Main {
    public static void main(String[] args) {
        try{
            CurrentAccount curr = new CurrentAccount("Adnan",7000,"AAABBCA");
            //System.out.println(curr.getBalance());
            System.out.println(curr.getTradeLicenseId());
            curr.validateLicenseId();
            System.out.println(curr.getTradeLicenseId());
            System.out.println(curr.generateAccountNumber(9,0));

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}