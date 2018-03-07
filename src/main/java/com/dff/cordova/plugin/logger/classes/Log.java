package com.dff.cordova.plugin.logger.classes;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

import java.util.Date;
import java.util.UUID;

public class Log extends RealmObject {

    @PrimaryKey
    private String _id;

    private String level;
    private String message;
    private int lineNumber;
    private String sourceID;
    private Date timestamp;

    public Log() {
        this._id = UUID.randomUUID().toString();
        this.timestamp = new Date();
    }

    public static Log newInstance() {
        return new Log();
    }

    public Log build(String level, String message, int lineNumber, String sourceID) {
        this.level = level;
        this.message = message;
        this.lineNumber = lineNumber;
        this.sourceID = sourceID;
        return this;
    }
}
