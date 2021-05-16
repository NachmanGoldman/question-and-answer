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
 * This class hold the servlet that handle the writing answer (by the user) process.
 * The servlet present to user form HTML element for his own answer.
 */
@WebServlet(name = "WriteAnswer", value = "/WriteAnswer")
public class WriteAnswer extends HttpServlet {
    /**
     * The main function in this class.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletContext servletContext = getServletContext();

        Map<Integer, QandA> map = (Map<Integer, QandA>) servletContext.getAttribute("questions");

        PrintWriter out = response.getWriter();
        request.getRequestDispatcher("/head.html").include(request, response);

        StringBuilder toHTML = new StringBuilder();

        int var = Integer.parseInt(request.getParameter("var"));

        QandA q = map.get(var);
        try {
            q.getQuestion();
        } catch (NullPointerException e) {
            response.sendRedirect("/ShowQuestions");
        }
        toHTML.append("<h3 class=\"my-3\">The question is: " + q.getQuestion() + "</h3><br>");

        toHTML.append("<form name=\"myForm\"  action=\"/AddAnswerTOArraay\" method=\"post\" onsubmit=\"return validateForm()\">\n" +
                "<div class=\"form-group\">" +
                "<label for=\"name\">Your name</label>" +
                "<input type=\"text\" class=\"form-control\" id=\"name\" name=\"name\"><br><br>" +
                "<label for=\"answer\">Your answer</label>" +
                "<input type=\"text\" class=\"form-control\" id=\"answer\" name=\"answer\"><br><br>" +
                "<button type=\"submit\" class=\"btn btn-primary\" value=\"Submit\">Submit</button>" +
                "<a class=\"btn btn-primary mx-2\" href=\"/ShowQuestions\" role=\"button\">Cancel</a>" +
                "<input type=\"hidden\" id=\"qId\" name=\"qId\" value=\"" + var + "\">" +
                "</div>" +
                "</form>");

        out.print(toHTML);
        request.getRequestDispatcher("/end.html").include(request, response);
    }

    /**
     * opss... Redirect to index page.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/ShowQuestions");
    }
}
