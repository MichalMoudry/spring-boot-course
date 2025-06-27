package com.example.demo.transport.model

import groovy.transform.CompileStatic

@CompileStatic
final class Student {
    private String firstName
    private String lastName
    private Country country
    private Language favouriteLanguage
    private List<String> favouriteOSes

    Student() {}

    String getFirstName() { firstName }

    void setFirstName(String firstName) {
        this.firstName = firstName
    }

    String getLastName() { lastName }

    void setLastName(String lastName) {
        this.lastName = lastName
    }

    Country getCountry() { country }

    void setCountry(Country country) {
        this.country = country
    }

    Language getFavouriteLanguage() { favouriteLanguage }

    void setFavouriteLanguage(Language favouriteLanguage) {
        this.favouriteLanguage = favouriteLanguage
    }

    List<String> getFavouriteOSes() { favouriteOSes }

    void setFavouriteOSes(List<String> favouriteOSes) {
        this.favouriteOSes = favouriteOSes
    }
}
