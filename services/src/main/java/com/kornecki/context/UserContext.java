package com.kornecki.context;

import com.kornecki.entity.Order;

import java.util.List;

/**
 * Interfejs przekazywany w wywolaniach metod serwisow.
 * Zaimplementowany w warstwie 'web' jako obiekt sesyjny.
 * Trzyma stan interakcji uzytkownika z aplikacja.
 *
 * @author karol.kornecki
 */
public interface UserContext {

    public Order getCriteria();

    public void setCriteria(Order criteria);

    public List<Order> getOrders();

    public void setOrders(List<Order> orders);

    public Order getNewOrder();

    public void setNewOrder(Order newOrder);

    public boolean isDefaultProceed();

    public void setDefaultProceed(boolean defaultProceed);

}
