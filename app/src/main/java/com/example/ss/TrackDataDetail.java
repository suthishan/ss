package com.example.ss;

public class TrackDataDetail {

    String name;
    String blood;
    String phone;

    public String getLattitude() {
        return lattitude;
    }

    public void setLattitude(String lattitude) {
        this.lattitude = lattitude;
    }

    public String getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(String longtitude) {
        this.longtitude = longtitude;
    }

    String lattitude;
    String longtitude;
    String height;
    String weight;
    String lastdonate;
    String address;



    public TrackDataDetail(String name, String blood, String phone, String lattitude, String longtitude, String height, String weight, String lastdonate, String address) {
        this.name = name;
        this.blood = blood;
        this.phone = phone;
        this.longtitude = longtitude;
        this.lattitude = lattitude;
        this.height = height;
        this.weight = weight;
        this.lastdonate = lastdonate;
        this.address = address;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getLastdonate() {
        return lastdonate;
    }

    public void setLastdonate(String lastdonate) {
        this.lastdonate = lastdonate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}
