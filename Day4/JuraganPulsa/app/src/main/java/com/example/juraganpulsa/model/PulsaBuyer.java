package com.example.juraganpulsa.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PulsaBuyer {
    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("code")
    @Expose
    private String code;

    @SerializedName("phone_number")
    @Expose
    private String phone_number;

    public PulsaBuyer(String code, String phone_number) {
        this.code = code;
        this.phone_number = phone_number;
    }

    //----------------Getter Setter----------


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
}
