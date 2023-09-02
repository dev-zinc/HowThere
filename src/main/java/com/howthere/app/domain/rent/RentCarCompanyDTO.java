package com.howthere.app.domain.rent;

import com.howthere.app.embed.Address;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Data
@Component
@ToString
@NoArgsConstructor
public class RentCarCompanyDTO {
    private Long id;
    private String RentCarCompanyName;
    private Address RentCarCompanyAddress;

    @Builder
    public RentCarCompanyDTO(Long id, String rentCarCompanyName, Address rentCarCompanyAddress) {
        this.id = id;
        RentCarCompanyName = rentCarCompanyName;
        RentCarCompanyAddress = rentCarCompanyAddress;
    }
}
