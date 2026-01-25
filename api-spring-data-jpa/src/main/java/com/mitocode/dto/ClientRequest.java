package com.mitocode.dto;

public record ClientRequest(Long id, String name, String email) {

    public ClientRequest withId(Long newId) {
        return new ClientRequest(newId, this.name, this.email);
    }
}
