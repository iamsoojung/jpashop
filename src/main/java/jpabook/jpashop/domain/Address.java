package jpabook.jpashop.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable // 어딘가에 내장되어있을수도
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;
}
