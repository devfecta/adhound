package com.adhound.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Object for state data.
 *
 * @author kkelm
 */
@Entity(name = "State")
@Table(name = "states")

public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @Column(name = "states_Abbreviation")
    private String abbreviation;

    @Column(name = "states_Name")
    private String name;

    /**
     * Instantiates a new State.
     */
    public State() {}

    /**
     * Gets state id.
     *
     * @return the state id integer
     */
    public int getId() {
        return id;
    }

    /**
     * Sets state id.
     *
     * @param id integer
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets abbreviation.
     *
     * @return the state abbreviation string
     */
    public String getAbbreviation() {
        return abbreviation;
    }

    /**
     * Sets state abbreviation.
     *
     * @param abbreviation string
     */
    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    /**
     * Gets state name.
     *
     * @return the state name string
     */
    public String getName() {
        return name;
    }

    /**
     * Sets state name.
     *
     * @param name string
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "State{" +
                "id=" + id +
                ", abbreviation='" + abbreviation + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

}
