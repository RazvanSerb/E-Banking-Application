package org.poo.cb;

import java.util.*;

public class User {
    private String email;
    private String firstname;
    private String lastname;
    private String address;
    protected LinkedHashMap<String, Account> accounts;
    protected TreeMap<String, StocksBought> stocks;
    protected LinkedHashMap<String, User> friends;
    protected boolean premium;
    public static class Builder {
        private String email;
        private String firstname;
        private String lastname;
        private String address;
        protected Builder() {}

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }
        public Builder withFirstname(String firstname) {
            this.firstname = firstname;
            return this;
        }
        public Builder withLastname(String lastname) {
            this.lastname = lastname;
            return this;
        }
        public Builder withAddress(String address) {
            this.address = address;
            return this;
        }
        public User build() {
            return new User(this);
        }
    }
    private User(Builder b) {
        User.this.email = b.email;
        User.this.firstname = b.firstname;
        User.this.lastname = b.lastname;
        User.this.address = b.address;
        User.this.accounts = new LinkedHashMap<>();
        User.this.stocks = new TreeMap<>();
        User.this.friends = new LinkedHashMap<>();
        User.this.premium = false;
    }

    public String getEmail() {
        return email;
    }
    public String getFirstname() {
        return firstname;
    }
    public String getLastname() {
        return lastname;
    }
    public String getAddress() {
        return address;
    }
}
