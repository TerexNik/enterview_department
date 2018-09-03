package ru.lanit.department.entities;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "LT_OFFICE")
public class Office {
    @Id @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String address;

    @Column(length = 1)
    private String category;

    @Column(length = 1)
    private Double value;

    @OneToMany(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Set<Department> departments;

    @Column(name = "PROPERTY_TYPE_ID")
    private Long propertyType;

}
