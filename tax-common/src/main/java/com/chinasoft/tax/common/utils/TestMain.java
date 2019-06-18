package com.chinasoft.tax.common.utils;

public class TestMain {
    public static void main(String[] args) {

        int value = mixCommonMultiplier(5,2);
        System.out.println(value);
    }

    /**
     *
     * @param a
     * @param b
     * @return
     *
     * 2 ,3
     */
    static int mixCommonMultiplier(int a,int b){
        int temp = a<b?b:a;
        while(true){
            if(temp%a==0 && temp%b==0){
                return temp;
            }else{
                temp++;
            }
        }
    }
}
