package com.sda.spring.database.dbtables;

public class StatementBuilider {
    public static String getCreateCustomerTableStatement(){
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE bank.customer ( ");
        sb.append("id SERIAL PRIMARY KEY,");
        sb.append("first_name character varying(140) NOT NULL,");
        sb.append("last_name character varying(140) NOT NULL,");
        sb.append("pesel character varying(11) NOT NULL,");
        sb.append("email character varying(90) NOT NULL,");
        sb.append("password character varying(70) NOT NULL);");
        return sb.toString();
    }

    public static String getCreateAccountTableStatement(){
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE bank.account ( ");
        sb.append("id SERIAL PRIMARY KEY,");
        sb.append("balance NUMERIC(9, 2) NOT NULL,");
        sb.append("currency character varying(20) DEFAULT 'PLN' NOT NULL);");
        return sb.toString();
    }

    public static String getCreateTransferHistoryTableStatement(){
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE bank.transfer_history ( ");
        sb.append("id SERIAL PRIMARY KEY,");
        sb.append("number_bank_account character varying(30) NOT NULL,");
        sb.append("date date NOT NULL,");
        sb.append("amount numeric(9, 2) NOT NULL);");
        return sb.toString();
    }

    public static String getConstraintsToAccount(){
        StringBuilder sb = new StringBuilder();
        sb.append("ALTER TABLE bank.account ");
        sb.append("add column customer_id integer NOT NULL,");
        sb.append("add constraint fk_customer_id FOREIGN KEY (customer_id) references bank.customer (id);");
        return sb.toString();
    }

    public static String getConstraintsToTransferHistoryDeparting(){
        StringBuilder sb = new StringBuilder();
        sb.append("ALTER TABLE bank.transfer_history ");
        sb.append("add column customer_id integer NOT NULL,");
        sb.append("add constraint fk_customer_id FOREIGN KEY (customer_id) references bank.customer (id);");
        return sb.toString();
    }




}
