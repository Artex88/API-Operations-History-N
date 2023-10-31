package ru.netology.Alex_Zadevalov;

import lombok.Data;

@Data
public class Operation implements ConsolePrintable {
    private int id;
    private int sum;
    private String currency;
    private String merchant;

    public Operation(int id, int sum, String currency, String merchant) {
        this.id = id;
        this.sum = sum;
        this.currency = currency;
        this.merchant = merchant;
    }

    public Operation() {
    }
    public void printToConsole(){
        System.out.println("id= " + id +
                " Sum: " + sum +
                " Currency: " + currency +
                " Merchant: " + merchant);
    }
    @Override
    public String toString(){
        return  "id= " + id +
                " Sum: " + sum +
                " Currency: " + currency +
                " Merchant: " + merchant;
    }
}
