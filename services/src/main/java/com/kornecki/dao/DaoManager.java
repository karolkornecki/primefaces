package com.kornecki.dao;

import com.kornecki.context.UserContext;
import com.kornecki.entity.Order;
import com.kornecki.enums.OrderType;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.Date;
import java.util.List;

/**
 * Dekorator do entityManagera.
 *
 * @author karol.kornecki
 */
@Singleton
public class DaoManager {

    private EntityManager entityManager;

    public synchronized void save(Order order) {
        init();
        EntityTransaction tx = null;
        try {
            tx = entityManager.getTransaction();
            tx.begin();
            entityManager.persist(order);
            tx.commit();
        } catch (Throwable e) {
            handleException(tx, e);
        }
    }

    public synchronized void update(Order order) {
        init();
        EntityTransaction tx = null;
        try {
            tx = entityManager.getTransaction();
            tx.begin();
            entityManager.merge(order);
            tx.commit();
        } catch (Throwable e) {
            handleException(tx, e);
        }
    }

    public synchronized void delete(Order order) {
        init();
        EntityTransaction tx = null;
        try {
            tx = entityManager.getTransaction();
            tx.begin();
            entityManager.remove(order);
            tx.commit();
        } catch (Throwable e) {
            handleException(tx, e);
        }
    }


    protected void init() {
        if (entityManager == null) {
            entityManager = Persistence.
                    createEntityManagerFactory("applicationDS").
                    createEntityManager();
        }
    }

    private void handleException(EntityTransaction tx, Throwable e) {
        if (tx != null && tx.isActive()) {
            tx.rollback();
        }
        throw new RuntimeException(e);
    }

    public List<?> findByNamedQuery(String namedQuery) {
        init();
        Query query = entityManager.createNamedQuery(namedQuery);
        return query.getResultList();
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public List<Order> findByCriteria(UserContext context) {
        Long id = context.getCriteria().getId();
        Date from = context.getCriteria().getDateFrom();
        Date to = context.getCriteria().getDateTo();
        OrderType type = context.getCriteria().getType();
        String serviceName = context.getCriteria().getServiceName();

        Criteria criteria = ((Session) getEntityManager().getDelegate()).createCriteria(Order.class);
        if (id != null) {
            criteria.add(Restrictions.eq("id", id));
        }
        if (from != null) {
            criteria.add(Restrictions.ge("createDate", from));
        }
        if (to != null) {
            criteria.add(Restrictions.le("createDate", to));
        }
        if (type != null) {
            criteria.add(Restrictions.eq("type", type));
        }
        if (serviceName != null && !serviceName.isEmpty()) {
            criteria.add(Restrictions.eq("serviceName", serviceName));
        }
        return criteria.list();
    }
}
