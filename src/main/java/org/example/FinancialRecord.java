package org.example;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "financial_records")
public class FinancialRecord {

    private int id;
    private double amount;

    public FinancialRecord(){

    }
    public FinancialRecord(int id, double amount){

        this.id=id;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}



