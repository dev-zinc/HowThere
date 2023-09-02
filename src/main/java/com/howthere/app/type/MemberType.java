package com.howthere.app.type;

public enum MemberType {
    MEMBER,
    ADMIN;
    private static final String ROLE_PREFIX = "ROLE_";

    public String getSecurityRole(){
        return ROLE_PREFIX + name();
    }
}
