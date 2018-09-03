package ru.lanit.department;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class RequestAnswer {
    private HttpStatus status;
    private String message;
    private Object body;

    public RequestAnswer() {
    }

    public RequestAnswer(String message, Object body) {
        this.status = HttpStatus.OK;
        this.message = message;
        this.body = body;
    }

    public RequestAnswer(HttpStatus status, String message, Object body) {
        this.status = status;
        this.message = message;
        this.body = body;
    }
}
