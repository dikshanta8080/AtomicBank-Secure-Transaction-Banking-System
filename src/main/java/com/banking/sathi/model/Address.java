package com.banking.sathi.model;

import java.time.LocalDateTime;

public class Address {
    private Long id;
    private Long userId;

    private String province;
    private String district;
    private String city;
    private Integer ward;
    private String tole;
    private LocalDateTime created;

    public Address(Long id, Long userId, String province, String district, String city, Integer ward, String tole, LocalDateTime created) {
        this.id = id;
        this.userId = userId;
        this.province = province;
        this.district = district;
        this.city = city;
        this.ward = ward;
        this.tole = tole;
        this.created = created;
    }

    public Address(String province, String district, String city, Integer ward, String tole) {
        this.province = province;
        this.district = district;
        this.city = city;
        this.ward = ward;
        this.tole = tole;
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

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getWard() {
        return ward;
    }

    public void setWard(Integer ward) {
        this.ward = ward;
    }

    public String getTole() {
        return tole;
    }

    public void setTole(String tole) {
        this.tole = tole;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }
}
