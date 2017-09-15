package com.smart_app.smartapp.util;

import com.smart_app.smartapp.register.model.Registration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2017-09-02.
 */

public class Constants {

    public static String NAME_REGEX = "[a-zA-Z\\-'\\s]+";//[\w\-'\s]+
    public static String NUMBER_REGEX = "[0-9]+";
    public static String EMAIL_REGEX = "\"^[_A-Za-z0-9-]+(\\\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\\\.(?:[A-Z]{2,}|com|co|org)*(\\\\.(?:[A-Z]{2,}|us|in|ch|jp|uk)))+$\"; ";

    public static List<Registration> userList = new ArrayList<>();
}
