package nc.unc.ama.guest_service.controller;

import nc.unc.ama.guest_service.exceptions.NotFoundExceptions;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("feedback")
public class FeedbackController {
    private int counter = 4;
    private final List<Map<String, String>> feedbacks = new ArrayList<>();

    @GetMapping
    public List<Map<String, String>> list(){
        return feedbacks;
    }

    @GetMapping("{commentId}")
    public Map<String, String> getFeedB(@PathVariable String commentId){
        return feedbacks.stream()
                .filter(feedback -> feedback.get("commentId").equals(commentId))
                .findFirst()
                .orElseThrow(NotFoundExceptions::new);
    }

    @PostMapping
    public Map<String, String> create(@RequestBody Map<String, String> feedback){
        feedback.put("commentId", String.valueOf(counter++));
        feedbacks.add(feedback);
        return feedback;
    }

    @PutMapping("{commentId}")
    public Map<String, String> update(@PathVariable String commentId, @RequestBody Map<String, String> feedback){
        Map<String, String> feedBackFromDb = getFeedB(commentId);
        feedBackFromDb.putAll(feedback);
        feedBackFromDb.put("commentId", commentId);
        return feedBackFromDb;
    }

    @DeleteMapping("{commentId}")
    public void delete(@PathVariable String commentId){
        Map<String, String> feedback = getFeedB(commentId);
        feedbacks.remove(feedback);
    }
}
