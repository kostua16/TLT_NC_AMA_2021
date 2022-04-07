package nc.unc.ama.test_spring_app.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {


    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public String errorController(
        final Model model,
        final MethodArgumentNotValidException mie
    ) {
        model.addAttribute("message", mie.getMessage());

        return "404error";
    }

}
