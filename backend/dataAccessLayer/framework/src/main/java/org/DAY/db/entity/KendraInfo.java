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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by 204048703 on 12/4/2017.
 */

@Entity
@Table(name="kendra_info", schema="dev")
public class KendraInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private Integer parent;
    private boolean active;
    private Date created_on;
    private Date last_updated_on;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getCreated_on() {
        return created_on;
    }

    public void setCreated_on(Date created_on) {
        this.created_on = created_on;
    }

    public Date getLast_updated_on() {
        return last_updated_on;
    }

    public void setLast_updated_on(Date last_updated_on) {
        this.last_updated_on = last_updated_on;
    }

    @Override
    public String toString() {
        return "KendraInfo{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", parent=" + parent +
            ", active=" + active +
            ", created_on=" + created_on +
            ", last_updated_on=" + last_updated_on +
            '}';
    }
}
