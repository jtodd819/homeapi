package com.api.homeapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.ColumnTransformer;

@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ColumnTransformer(forColumn = "user_name", read="pgp_sym_decrypt(age, 'password)", write="pgp_sym_encrypt(?, 'password')")
    @Column(name = "user_name", columnDefinition = "bytea")
    private String userName;

    @ColumnTransformer(forColumn = "password", read="pgp_sym_decrypt(age, 'password)", write="pgp_sym_encrypt(?, 'password')")
    @Column(name = "password", columnDefinition = "bytea")
    private String password;

    public long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
