/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

/**
 *
 * @author Marks
 */
@Entity
@NamedQueries({
@NamedQuery(name = "Address.deleteAllRows", query = "DELETE from Address"),
@NamedQuery(name = "Address.getAllRows", query = "SELECT a from Address a")})

public class Address implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String street;
    private String zip;
    private String city;
    @OneToOne(mappedBy = "address")
    private List<Person> persons;

    public Address() {
    }
    
    

    public Address(String street, String zip, String city) {
        this.street = street;
        this.zip = zip;
        this.city = city;
        persons = new ArrayList<>();
    }
    
    
    
    
    public void addPerson(Person person){
        if(person != null){
            persons.add(person);
        }
    }
    

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
    
}
