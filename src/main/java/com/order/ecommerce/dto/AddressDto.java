package com.order.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressDto {

    @NonNull String address1;
    @NonNull String address2;
    @NonNull String city;
    @NonNull String state;
    @NonNull String zip;
    @NonNull String email;
    @NonNull String phone;

}
