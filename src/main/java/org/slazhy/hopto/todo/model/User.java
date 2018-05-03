package org.slazhy.hopto.todo.model;

import org.slazhy.hopto.todo.TodoApplication;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.Collection;


@Entity
@Table(name = "users")
@XmlRootElement
public final class User {

    @Id @GeneratedValue
    @XmlTransient
    private final Long id;
    @Column(nullable = false)
    private final String firstname;
    @Column(nullable = false)
    private final String lastname;

    @XmlTransient
    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER, orphanRemoval=true)
    private final Collection<ToDo> toDos;

    protected User(){
        id=-1L;
        firstname = null;
        lastname = null;
        toDos = null;
    }

    public User(String firstname, String lastname) {
        this.id = -1L;
        this.firstname = firstname;
        this.lastname = lastname;
        this.toDos = null;
    }

    public Long getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public Collection<ToDo> getToDos() {
        return toDos;
    }

    public String toString(){
        return String.format("userID: %s, firstname: %s, lastname: %s", getId(), getFirstname(), getLastname());
    }

}
