package com.example.demo.controller.Question;

import com.example.demo.model.Question;
import com.example.demo.model.Test;
import com.example.demo.repository.QuestionRepository;
import com.example.demo.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;


@Controller
public class QuestionController {


    @Autowired
    TestRepository testRepository;
    @Autowired
    QuestionRepository questionRepository;

    @GetMapping("/question")
    public String getQuestion(@RequestParam(required = true) Long id, Model model) {
        try {

            Question question = questionRepository.getReferenceById(id);

            String description = question.getDescription();
            String title = question.getTitle();
            Test test = question.getTest();
            long testId = test.getId();
            String testTitle = test.getTitle();

            model.addAttribute("id", id);
            model.addAttribute("description", description);
            model.addAttribute("title", title);
            model.addAttribute("testId", testId);
            model.addAttribute("testTitle", testTitle);
            return "question";

        } catch (Exception e) {
            return "error";
        }
    }

    @GetMapping("/question/create")
    public String createQuestion(@RequestParam(required = false) Long testId, Model model) {
        try {
            if (testId != null) {
                model.addAttribute("testId", testId);
            }
            return "createQuestion";

        } catch (Exception e) {
            return "error";
        }
    }

    @RequestMapping(value = "/question/create/new", method = RequestMethod.POST)
    public String addQuestion(@ModelAttribute("SpringWeb") Question question, ModelMap model, @RequestParam(required = true) Long testId) {

        question.setTest(testRepository.getReferenceById(testId));
        questionRepository.save(question);
        model.addAttribute("value", "/test?id=" + testId);
        return "open";
    }


    @RequestMapping(value = "/question/update", method = RequestMethod.POST)
    public String updateQuestion(@ModelAttribute("SpringWeb") Question question, ModelMap model, @RequestParam(required = true) Long testId, @RequestParam(required = true) Long id) {

        question.setTest(testRepository.getReferenceById(testId));
        Question questionUpdate = questionRepository.getReferenceById(id);
        questionUpdate.setTitle(question.getTitle());
        questionUpdate.setDescription(question.getDescription());
        questionRepository.save(questionUpdate);
        model.addAttribute("value", "/test?id=" + testId);
        return "open";
    }

    @RequestMapping(value = "/question/delete")
    public String deleteQuestion(ModelMap model, @RequestParam(required = true) Long id) {
        Question question = questionRepository.getReferenceById(id);
        questionRepository.delete(question);
        model.addAttribute("value", "/test?id=" + question.getTest().getId());
        return "open";
    }
}
