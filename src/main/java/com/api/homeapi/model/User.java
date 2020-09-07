package com.api.homeapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.ColumnTransformer;

@Entity
@Table(name="user_account")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ColumnTransformer(forColumn = "email_address", read="pgp_sym_decrypt(email_address, 'pw')", write="pgp_sym_encrypt(?, 'pw')")
    @Column(name = "email_address", columnDefinition = "bytea")
    private String emailAddress;

    @ColumnTransformer(forColumn = "password", read="pgp_sym_decrypt(password, 'pw')", write="pgp_sym_encrypt(?, 'pw')")
    @Column(name = "password", columnDefinition = "bytea")
    private String password;

    public long getId() {
        return id;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setEmailAdress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
