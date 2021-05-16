package com.example;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * This is the index page of the program,
 * ShowQuestions reads the questions file, and displays them on the web.
 */
@WebServlet(name = "ShowQuestions", value = "/ShowQuestions", initParams = {@WebInitParam(name = "path", value = "questions.txt")})
public class ShowQuestions extends HttpServlet {

    /**
     * Reads the questions file, and stores them in the map.
     */
    public void init() {
        String path = getServletContext().getRealPath(getInitParameter("path"));
        String file = null;
        try {
            file = new String(Files.readAllBytes(Paths.get(path)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String lines[] = file.split("\\r?\\n");

        Map<Integer, QandA> map = new HashMap<>();

        for (int i = 0; i < lines.length; i++) {
            QandA qandA = new QandA(lines[i]);
            map.put(i, qandA);
        }
        ServletContext servletContext = getServletContext();
        servletContext.setAttribute("questions", map);
    }

    /**
     * The main function in this class.
     * Display the questions.
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        ServletContext servletContext = getServletContext();

        Map<Integer, QandA> map = (Map<Integer, QandA>) servletContext.getAttribute("questions");

        PrintWriter out = response.getWriter();
        StringBuilder toHTML = new StringBuilder();

        request.getRequestDispatcher("/head.html").include(request, response);

        for (Map.Entry<Integer, QandA> entry : map.entrySet()) {

            toHTML.append(MessageFormat.format("<div class=\"border-bottom m-4\">" +
                            "{0}<br>{1} Answers<br>" +
                            "<div class=\"btn-toolbar \" >" +
                            "<a class=\"btn btn-primary\" " +
                            "href=\"/WriteAnswer?var={2}\" role=\"button\">Answer</a>",
                    entry.getValue().getQuestion(),
                    entry.getValue().getAnswers().size(),
                    entry.getKey()));

            toHTML.append(MessageFormat.format("<button id=\"button{0}\" type=\"button\" " +
                            "class=\"display btn btn-primary mx-2\" data-pid=\"{0}\">Show answers</button></div>",
                    entry.getKey()));
            toHTML.append(MessageFormat.format("<div id=\"{0}\" ></div></div>", entry.getKey()));
        }
        out.print(toHTML);
        request.getRequestDispatcher("/end.html").include(request, response);
        out.close();
    }

    /**
     * opss... Redirect to index page.
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("/ShowQuestions");
    }

}