package com.kornecki.controller;

import com.kornecki.context.UserContext;
import com.kornecki.services.OrderService;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 * Nadklasa dla kontrolerow.
 *
 * @author karol.kornecki
 */
public class AbstractController {

    @Inject
    protected OrderService orderService;

    @ManagedProperty(value = "#{userContext}")
    private UserContext userContext;

    protected void addMessage(String message) {
        FacesMessage msg = new FacesMessage(message);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void setUserContext(UserContext userContext) {
        this.userContext = userContext;
    }

    public UserContext getUserContext() {
        return userContext;
    }
}
