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

/**
 *  @author karol.kornecki
 */
@RunWith(Arquillian.class)
public class DeleteTestCase {


    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(DaoManager.class)
                .addAsResource("META-INF/test-persistence.xml");
    }

    @Inject
    private DaoManager daoManager;


    @Test
    public void testDeleteOrder() {
        //given
        daoManager.init();
        Order order = daoManager.getEntityManager().find(Order.class, 1L);
        Assert.assertNotNull(order);
        Assert.assertEquals("Macbook PRO Retina", order.getServiceName());
        Assert.assertEquals(OrderType.COMPLEX, order.getType());

        //when
        daoManager.delete(order);

        //then
        Order shouldBeNull = daoManager.getEntityManager().find(Order.class, 1L);
        Assert.assertNull(shouldBeNull);
    }
}
