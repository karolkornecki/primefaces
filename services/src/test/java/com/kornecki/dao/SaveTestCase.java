package com.kornecki.dao;

import com.kornecki.entity.Order;
import com.kornecki.enums.OrderType;
import junit.framework.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.Date;

/**
 * Testy zapisu.
 *
 * @author karol.kornecki
 */
@RunWith(Arquillian.class)
public class SaveTestCase {

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(DaoManager.class)
                .addAsResource("META-INF/test-persistence.xml");
    }

    @Inject
    private DaoManager daoManager;

    @Test
    public void testSaveOrder() {
        //given
        Order order = new Order();
        Date createDate = new Date();
        order.setServiceName("test");
        order.setCreateDate(createDate);
        order.setType(OrderType.SIMPLE);
        daoManager.save(order);
        //when
        Order savedOrder = daoManager.getEntityManager().find(Order.class, 26L);
        //then
        Assert.assertNotNull(savedOrder);
        Assert.assertEquals(createDate, savedOrder.getCreateDate());
        Assert.assertEquals("test", savedOrder.getServiceName());
        Assert.assertEquals(OrderType.SIMPLE, savedOrder.getType());
    }
}
