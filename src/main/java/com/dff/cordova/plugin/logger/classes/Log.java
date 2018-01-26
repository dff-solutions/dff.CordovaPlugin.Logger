package com.dff.cordova.plugin.logger.classes;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

import java.util.UUID;

public class Log extends RealmObject {

    @PrimaryKey
    private String _id;

    private String message;
    private int lineNumber;
    private String sourceID;

    public Log() {
        this._id = UUID.randomUUID().toString();
    }

    public static Log newInstance() {
        return new Log();
    }

    public Log build(String message, int lineNumber, String sourceID) {
        this.message = message;
        this.lineNumber = lineNumber;
        this.sourceID = sourceID;
        return this;
    }
}
