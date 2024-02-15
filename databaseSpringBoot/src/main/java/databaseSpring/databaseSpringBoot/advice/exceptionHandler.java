package databaseSpring.databaseSpringBoot.advice;
import databaseSpring.databaseSpringBoot.controller.errorinlist;
import databaseSpring.databaseSpringBoot.exception.userNotFoundException;
import databaseSpring.databaseSpringBoot.exception.userNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.HashMap;
import java.util.Map;
@RestControllerAdvice
public class exceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> handleinvalidarguments(MethodArgumentNotValidException ex)
    {
        Map<String,String>errormap=new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error->{
            errormap.put(error.getField(),error.getDefaultMessage());
        });
        return errormap;
    }

    @ExceptionHandler(userNotFoundException.class)
    public Map<String,String> handleinvalidarguments(userNotFoundException ex)
    {
        Map<String,String>errormap=new HashMap<>();

        errormap.put("error",ex.getMessage());
        return errormap;
    }

    @ExceptionHandler(NullPointerException.class)
    public String errorMessage(NullPointerException ex)
    {
        return "Please Enter the Valid Id";
    }



}
