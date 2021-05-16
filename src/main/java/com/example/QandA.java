package com.example;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import java.util.ArrayList;

/**
 * This class hold the questions and answers data structure.
 * The class contains String as a question, and ArrayList of String as answers.
 */
public class QandA {
    /**
     * Hold the question.
     */
    private String question;
    /**
     * Hold the answers in ArrayList of String.
     */
    private ArrayList<String> answers = new ArrayList<>(0);

    /**
     * Constructs a new QandA with the specified String.
     * @param s the question to store.
     */
    public QandA(String s) {
        this.question = s;
    }

    /**
     * return the question of given QandA object.
     * @return the question
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Store the answer of given QandA object.
     * @param answers the answer to store.
     */
    public void setAnswers(String answers) {
        this.answers.add(answers);
    }

    /**
     * return all answers of given QandA object.
     * @return ArrayList of answers
     */
    public ArrayList<String> getAnswers() {
        return answers;
    }

    /**
     * return Json that hold the answers.
     * @return answers Json.
     */
    public JsonArray getJsonAnswers() {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (String s : answers) {
            JsonObjectBuilder builder = Json.createObjectBuilder().add("answer", s);
            arrayBuilder.add(builder.build());
        }
        return arrayBuilder.build();
    }
}
