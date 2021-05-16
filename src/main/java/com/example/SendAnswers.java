package com.example;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * This class is used by js script to fetch answer by ID of the question.
 */
@WebServlet(name = "SendAnswers", value = "/SendAnswers")
public class SendAnswers extends HttpServlet {
    /**
     * The main function in this class.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletContext servletContext = getServletContext();

        Map<Integer, QandA> map = (Map<Integer, QandA>) servletContext.getAttribute("questions");
        int var = Integer.parseInt(request.getParameter("id"));

        QandA q = map.get(var);

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try {
            out.print(q.getJsonAnswers());
        } catch (Exception e) {
            response.sendRedirect("/ShowQuestions");
        }
        out.flush();
        out.close();
    }

    /**
     * opss... Redirect to index page.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/ShowQuestions");
    }
}
