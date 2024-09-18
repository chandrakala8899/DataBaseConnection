package com.neoteric.fullstack_31082024.hibernate.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "  ",schema = " bank ")
@Data
public class AadharEntity {
    public  AadharEntity(){

    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "name")
    public String name;
    @Column(name = " adharnumber")
    public  Integer adharnumber;


 @OneToMany(mappedBy = "mappedaadharEntity",cascade = CascadeType.ALL)

    public List<AddressEntity> addressEntityList;

}
