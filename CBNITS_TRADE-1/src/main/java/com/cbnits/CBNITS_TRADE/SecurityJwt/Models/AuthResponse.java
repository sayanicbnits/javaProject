package com.cbnits.CBNITS_TRADE.SecurityJwt.Models;

import java.io.Serializable;

public class AuthResponse implements Serializable {

	private String jwt;

    public AuthResponse(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }
}
