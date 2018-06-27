package com.ch3nk.ch3nkSite.modules.utils;

import org.junit.Test;

import java.util.UUID;

public class UUIDutil {
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-","");
    }
}
