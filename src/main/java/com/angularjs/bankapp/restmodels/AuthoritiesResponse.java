package com.angularjs.bankapp.restmodels;

import com.angularjs.bankapp.model.Authorities;
import com.fasterxml.jackson.annotation.JsonInclude;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ravindra.palli
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthoritiesResponse {

    private Long id;
    private String authority;

    public AuthoritiesResponse() {
    }

    public AuthoritiesResponse(Long id, String authority) {
        this.id = id;
        this.authority = authority;
    }

    public AuthoritiesResponse(Authorities authorities) {
        this.id = authorities.getId();
        this.authority = authorities.getAuthority();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
