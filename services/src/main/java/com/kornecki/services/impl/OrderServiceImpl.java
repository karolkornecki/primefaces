package com.kornecki.services.impl;

import com.kornecki.context.UserContext;
import com.kornecki.dao.DaoManager;
import com.kornecki.entity.Order;
import com.kornecki.enums.OrderType;
import com.kornecki.services.OrderService;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Serwis z operacjami na zamowieniach.
 *
 * @author karol.kornecki
 */
@RequestScoped
public class OrderServiceImpl implements OrderService {

    /**
     *
     */
    @Inject
    private DaoManager daoManager;

    @Override
    public void readAllOrders(UserContext userContext) {
        List<Order> orders = (List<Order>) daoManager.findByNamedQuery("Order.findAll");
        if (orders != null) {
            Collections.sort(orders);
        }
        userContext.setOrders(orders);
    }

    @Override
    public void deleteOrder(Order order) {
        daoManager.delete(order);
    }

    @Override
    public void saveOrder(Order order) {
        daoManager.save(order);
    }

    @Override
    public void updateOrder(Order order) {
        daoManager.update(order);
    }

    public void findByCriteria(UserContext context) {
        List<Order> orders = daoManager.findByCriteria(context);
        context.setOrders(orders);
    }
}
