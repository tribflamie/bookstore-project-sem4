package com.backend.bookstore.utils;

import java.util.Map;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.google.common.base.Strings;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.util.Date;

public class BookstoreUtil {
    private BookstoreUtil() {

    }

    public static ResponseEntity<String> getResponseEntity(String responseMessage, HttpStatus httpStatus) {
        return new ResponseEntity<String>("{\"message\":\"" + responseMessage + "\"}", httpStatus);
    }

    public static String getUUID() {
        Date date = new Date();
        long time = date.getTime();
        return "Order-" + time;
    }

    public static JSONArray getJsonArrayFromString(String data) throws JSONException {
        JSONArray jsonArray = new JSONArray(data);
        return jsonArray;
    }

    public static Map<String, Object> getMapFromJson(String data){
        if(!Strings.isNullOrEmpty(data))
            return new Gson().fromJson(data, new TypeToken<Map<String, Object>>() {
            }.getType());
        return new HashMap<>();
    }
}
