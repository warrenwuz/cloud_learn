package com.warren.service;


import com.warren.entity.KhtUserSessionData;

public class SessionDataHolder {

    private static ThreadLocal<KhtUserSessionData> sessionDataLocal = new ThreadLocal<>();

    private SessionDataHolder() {
    }

    public static KhtUserSessionData getSessionData() {
        return sessionDataLocal.get();
    }

    public static void setSessionData(KhtUserSessionData sessionData) {
        if (sessionData == null) {
            sessionDataLocal.remove();
        } else {
            sessionDataLocal.set(sessionData);
        }
    }

}
