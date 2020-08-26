package com.adhound.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

/**
 * Object for a location contact.
 *
 * @author kkelm
 */
@Entity(name = "LocationContacts")
@Table(name = "location_contacts")
public class LocationContact {
    private int id;
    private String firstName;
    private String lastName;
    private String phone;
    private String fax;
    private String email;
    private String address;
    private String city;
    private int stateId;
    private State state;
    private String zipcode;
    private int typeId;

    /**
     * Gets id.
     *
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets first name.
     *
     * @return the first name
     */
    @NotNull
    @NotEmpty(message = "Enter a First Name")
    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets first name.
     *
     * @param firstName the first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    @NotNull
    @NotEmpty(message = "Enter a Last Name")
    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets last name.
     *
     * @param lastName the last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets phone.
     *
     * @return the phone
     */
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    /**
     * Sets phone.
     *
     * @param phone the phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Gets fax.
     *
     * @return the fax
     */
    @Column(name = "fax")
    public String getFax() {
        return fax;
    }

    /**
     * Sets fax.
     *
     * @param fax the fax
     */
    public void setFax(String fax) {
        this.fax = fax;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets address.
     *
     * @return the address
     */
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    /**
     * Sets address.
     *
     * @param address the address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets city.
     *
     * @return the city
     */
    @NotNull
    @NotEmpty(message = "Enter a City Name")
    @Column(name = "city")
    public String getCity() {
        return city;
    }

    /**
     * Sets city.
     *
     * @param city the city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets state id.
     *
     * @return the state id
     */
    @Column(name = "state_id")
    public int getStateId() {
        return stateId;
    }

    /**
     * Sets state id.
     *
     * @param stateId the state id
     */
    public void setStateId(int stateId) {
        this.stateId = stateId;
    }

    /**
     * Gets state.
     *
     * @return the state
     */
    @OneToOne
    @JoinColumn(name = "state_id", insertable = false, updatable = false, nullable = false)
    public State getState() {
        return state;
    }

    /**
     * Sets state.
     *
     * @param state the state
     */
    public void setState(State state) {
        this.state = state;
    }

    /**
     * Gets zipcode.
     *
     * @return the zipcode
     */
    @NotNull
    @NotEmpty(message = "Enter a Zipcode")
    @Column(name = "zipcode")
    public String getZipcode() {
        return zipcode;
    }

    /**
     * Sets zipcode.
     *
     * @param zipcode the zipcode
     */
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    /**
     * Gets type id.
     *
     * @return the type id
     */
    @Column(name = "type_id")
    public int getTypeId() {
        return typeId;
    }

    /**
     * Sets type id.
     *
     * @param typeId the type id
     */
    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    // mappedBy references the locationContacts instance variable in Location
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "locationContacts")
    private List<Location> locations;

    /**
     * Instantiates a new Location contact.
     */
    public LocationContact() {}

    /**
     * Instantiates a new Location contact.
     *
     * @param firstName the first name
     * @param lastName  the last name
     * @param phone     the phone
     * @param fax       the fax
     * @param email     the email
     * @param address   the address
     * @param city      the city
     * @param stateId   the state id
     * @param zipcode   the zipcode
     * @param typeId    the type id
     */
    public LocationContact(String firstName, String lastName, String phone, String fax, String email, String address, String city, int stateId, String zipcode, int typeId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.fax = fax;
        this.email = email;
        this.address = address;
        this.city = city;
        this.stateId = stateId;
        this.zipcode = zipcode;
        this.typeId = typeId;
    }

    @Override
    public String toString() {
        return "LocationContact{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", fax='" + fax + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", stateId=" + stateId +
                ", state=" + state +
                ", zipcode='" + zipcode + '\'' +
                ", typeId=" + typeId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocationContact that = (LocationContact) o;
        return id == that.id &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(fax, that.fax) &&
                Objects.equals(email, that.email) &&
                Objects.equals(address, that.address) &&
                Objects.equals(city, that.city) &&
                Objects.equals(stateId, that.stateId) &&
                Objects.equals(zipcode, that.zipcode) &&
                Objects.equals(typeId, that.typeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, phone, fax, email, address, city, stateId, zipcode, typeId);
    }
}
