package com.example.demo.controller.Test;

import com.example.demo.controller.Test.RESTObj.Answer;
import com.example.demo.repository.TestRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "api", produces = "application/json", method = RequestMethod.POST)
public class TestControllerRest {

    @Autowired
    TestRepository testRepository;

    @GetMapping("/runtest/result/{testid}")
    public String getEmployeeByID(@PathVariable("testid") String id, @RequestBody String AnswersJson) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Answer> answers = objectMapper.readValue(AnswersJson, new TypeReference<>() {
        });
        //Calculate
        return "[{\"title\": \"Red\",  \"value\": \"25\"},{\"title\": \"Blue\", \"value\": \"60\"},{\"title\": \"Pink\", \"value\": \"50\"}]";
    }


}