package com.example.listener;

import com.example.model.Passenger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.ArrayList;
import java.util.List;

@WebListener
public class PassengerListener implements ServletContextListener{

    public PassengerListener() {

    }

    /**
     * creates "passengers" attribute as new {@code List<Passenger>}
     * @param servletContextEvent that is used to extract
     *                            {@code ServletContext}
     */
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext =
                servletContextEvent.getServletContext();
        if (servletContext.getAttribute("passengers") == null){
            List<Passenger> passengers = new ArrayList<>();
            servletContext.setAttribute("passengers", passengers);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
