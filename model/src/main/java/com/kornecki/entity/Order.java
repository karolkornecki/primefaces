package com.kornecki.entity;

import com.kornecki.enums.OrderType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Encja zamowienia.
 *
 * @author karol.kornecki
 */
@Entity
@Table(name = "DB_ORDER")
@NamedQuery(name = "Order.findAll", query = "SELECT o FROM Order o")
public class Order implements Serializable, Comparable {

    /**
     * Identyfikator (klucz glowny).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    /**
     * Data utworzenia.
     */
    @Column(name = "CREATE_DATE")
    private Date createDate;

    /**
     * Typ zamowienia.
     */
    @Column(name = "TYPE")
    @Enumerated(value = EnumType.STRING)
    private OrderType type;

    /**
     * Nazwa zamawianej uslugi
     */
    @Column(name = "SERVICE_NAME")
    private String serviceName;

    @Transient
    private transient Date dateFrom;

    @Transient
    private transient Date dateTo;
    /**
     * Pozycje zamowienia
     */
    @Transient
    private List<OrderLine> orderLines = new ArrayList<OrderLine>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public OrderType getType() {
        return type;
    }

    public void setType(OrderType type) {
        this.type = type;
    }

    public List<OrderLine> getOrderLines() {
        return Collections.unmodifiableList(orderLines);
    }

    public void addOrderLine(OrderLine orderLine) {
        orderLines.add(orderLine);
    }

    public void setOrderLines(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public String getTextDate() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(createDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (id != null ? !id.equals(order.id) : order.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public int compareTo(Object o) {
        if (getClass() != o.getClass()) {
            return 0;
        }
        Order order = (Order) o;
        if (getId() == null || order.getId() == null) {
            return 0;
        }
        return getId().compareTo(order.getId());
    }
}
