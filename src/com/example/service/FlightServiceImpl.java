package com.example.service;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@Stateless
@LocalBean
public class FlightServiceImpl {

    // properties

    private Integer id = 234857;
    private String from = "LA";
    private String to = "London";
    private Integer price = 400;

    // getters and setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    // constructors

    public FlightServiceImpl() {

    }

    // overrides

    @Override
    public String toString() {
        return "FlightServiceImpl{" +
                "id=" + id +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", price=" + price +
                '}';
    }
}
