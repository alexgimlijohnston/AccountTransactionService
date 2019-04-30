package com.service.common.enums;

public enum Currency {

    GBP("GBP", "£"),
    EUR("EUR", "€"),
    USD("USD", "$");

    String name;
    String symbol;

    Currency(String name, String symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

}
