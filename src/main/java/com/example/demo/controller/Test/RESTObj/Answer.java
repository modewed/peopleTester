package com.example.demo.controller.Test.RESTObj;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micrometer.common.lang.NonNull;

public class Answer {

    @JsonProperty("id")
    private String id;

    @JsonProperty("value")
    private String value;
     public Answer(String id, String value) {
         this.id = id;
         this.value = value;
     }

     public Answer() {

     }
    public  String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


}
