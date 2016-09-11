package com.ups.riseappstesttask.model.entities;

import android.os.Parcelable;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Yuriy Diachenko on 11.09.2016.
 */
public class Contact extends RealmObject{

    @PrimaryKey
    private long id;

    private String firstName;

    private String secondName;

    private String sourname;

    private int image;

    private String phone;

    private String email;

    private RealmList<CustomField> customFields;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getSurname() {
        return sourname;
    }

    public void setSurname(String sourname) {
        this.sourname = sourname;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public RealmList<CustomField> getCustomFields() {
        return customFields;
    }

    public void setCustomFields(RealmList<CustomField> customFields) {
        this.customFields = customFields;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
