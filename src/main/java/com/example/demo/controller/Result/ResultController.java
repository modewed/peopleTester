package com.example.demo.controller.Result;

import com.example.demo.model.Result;
import com.example.demo.model.Test;
import com.example.demo.repository.QuestionRepository;
import com.example.demo.repository.ResultRepository;
import com.example.demo.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
public class ResultController {


    @Autowired
    TestRepository testRepository;
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    ResultRepository resultRepository;


    @GetMapping("/result")
    public String getResult(@RequestParam(required = true) Long id, Model model) {
        try {

            Result result = resultRepository.getReferenceById(id);

            String description = result.getDescription();
            String title = result.getTitle();

            Test test = result.getTest();

            long testId = test.getId();
            String testTitle = test.getTitle();

            model.addAttribute("id", id);
            model.addAttribute("description", description);
            model.addAttribute("title", title);
            model.addAttribute("testId", testId);
            model.addAttribute("testTitle", testTitle);
            return "result";

        } catch (Exception e) {
            return "error";
        }
    }

    @GetMapping("/result/create")
    public String createResult(@RequestParam(required = false) Long testId, Model model) {
        try {
            if (testId != null) {
                model.addAttribute("testId", testId);
            }
            return "createResult";

        } catch (Exception e) {
            return "error";
        }
    }

    @RequestMapping(value = "/result/create/new", method = RequestMethod.POST)
    public String addResult(@ModelAttribute("SpringWeb") Result result, ModelMap model, @RequestParam(required = true) Long testId) {

        result.setTest(testRepository.getReferenceById(testId));
        resultRepository.save(result);
        model.addAttribute("value", "/test?id=" + testId);
        return "open";
    }


    @RequestMapping(value = "/result/update", method = RequestMethod.POST)
    public String updateResult(@ModelAttribute("SpringWeb") Result result, ModelMap model, @RequestParam(required = true) Long testId, @RequestParam(required = true) Long id) {

        result.setTest(testRepository.getReferenceById(testId));
        Result resultUpdate = resultRepository.getReferenceById(id);
        resultUpdate.setTitle(result.getTitle());
        resultUpdate.setDescription(result.getDescription());
        resultRepository.save(resultUpdate);
        model.addAttribute("value", "/test?id=" + testId);
        return "open";
    }

    @RequestMapping(value = "/result/delete")
    public String deleteResult(ModelMap model, @RequestParam(required = true) Long id) {
        Result result = resultRepository.getReferenceById(id);
        resultRepository.delete(result);
        model.addAttribute("value", "/test?id=" + result.getTest().getId());
        return "open";
    }
}
