package com.example;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * This class reads the answer from the form, and stores it in data structure.
 */
@WebServlet(name = "AddAnswerTOArraay", value = "/AddAnswerTOArraay")
public class AddAnswerTOArraay extends HttpServlet {
    /**
     * opss... Redirect to index page.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/ShowQuestions");
    }

    /**
     * The main function in this class.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletContext servletContext = getServletContext();
        Map<Integer, QandA> map = (Map<Integer, QandA>) servletContext.getAttribute("questions");

        int id = Integer.parseInt(request.getParameter("qId"));
        QandA q = map.get(id);
        String name = request.getParameter("name");
        String answer = request.getParameter("answer");
        if (name != null && !name.trim().isEmpty() && answer != null && !answer.trim().isEmpty()) {
            q.setAnswers("(" + name + ") " + answer);
        }
        response.sendRedirect("/ShowQuestions");
    }
}
