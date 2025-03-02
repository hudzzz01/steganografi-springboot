package com.hudzaifah.Rest_steganografi.constant;

public enum UserRole {
    ADMIN,
    USER;

    String getRole(){
        return name();
    }
}
