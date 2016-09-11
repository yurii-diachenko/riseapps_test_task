package com.ups.riseappstesttask.model.entities;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Yuriy Diachenko on 11.09.2016.
 */
public class Contact extends RealmObject {

    @PrimaryKey
    private long id;

    private String firstName;

    private String secondName;

    private String sourname;

    private int image;

    private RealmList<CustomField> customFields;
}
