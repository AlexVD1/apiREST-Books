package com.example.apiRESTBooks.response;

import java.util.ArrayList;
import java.util.HashMap;

public class ResponseREST {

    private ArrayList <HashMap<String,String>> metadata =new ArrayList<>();

    public ArrayList <HashMap <String ,String>> getMetadata(){
        return metadata;
    }

    public void setMetadata(String type, String code, String data){
        HashMap<String,String > map = new HashMap<String,String>();

        map.put("type",type);
        map.put("code",code);
        map.put("data",data);

        metadata.add(map);
    }

}
