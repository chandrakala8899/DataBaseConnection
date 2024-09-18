package com.neoteric.fullstack_31082024.hibernate.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "address",schema = "bank ")
@Data
public class AccountEntityAddress {
    public  AccountEntityAddress (){

    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "add1")
    private  String add1;

    @Column(name = "add2")
    private   String add2;

    @Column(name = "city")
    private  String city;

    @Column(name = "state")
    private  String state;

    @Column(name = "pincode")
    private  String pincode;

    @Column(name = "statuscode")
    private  Integer statuscode;

    @ManyToOne
    @JoinColumn(name ="accountnumber",referencedColumnName ="accountnumber")
    public  AccountEntity accountEntity;

}
