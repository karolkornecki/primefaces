package com.kornecki.controller;

import com.kornecki.context.UserContext;
import com.kornecki.entity.Order;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * {@link UserContext}
 *
 * @author karol.kornecki
 */
@ManagedBean(name = "userContext")
@SessionScoped
public class UserSessionContext implements Serializable, UserContext {

    /**
     * Kryteria wyszukiwania.
     */
    private Order criteria = new Order();

    /**
     * Dane nowego zamowienia
     */
    private Order newOrder = new Order();

    /**
     * Znalezione zamowienia
     */
    private List<Order> orders = new ArrayList<Order>();

    /**
     * Dane zostaly inicjalne pobrane.
     */
    private boolean defaultProceed;

    public Order getCriteria() {
        return criteria;
    }

    public void setCriteria(Order criteria) {
        this.criteria = criteria;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public Order getNewOrder() {
        return newOrder;
    }

    @Override
    public void setNewOrder(Order newOrder) {
        this.newOrder = newOrder;
    }

    @Override
    public boolean isDefaultProceed() {
        return defaultProceed;
    }

    @Override
    public void setDefaultProceed(boolean defaultProceed) {
        this.defaultProceed = defaultProceed;
    }
}
