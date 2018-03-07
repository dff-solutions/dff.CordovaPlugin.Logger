package com.dff.cordova.plugin.logger.actions;

import com.dff.cordova.plugin.dagger2.abstracts.Action;
import io.realm.Realm;

import javax.inject.Inject;

public class GetLogsAction extends Action {

    private Realm mRealm;

    @Inject
    public GetLogsAction(Realm mRealm) {
        this.mRealm = mRealm;
    }

    @Override
    public void execute() {

    }
}
