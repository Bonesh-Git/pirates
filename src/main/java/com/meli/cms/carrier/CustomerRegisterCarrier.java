package com.meli.cms.carrier;

public class CustomerRegisterCarrier {

    private final String firstName;
    private final String lastName;
    private final String nationalId;

    public CustomerRegisterCarrier(String firstName, String lastName, String nationalId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationalId = nationalId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNationalId() {
        return nationalId;
    }
}