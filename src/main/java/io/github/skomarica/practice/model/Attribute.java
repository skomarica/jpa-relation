package io.github.skomarica.practice.model;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

/**
 * @author Sinisa Komarica
 */
@Entity
@EqualsAndHashCode(of = "name")
@ToString
@Table(name = "ac_attribute")
public class Attribute {

    @Id
    @GeneratedValue
    @Column
    private Long id;

    @Column
    private String name;

    public Attribute() {

    }

    public Attribute(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
