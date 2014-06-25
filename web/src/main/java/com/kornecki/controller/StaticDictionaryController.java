package com.kornecki.controller;

import com.kornecki.enums.OrderType;

import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

/**
 * Dostarcza statyczne slowniki dla ekranow JSF'a.
 *
 * @author karol.kornecki
 */
@Singleton
@Named
public class StaticDictionaryController {

    /**
     * Zwraca Liste typow zamowienia dla combo.
     */
    public List<SelectItem> getOrderTypeValues() {
        List<SelectItem> items = new ArrayList<SelectItem>();
        items.add(new SelectItem(OrderType.SIMPLE, OrderType.SIMPLE.getText()));
        items.add(new SelectItem(OrderType.COMPLEX, OrderType.COMPLEX.getText()));
        return items;
    }
}
