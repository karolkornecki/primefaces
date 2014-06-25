package com.kornecki.enums;

/**
 * Type zamowienia
 *
 * @author karol.kornecki
 */
public enum OrderType {

    SIMPLE("Proste"), COMPLEX("Zlo\u017cone");

    /**
     * Nazwa (opis).
     */
    private String text;

    private OrderType(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return text;
    }
}
