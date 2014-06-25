package com.kornecki.services;

import com.kornecki.context.UserContext;
import com.kornecki.entity.Order;
import com.kornecki.enums.OrderType;
import org.hibernate.Criteria;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class UserContextMock implements UserContext {

    private List<Order> orders = new ArrayList<Order>();

    private Order criteria = new Order();

    @Override
    public Order getCriteria() {
        return criteria;
    }

    @Override
    public void setCriteria(Order criteria) {
        this.criteria = criteria;
    }

    @Override
    public List<Order> getOrders() {
        return orders;
    }

    @Override
    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public Order getNewOrder() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setNewOrder(Order newOrder) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isDefaultProceed() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setDefaultProceed(boolean defaultProceed) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
