package com.example.service;

import javax.ejb.Stateless;

@Stateless(name = "FlightServiceImpl")
public class FlightServiceImpl implements FlightService{

    // properties

    private Integer id = 234857;
    private String from = "LA";
    private String to = "London";
    private Integer price = 400;

    // getters and setters

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getFrom() {
        return from;
    }

    @Override
    public void setFrom(String from) {
        this.from = from;
    }

    @Override
    public String getTo() {
        return to;
    }

    @Override
    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public Integer getPrice() {
        return price;
    }

    @Override
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
