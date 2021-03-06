package com.example.CompuCom2.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "StatusShipping")
public class StatusShipping {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "status")
    private String status;

    @Column(name = "last_update")
    private LocalDateTime last_update;

    public StatusShipping(){}

    public StatusShipping(String status, LocalDateTime last_update) {
        this.status = status;
        this.last_update = last_update;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getLast_update() {
        return last_update;
    }

    public void setLast_update(LocalDateTime last_update) {
        this.last_update = last_update;
    }

    @Override
    public String toString() {
        return "StatusShipping{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", last_update=" + last_update +
                '}';
    }
}
