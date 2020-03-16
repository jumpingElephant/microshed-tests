package org.acme;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Person extends PanacheEntity {
  
    public String firstName;
    public String lastName;
    public LocalDate birth;

    public static List<Person> findByName(String name){
        return find("firstName", name).list();
    }

}