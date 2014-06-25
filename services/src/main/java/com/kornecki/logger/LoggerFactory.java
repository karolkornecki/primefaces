package com.kornecki.logger;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

/**
 * @author karol.kornecki
 */
public class LoggerFactory {

    @Produces
    public Log createLogger(InjectionPoint injectionPoint) {
        String name = injectionPoint.getMember().getDeclaringClass().getName();
        return LogFactory.getLog(name);
    }
}