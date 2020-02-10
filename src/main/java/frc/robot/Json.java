package frc.robot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;  
public class Json{
/**
 * jh
 */
private Map< String,Object> Contents = new HashMap< String,Object>(); 
public static void main(String[] args) {
    Json json = new Json();
    json.initialize();
}

    public void initialize() {
        GsonBuilder builder = new GsonBuilder(); 
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        Color blue = new Color(30, 100, 120, "blue");
        Color green = new Color(40, 150, 65, "green");
        Color red = new Color(140, 80, 30, "red");
        Contents.put("blue", blue);
        Contents.put("green", green);
        Contents.get("blue");

        System.out.println(gson.toJson(Contents));
        


    }
    public void put(String key, Object obj) {
        this.Contents.put(key, obj);
    }
    public void out() {
        
    }
}