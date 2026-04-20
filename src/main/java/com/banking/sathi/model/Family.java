package com.banking.sathi.model;

import java.time.LocalDateTime;

public class Family {
    private Long id;
    private Long userId;

    private String father;
    private String mother;

    private LocalDateTime created;

    public Family(String father, String mother) {
        this.father = father;
        this.mother = mother;
    }

    public Family(Long userId, String father, String mother) {
        this.userId = userId;
        this.father = father;
        this.mother = mother;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFather() {
        return father;
    }

    public void setFather(String father) {
        this.father = father;
    }

    public String getMother() {
        return mother;
    }

    public void setMother(String mother) {
        this.mother = mother;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }
}
