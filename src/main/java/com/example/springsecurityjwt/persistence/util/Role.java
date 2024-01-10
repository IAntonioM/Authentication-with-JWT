package com.example.springsecurityjwt.persistence.util;

import java.util.Arrays;
import java.util.List;

public enum Role {
    CUSTOMER(Arrays.asList(Permission.READ_ALL_Course)),
    ADMINISTRATOR(Arrays.asList(Permission.READ_ALL_Course, Permission.SAVE_ONE_Course));
    private List<Permission> permissions;

    Role(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}
