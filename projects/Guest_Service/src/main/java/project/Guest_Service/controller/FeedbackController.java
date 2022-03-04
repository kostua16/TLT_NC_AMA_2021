package project.Guest_Service.controller;

import org.springframework.web.bind.annotation.*;
import project.Guest_Service.exceptions.NotFoundExceptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("feedback")
public class FeedbackController {
    private int counter = 4;
    private List<Map<String, String>> feedbacks = new ArrayList<>(){{
       add(new HashMap<String, String>() {{ put("id", "1"); put("text", "YourFeedback");}});
       add(new HashMap<String, String>() {{ put("id", "2"); put("text", "YourFeedback");}});
       add(new HashMap<String, String>() {{ put("id", "3"); put("text", "YourFeedback");}});
    }};

    @GetMapping
    public List<Map<String, String>> list(){
        return feedbacks;
    }

    @GetMapping("{id}")
    public Map<String, String> getFeedB(@PathVariable String id){
        return feedbacks.stream()
                .filter(feedback -> feedback.get("id").equals(id))
                .findFirst()
                .orElseThrow(NotFoundExceptions::new);
    }

    @PostMapping
    public Map<String, String> create(@RequestBody Map<String, String> feedback){
        feedback.put("id", String.valueOf(counter++));
        feedbacks.add(feedback);
        return feedback;
    }

    @PutMapping("{id}")
    public Map<String, String> update(@PathVariable String id, @RequestBody Map<String, String> feedback){
        Map<String, String> feedBackFromDb = getFeedB(id);
        feedBackFromDb.putAll(feedback);
        feedBackFromDb.put("id", id);
        return feedBackFromDb;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        Map<String, String> feedback = getFeedB(id);
        feedbacks.remove(feedback);
    }
}
