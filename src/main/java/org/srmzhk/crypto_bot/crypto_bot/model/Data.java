package org.srmzhk.crypto_bot.crypto_bot.model;

import java.sql.Date;

public class Data {
    private int ID;
    private String symbols;
    private int price;
    private Date date;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getSymbols() {
        return symbols;
    }

    public void setSymbols(String symbols) {
        this.symbols = symbols;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Data{" +
                "ID=" + ID +
                ", symbols='" + symbols + '\'' +
                ", price=" + price +
                ", date=" + date +
                '}';
    }
}
