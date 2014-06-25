package com.kornecki.services;

import com.kornecki.context.UserContext;
import com.kornecki.entity.Order;

/**
 * Serwis zamowien
 *
 * @author karol.kornecki
 */
public interface OrderService {

    public void readAllOrders(UserContext userContext);

    public void deleteOrder(Order order);

    public void updateOrder(Order order);

    public void saveOrder(Order order);

    public void findByCriteria(UserContext context);
}
