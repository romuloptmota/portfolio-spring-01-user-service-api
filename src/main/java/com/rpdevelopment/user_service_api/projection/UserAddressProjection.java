package com.rpdevelopment.user_service_api.projection;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "name", "email",
        "road", "number", "neighborhood", "complement", "city", "zip_code"})
public interface UserAddressProjection {

    //USER
    String getName();
    String getEmail();

    //ADDRESS
    String getRoad();
    String getNumber();
    String getNeighborhood();
    String getComplement();
    String getCity();
    String getZipCode();
}
