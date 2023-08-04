package com.howthere.app.embed;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
public class Address {

    private String address;
    private String addressDetail;
    private Double latitude;
    private Double longitude;

    @Builder
    public Address(String address, String addressDetail, Double latitude, Double longitude) {
        this.address = address;
        this.addressDetail = addressDetail;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
