package com.rpdevelopment.user_service_api.projection;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "name", "email", "document" })
public interface UserDocumentProjection {

    //USER
    String getName();
    String getEmail();

    //PERSON
    String getDocument();

}
