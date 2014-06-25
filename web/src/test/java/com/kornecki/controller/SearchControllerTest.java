package com.kornecki.controller;

import com.kornecki.context.UserContext;
import com.kornecki.entity.Order;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * *  @author karol.kornecki
 */
public class SearchControllerTest {

    private SearchController searchController;

    @Before
    public void setUp() {
        //given
        searchController = new SearchController();
        prepareOrders();
    }

    @Test
    public void testFindOrderById() {
        //when
        Order found = searchController.findOrderById(1L);
        //then
        Assert.assertNotNull(found);
        Assert.assertEquals(1L, found.getId().longValue());
        Assert.assertEquals("name1", found.getServiceName());
    }

    @Test
    public void testFindOrderByIdFail() {
        //when
        Order notFound = searchController.findOrderById(5L);
        //then
        Assert.assertNull(notFound);
    }

    private void prepareOrders() {
        UserContext ctx = new UserSessionContext();
        Order order1 = new Order();
        order1.setId(1L);
        order1.setServiceName("name1");
        Order order2 = new Order();
        order2.setId(2L);
        order2.setServiceName("name2");
        ctx.getOrders().add(order1);
        ctx.getOrders().add(order2);
        searchController.setUserContext(ctx);
    }

}
