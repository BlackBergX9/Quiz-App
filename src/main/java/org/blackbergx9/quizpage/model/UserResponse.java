package org.blackbergx9.quizpage.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
//@RequiredArgsConstructor
public class UserResponse {

//    @JsonProperty("questionId")   // To map the Request variable to the object variable... No need if both name are same.
//    private Integer id;   // needed here...

    private Integer questionId;
    private String response;

    public UserResponse(Integer questionId, String response) {
        this.questionId = questionId;
        this.response = response;
    }
}
