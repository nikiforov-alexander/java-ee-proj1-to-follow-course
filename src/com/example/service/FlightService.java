package com.example.service;

import javax.ejb.Local;

@Local
public interface FlightService {

    Integer getId();

    void setId(Integer id);

    String getFrom();

    void setFrom(String from);

    String getTo();

    void setTo(String to);

    Integer getPrice();

    void setPrice(Integer price);

    String toString();
}
