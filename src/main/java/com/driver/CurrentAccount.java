package com.driver;

import java.util.PriorityQueue;

public class CurrentAccount extends BankAccount{
    String tradeLicenseId; //consists of Uppercase English characters only

    public String getTradeLicenseId() {
        return tradeLicenseId;
    }
    public void setTradeLicenseId(String tradeLicenseId) {
        this.tradeLicenseId = tradeLicenseId;
    }

    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception

        super(name,balance,5000);
        this.tradeLicenseId = tradeLicenseId;
//        if(balance < 5000){
//            throw new Exception("Insufficient Balance");
//        }
        super.withdraw(0);

    }

    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception
        boolean valid = true;
        for(int i=0 ; i<tradeLicenseId.length()-1;i++){
            if(tradeLicenseId.charAt(i)==tradeLicenseId.charAt(i+1)){
                valid = false;
            }
        }
        int noOfChar[] = new int[26];
        if(!valid){
            for(int i=0 ; i<tradeLicenseId.length();i++){
                int idx = tradeLicenseId.charAt(i)-'A';
                noOfChar[idx]++;
            }
        }
        int n = tradeLicenseId.length();
        if(n%2==0){
            n =n/2 ;
            //System.out.println("even");
        }else{
            //System.out.println("odd"+" "+n/2);
            n =n/2 + 1;
        }
        for(int i=0 ; i<26;i++){
            if(noOfChar[i] > n){
                //System.out.println(n+" "+noOfChar[i]);
                throw new Exception("Valid License can not be generated");
            }
        }
        StringBuilder sb = new StringBuilder();
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) ->{
            return b[0]-a[0];
        });
        for(int i=0 ; i<26;i++){
            if(noOfChar[i]!=0){
                pq.add(new int[] { noOfChar[i], i });
            }
        }
        while (pq.size()>1){
            int a[] = pq.remove();
            int b[] = pq.remove();
            char x = (char)(a[1]+'A');
            sb.append(x);
            a[0]--;
            if(a[0]!=0) pq.add(new int[] {a[0] , a[1]});
            x=(char)(b[1]+'A');
            sb.append(x);

            b[0]--;
            if(b[0]!=0) pq.add(new int[] {b[0] ,b[1]});
        }
        if(pq.size()==1){
            int a[] = pq.remove();
            char x = (char)(a[1]+'A');
            sb.append(x);
        }
        tradeLicenseId = sb.toString();
    }

}
