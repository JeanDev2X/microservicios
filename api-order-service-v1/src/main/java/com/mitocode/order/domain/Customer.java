package com.mitocode.order.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class Customer {

    private Long id;
    private String name;

}
