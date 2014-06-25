package com.kornecki.dao;

import com.kornecki.context.UserContext;
import com.kornecki.entity.Order;
import com.kornecki.enums.OrderType;
import com.kornecki.services.UserContextMock;
import junit.framework.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * @author karol.kornecki
 */
@RunWith(Arquillian.class)
public class DaoManagerTestCase {

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(DaoManager.class)
                .addAsResource("META-INF/test-persistence.xml");
    }

    @Inject
    private DaoManager daoManager;

    @Test
    public void testGetEntityManagerNull() {
        EntityManager entityManager = daoManager.getEntityManager();
        Assert.assertNull(entityManager);
    }


    @Test
    public void testReadOrder() {
        //when
        daoManager.init();
        Order order = daoManager.getEntityManager().find(Order.class, 1L);
        //then
        Assert.assertNotNull(order);
        Assert.assertEquals("Macbook PRO Retina", order.getServiceName());
        Assert.assertEquals(OrderType.COMPLEX, order.getType());
    }


    @Test
    public void testReadAllOrders() {
        daoManager.init();
        List<Order> orders = (List<Order>) daoManager.findByNamedQuery("Order.findAll");

        Assert.assertEquals(25, orders.size());
    }

    @Test
    public void testFindBySimpleType() {
        //given
        UserContext context = new UserContextMock();
        context.getCriteria().setType(OrderType.SIMPLE);
        //when
        daoManager.init();
        List<Order> orders = daoManager.findByCriteria(context);
        //then
        Assert.assertEquals(14, orders.size());
    }

    @Test
    public void testFindByComplexType() {
        //given
        UserContext context = new UserContextMock();
        context.getCriteria().setType(OrderType.COMPLEX);
        //when
        daoManager.init();
        List<Order> orders = daoManager.findByCriteria(context);
        //then
        Assert.assertEquals(11, orders.size());
    }

    @Test
    public void testFindByNonExistingId() {
        //given
        UserContext context = new UserContextMock();
        context.getCriteria().setId(50L);
        //when
        daoManager.init();
        List<Order> orders = daoManager.findByCriteria(context);
        //then
        Assert.assertEquals(0, orders.size());
    }

    @Test
    public void testFindByExistingId() {
        //given
        UserContext context = new UserContextMock();
        context.getCriteria().setId(1L);
        //when
        daoManager.init();
        List<Order> orders = daoManager.findByCriteria(context);
        //then
        Assert.assertEquals(1, orders.size());
    }

    @Test
    public void testFindByEmptyCriteria() {
        //given
        UserContext context = new UserContextMock();
        //when
        daoManager.init();
        List<Order> orders = daoManager.findByCriteria(context);
        //then
        Assert.assertEquals(25, orders.size());
    }

    @Test
    public void testFindByDateFromYearCriteria() {
        //given
        UserContext context = new UserContextMock();
        Calendar calendar = new GregorianCalendar(2013, 00, 01);   //january
        Date dateFrom = calendar.getTime();
        context.getCriteria().setDateFrom(dateFrom);
        //when
        daoManager.init();
        List<Order> orders = daoManager.findByCriteria(context);
        //then
        Assert.assertEquals(13, orders.size());
    }

    @Test
    public void testFindByDateFromMonthCriteria() {
        //given
        UserContext context = new UserContextMock();
        Calendar calendar = new GregorianCalendar(2013, 01, 01);    //february
        Date dateFrom = calendar.getTime();
        context.getCriteria().setDateFrom(dateFrom);
        //when
        daoManager.init();
        List<Order> orders = daoManager.findByCriteria(context);
        //then
        Assert.assertEquals(10, orders.size());
    }

    @Test
    public void testFindByDateToCriteria() {
        //given
        UserContext context = new UserContextMock();
        Calendar calendar = new GregorianCalendar(2013, 00, 01);
        Date dateTo = calendar.getTime();
        context.getCriteria().setDateTo(dateTo);
        //when
        daoManager.init();
        List<Order> orders = daoManager.findByCriteria(context);
        //then
        Assert.assertEquals(12, orders.size());
    }

    @Test
    public void testFindByDateFromDateToCriteria() {
        //given
        UserContext context = new UserContextMock();
        Calendar calendarFrom = new GregorianCalendar(2012, 00, 01);
        Date dateFrom = calendarFrom.getTime();
        context.getCriteria().setDateFrom(dateFrom) ;
        Calendar calendarTo = new GregorianCalendar(2013, 00, 01);
        Date dateTo = calendarTo.getTime();
        context.getCriteria().setDateTo(dateTo);
        //when
        daoManager.init();
        List<Order> orders = daoManager.findByCriteria(context);
        //then
        Assert.assertEquals(9, orders.size());
    }

}
