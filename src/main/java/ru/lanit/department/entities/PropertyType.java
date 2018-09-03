package ru.lanit.department.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "D_PROPERTY_TYPE")
public class PropertyType {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String value;
}
