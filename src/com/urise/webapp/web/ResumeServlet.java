package com.urise.webapp.web;

import com.urise.webapp.Config;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.SqlStorage;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ResumeServlet extends HttpServlet {
    private SqlStorage storage; // = Config.getInstance().getStorage();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        storage = Config.getInstance().getStorage();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
//        response.setHeader("Content-Type", "text/html; charset=UTF-8");
//        String name = request.getParameter("name");
//        response.getWriter().write(name == null ? "Hello Resumes!" : "Hello " + name + '!');
        printResumes(response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void printResumes(HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();

        writer.println("<table>\n<caption>Table for resumes</caption>\n<tr>\n<th>UUID</th>\n<th>Full Name</th>\n</tr>");
        for (Resume r : storage.getAllSorted()) {
            writer.println("<tr>\n<td>" + r.getUuid() + "</td>\n<td>" + r.getFullName() + "</td>\n</tr>");
        }
        writer.print("</table>");
    }
}
