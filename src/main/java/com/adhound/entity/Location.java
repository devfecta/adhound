package com.adhound.entity;

import com.adhound.persistence.LocationData;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Object for a location.
 *
 * @author kkelm
 */
@Entity(name = "Location")
@Table(name = "locations")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @ManyToOne
    private User user;

    @NotNull
    @NotEmpty(message = "Enter a Location Name")
    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

    @Column(name = "fax")
    private String fax;

    @Column(name = "address")
    private String address;
    @NotNull
    @NotEmpty(message = "Enter a City")
    @Column(name = "city")
    private String city;

    @Column(name = "state_id")
    private int stateId;

    @OneToOne
    @JoinColumn(name = "state_id", insertable = false, updatable = false, nullable = false)
    private State state;

    @NotNull
    @NotEmpty(message = "Enter a Zipcode")
    @Column(name = "zipcode")
    private String zipcode;

    @Column(name = "region_id")
    private int regionId;

    @OneToOne
    @JoinColumn(name = "region_id", insertable = false, updatable = false, nullable = false)
    private Region region;

    /**
     * Instantiates a new Location.
     */
    public Location() {}

    /**
     * Instantiates a new Location.
     *
     * @param user     the user
     * @param name     the name
     * @param phone    the phone
     * @param fax      the fax
     * @param address  the address
     * @param city     the city
     * @param stateId  the state id
     * @param zipcode  the zipcode
     * @param regionId the region id
     */
    public Location(User user, String name, String phone, String fax, String address, String city, int stateId, String zipcode, int regionId) {
        this.user = user;
        this.name = name;
        this.phone = phone;
        this.fax = fax;
        this.address = address;
        this.city = city;
        this.stateId = stateId;
        this.zipcode = zipcode;
        this.regionId = regionId;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
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
     * Gets user.
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets user.
     *
     * @param user the user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets phone.
     *
     * @return the phone
     */
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
     * Gets address.
     *
     * @return the address
     */
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
     * Gets region id.
     *
     * @return the region id
     */
    public int getRegionId() {
        return regionId;
    }

    /**
     * Sets region id.
     *
     * @param regionId the region id
     */
    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    /**
     * Gets region.
     *
     * @return the region
     */
    public Region getRegion() {
        return region;
    }

    /**
     * Sets region.
     *
     * @param region the region
     */
    public void setRegion(Region region) {
        this.region = region;
    }

    /**
     * Gets locations for a specific user.
     *
     * @param user the User object
     * @return the user locations
     */
    public Set<Location> getLocations(User user) {
        LocationData locationData = new LocationData();
        List<Location> allLocations = locationData.crud.getAll();

        Set<Location> locations = new HashSet<>();

        for (Location location : allLocations) {
            if (location.getUser().equals(user)) {
                Location userLocation = (Location) locationData.crud.getById(location.getId());
                locations.add(userLocation);
            }
        }

        return locations;
    }

    // mappedBy refers to the location_contact table
    //// CascadeType.ALL removes locations associated with the user, orphanRemoval does the same in hibernate
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "location_contact", joinColumns = {
            @JoinColumn(name = "location_id")
    }, inverseJoinColumns = {
            @JoinColumn(name = "contact_id")
    })
    private Set<LocationContact> locationContacts;

    /**
     * Gets location contacts.
     *
     * @return the location contacts
     */
    public Set<LocationContact> getLocationContacts() { return locationContacts; }

    /**
     * Sets location contacts.
     *
     * @param locationContacts the location contacts
     */
    public void setLocationContacts(Set<LocationContact> locationContacts) { this.locationContacts = locationContacts; }

    //@JsonIgnore


    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", fax='" + fax + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", stateId=" + stateId +
                ", state=" + state +
                ", zipcode='" + zipcode + '\'' +
                ", regionId=" + regionId +
                ", region=" + region +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return id == location.id &&
                Objects.equals(name, location.name) &&
                Objects.equals(phone, location.phone) &&
                Objects.equals(fax, location.fax) &&
                Objects.equals(address, location.address) &&
                Objects.equals(city, location.city) &&
                Objects.equals(stateId, location.stateId) &&
                Objects.equals(zipcode, location.zipcode) &&
                Objects.equals(regionId, location.regionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, phone, fax, address, city, stateId, zipcode, regionId);
    }
}
