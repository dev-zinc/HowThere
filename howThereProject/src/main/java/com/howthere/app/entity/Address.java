package com.howthere.app.entity;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

    private String address;
    private Double rentCarLatitude;
    private Double rentCarLongitude;
}
