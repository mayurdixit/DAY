/********************************************************************************
 * Copyright (c) 2015-2016 GE Digital. All rights reserved.                     *
 *                                                                              *
 * The copyright to the computer software herein is the property of GE Digital. *
 * The software may be used and/or copied only with the written permission of   *
 * GE Digital or in accordance with the terms and conditions stipulated in the  *
 * agreement/contract under which the software has been supplied.               *
 ********************************************************************************/

package org.DAY.db.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnTransformer;

/**
 * Created by 204048703 on 11/28/2017.
 */

@Entity
@Table(name="user", schema="dev")
public class User {


    private String first_name;
    private String last_name;
    private String contact_number;
    private boolean enabled;
    private Date created_on;
    private Date last_modified_on;
    private String email;
    private String user_name;
    @ColumnTransformer(read = "pgp_sym_decrypt(password::bytea, 'mySecretKey')", write = "pgp_sym_encrypt(?, "
        + "'mySecretKey')" )
    private String password;
    private String default_password;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Date getCreated_on() {
        return created_on;
    }

    public void setCreated_on(Date created_on) {
        this.created_on = created_on;
    }

    public Date getLast_modified_on() {
        return last_modified_on;
    }

    public void setLast_modified_on(Date last_modified_on) {
        this.last_modified_on = last_modified_on;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        System.out.println("getPassword is called");
        return password;
    }

    public void setPassword(String password) {
        System.out.println("setPassword is called");
        this.password = password;
    }

    public String getDefault_password() {
        return default_password;
    }

    public void setDefault_password(String default_password) {
        this.default_password = default_password;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
            "first_name='" + first_name + '\'' +
            ", last_name='" + last_name + '\'' +
            ", contact_number='" + contact_number + '\'' +
            ", enabled=" + enabled +
            ", created_on=" + created_on +
            ", last_modified_on=" + last_modified_on +
            ", email='" + email + '\'' +
            ", user_name='" + user_name + '\'' +
            ", password='" + password + '\'' +
            ", default_password='" + default_password + '\'' +
            ", id=" + id +
            '}';
    }
}
