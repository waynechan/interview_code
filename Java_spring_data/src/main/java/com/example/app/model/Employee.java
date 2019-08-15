package com.example.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "employees")
public class Employee extends BaseEntity {

    @Column(name = "first_name")
    @NotEmpty
    protected String firstName;

    @Column(name = "last_name")
    @NotEmpty
    protected String lastName;

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
