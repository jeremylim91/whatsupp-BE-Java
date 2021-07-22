package com.chatApp.demo.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public class HandleJson {

    public static String deserializeToString(String jsonObj, String key ) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<HashMap<String, String>> typeRef= new TypeReference<HashMap<String, String>>() {
        };
        Map<String, String> map= mapper.readValue(jsonObj, typeRef );
        return map.get(key);
    }


}
