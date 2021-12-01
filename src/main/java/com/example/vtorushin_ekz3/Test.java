package com.example.vtorushin_ekz3;

import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Scope("singleton")
public class Test {
    private Map<String, String> map = new HashMap<>();
    private JSONObject jsonObject;

    public Test(){
        map.put("exam", "NT");
        jsonObject = new JSONObject(map);
    }

    @Override
    public String toString(){
        return this.jsonObject.toString();
    }
}
