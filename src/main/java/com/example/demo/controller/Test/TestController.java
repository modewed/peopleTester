package com.example.demo.controller.Test;

import com.example.demo.model.Question;
import com.example.demo.model.Result;
import com.example.demo.model.Test;
import com.example.demo.repository.ResultRepository;
import com.example.demo.repository.TestRepository;
import com.example.demo.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class TestController {


    @Autowired
    TestRepository testRepository;
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    ResultRepository resultRepository;

    @GetMapping("/")
    public String home(Model model) {
        try {

            model.addAttribute("value", "tests");
            return "open";

        } catch (Exception e) {
            return "error";
        }
    }

    @GetMapping("/tests")
    public String getAllTests(Model model) {
        try {
            List<Test> tests = new ArrayList<Test>();
            List<HashMap> retTests = new ArrayList<HashMap>();
            testRepository.findAll().forEach(tests::add);

            for (int i = 0; i < tests.size(); i++) {
                HashMap<String, String> test = new HashMap<String, String>();
                test.put("id", Long.toString(tests.get(i).getId()));
                test.put("title", tests.get(i).getTitle());
                test.put("description", tests.get(i).getDescription());
                retTests.add(test);
            }
            if (tests.isEmpty()) {
                return "tests";
            }
            model.addAttribute("tests", retTests);
            return "tests";

        } catch (Exception e) {
            return "error";
        }
    }

    @GetMapping("/test")
    public String getTest(@RequestParam(required = true) Long id, Model model) {
        try {
            List<Question> questions = new ArrayList<Question>();
            List<Result> results = new ArrayList<Result>();
            List<HashMap> retQuestions = new ArrayList<HashMap>();
            List<HashMap> retResults = new ArrayList<HashMap>();

            Test test = testRepository.getReferenceById(id);

            String description = test.getDescription();
            String title = test.getTitle();

            questionRepository.getQuestionsByTestId(id).forEach(questions::add);

            for (int i = 0; i < questions.size(); i++) {
                HashMap<String, String> m = new HashMap<String, String>();
                m.put("id", Long.toString(questions.get(i).getId()));
                m.put("title", questions.get(i).getTitle());
                m.put("description", questions.get(i).getDescription());
                retQuestions.add(m);
            }

            resultRepository.getResultsByTestId(id).forEach(results::add);

            for (int i = 0; i < results.size(); i++) {
                HashMap<String, String> m = new HashMap<String, String>();
                m.put("id", Long.toString(results.get(i).getId()));
                m.put("title", results.get(i).getTitle());
                m.put("description", results.get(i).getDescription());
                retResults.add(m);
            }


            model.addAttribute("id", id);
            model.addAttribute("description", description);
            model.addAttribute("title", title);
            model.addAttribute("questions", retQuestions);
            model.addAttribute("results", retResults);
            return "test";

        } catch (Exception e) {
            return "error";
        }
    }

    @GetMapping("/runtest")
    public String runTest(@RequestParam(required = true) Long id, Model model) {
        try {
            List<Question> questions = new ArrayList<Question>();
            List<HashMap> retQuestions = new ArrayList<HashMap>();

            Test test = testRepository.getReferenceById(id);

            String description = test.getDescription();
            String title = test.getTitle();

            questionRepository.getQuestionsByTestId(id).forEach(questions::add);

            for (int i = 0; i < questions.size(); i++) {
                HashMap<String, String> m = new HashMap<String, String>();
                m.put("id", Long.toString(questions.get(i).getId()));
                m.put("title", questions.get(i).getTitle());
                m.put("description", questions.get(i).getDescription());
                retQuestions.add(m);
            }
            model.addAttribute("id", id);
            model.addAttribute("description", description);
            model.addAttribute("title", title);
            model.addAttribute("quantityQuestions", questions.size());
            model.addAttribute("questions", retQuestions);
            return "runtest";

        } catch (Exception e) {
            return "error";
        }
    }

    @RequestMapping(value = "/test/update", method = RequestMethod.POST)
    public String updateTest(@ModelAttribute("SpringWeb") Test test, ModelMap model, @RequestParam(required = true) Long id) {

        Test testUpdate = testRepository.getReferenceById(id);
        testUpdate.setTitle(test.getTitle());
        testUpdate.setDescription(test.getDescription());
        testRepository.save(testUpdate);
        model.addAttribute("value", "/tests");
        return "open";
    }

    @GetMapping("/test/create")
    public String createTest(@RequestParam(required = false) Long testId, Model model) {
        try {
            if (testId != null) {
                model.addAttribute("testId", testId);
            }
            return "createTest";

        } catch (Exception e) {
            return "error";
        }
    }

    @RequestMapping(value = "/test/create/new", method = RequestMethod.POST)
    public String addTest(@ModelAttribute("SpringWeb") Test test, ModelMap model) {

        testRepository.save(test);
        model.addAttribute("value", "/tests");
        return "open";
    }

    @RequestMapping(value = "/test/delete")
    public String deleteTest(ModelMap model, @RequestParam(required = true) Long id) {
        Test test = testRepository.getReferenceById(id);
        testRepository.delete(test);
        model.addAttribute("value", "/tests");
        return "open";
    }
}
