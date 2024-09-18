package com.neoteric.fullstack_31082024.hibernate.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name=" ",schema = " ")
@Data
public class AddressEntity {
    public  AddressEntity(){

    }
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id ")
    public Integer id;
   @Column(name = "state ")
    public  String state;


   @ManyToOne
   @JoinColumn(name = " adharnumber",referencedColumnName = "adharnumber")
   public  AadharEntity mappedaadharEntity;

}
