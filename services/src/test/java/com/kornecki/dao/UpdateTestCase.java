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
 * @author karol.kornecki
 */
@RunWith(Arquillian.class)
public class UpdateTestCase {

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(DaoManager.class)
                .addAsResource("META-INF/test-persistence.xml");
    }

    @Inject
    private DaoManager daoManager;

    @Test
    public void testUpdateOrder() {
        //given
        daoManager.init();
        Order order = daoManager.getEntityManager().find(Order.class, 2L);
        Assert.assertNotNull(order);
        Assert.assertEquals("Samsung LED", order.getServiceName());
        Assert.assertEquals(OrderType.SIMPLE, order.getType());

        //when
        order.setServiceName("Nowa nazwa");
        daoManager.update(order);

        //then
        Order modified = daoManager.getEntityManager().find(Order.class, 2L);
        Assert.assertNotNull(modified);
        Assert.assertEquals("Nowa nazwa", modified.getServiceName());
        Assert.assertEquals(OrderType.SIMPLE, modified.getType());
    }
}
