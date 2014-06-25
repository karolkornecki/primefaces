package com.kornecki.controller;

import com.kornecki.context.UserContext;
import com.kornecki.entity.Order;
import org.apache.commons.logging.Log;
import org.primefaces.event.RowEditEvent;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Kontroler ekranu wyszukiwania.
 *
 * @author karol.kornecki
 */
@ManagedBean(name = "searchController")
@RequestScoped
public class SearchController extends AbstractController {

    @Inject
    private Log logger;

    /**
     * Kryteira dla wyszukiwania.
     */
    public Order getNewOrder() {
        UserContext ctx = getUserContext();
        return ctx.getNewOrder();
    }

    /**
     * Kryteira dla wyszukiwania.
     */
    public Order getCriteria() {
        UserContext ctx = getUserContext();
        return ctx.getCriteria();
    }

    /**
     * Lista wszystkich zamowien.
     */
    public List<Order> getOrders() {
        UserContext ctx = getUserContext();
        if (!ctx.isDefaultProceed()) {
            readAllOrders(ctx);
            ctx.setDefaultProceed(true);
        }
        return ctx.getOrders();
    }

    public void chooseAdd() {
        UserContext ctx = getUserContext();
        ctx.setNewOrder(new Order());
        if (logger.isDebugEnabled()) {
            logger.debug("ADD action.");
        }

    }

    /**
     * Wyszukuje wg. kryteria podanych przez uzytkownika.
     */
    public void findByCriteria() {
        UserContext ctx = getUserContext();
        orderService.findByCriteria(ctx);
        addMessage("Wyszukiwanie zako\u0144czone.");
    }

    /**
     * Zapisuje nowe Zamówienie
     */
    public void addAction() {
        UserContext ctx = getUserContext();
        Order newOrder = ctx.getNewOrder();
        if (!checkFields(newOrder)) {
            return;
        }
        orderService.saveOrder(ctx.getNewOrder());
        readAllOrders(ctx);
        addMessage("Zam\u00f3wienie dodano.");
    }

    /**
     * Usuwa Zamówienie.
     */
    public void deleteAction(Long id) {
        Order order = findOrderById(id);
        delete(order);
    }

    protected Order findOrderById(Long id) {
        UserContext ctx = getUserContext();
        Iterator<Order> orderIterator = ctx.getOrders().iterator();
        while (orderIterator.hasNext()) {
            Order order = orderIterator.next();
            if (id.equals(order.getId())) {
                return order;
            }
        }
        return null;
    }

    private void delete(Order order) {
        if (order == null) {
            return;
        }
        orderService.deleteOrder(order);
        UserContext ctx = getUserContext();
        readAllOrders(ctx);
        addMessage("Zam\u00f3wienie usuni\u0119to.");
    }

    private void readAllOrders(UserContext ctx) {
        orderService.readAllOrders(ctx);
    }

    public void onEdit(RowEditEvent event) {
        Order modifiedOrder = (Order) event.getObject();
        update(modifiedOrder);

    }

    private void update(Order modifiedOrder) {
        if (!checkFields(modifiedOrder)) {
            return;
        }
        orderService.updateOrder(modifiedOrder);
        readAllOrders(getUserContext());
        addMessage("Zam\u00f3wienie o id: "+modifiedOrder.getId()+" zosta\u0142o zaktualizowane.");
    }

    public void onCancel(RowEditEvent event) {
        addMessage("Anulowano.");
    }

    private boolean checkFields(Order order) {
        Date date = order.getCreateDate();
        String serviceName = order.getServiceName();
        boolean result = true;
        if (date == null) {
            addMessage("Zam\u00f3wienie nie zosta\u0142o dodane.\nWype\u0142nij pole \"data utworzenia\"");
            result = false;
        }
        if (serviceName == null || serviceName.isEmpty()) {
            addMessage("Zam\u00f3wienie nie zosta\u0142o dodane.\nWype\u0142nij pole \"nazwa us\u0142ugi\"");
            result = false;
        }
        return result;
    }
}
