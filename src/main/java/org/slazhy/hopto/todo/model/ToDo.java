package org.slazhy.hopto.todo.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@Entity
@Table(name = "todos")
@XmlRootElement
public final class ToDo {

    @Id @GeneratedValue
    private final Long id;
    @Column(nullable = false)
    private final String description;
    private final int priority;
    @ManyToOne
    @XmlTransient
    private final User owner;

    @JsonInclude(NON_EMPTY)
    @Transient
    private final List<LinkMessage> links = new ArrayList<>();

    protected ToDo(){
        id = -1L;
        description = null;
        priority = 0;
        owner = null;
    }

    public ToDo(String description, int priority, User owner) {
        this.id = -1L;
        this.description = description;
        this.priority = priority;
        this.owner = owner;
    }
    public ToDo(String description, int priority) {
        this.id = -1L;
        this.description = description;
        this.priority = priority;
        this.owner = null;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }

    public User getOwner() {
        return owner;
    }

    public List<LinkMessage> getLinks() {
        return links;
    }

    public void addLink(LinkMessage linkMessage){
        links.add(linkMessage);
    }
    public String toString(){
        return String.format("TodoID: %s, description: %s, priority: %s", getId(), getDescription(), getPriority());
    }

}
