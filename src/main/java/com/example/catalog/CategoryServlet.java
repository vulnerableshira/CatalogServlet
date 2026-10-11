package com.example.catalog;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class CategoryServlet extends HttpServlet {

    private List<Category> categories;

    @Override
    public void init() {
        categories = new ArrayList<>();

        categories.add(new Category("CAT-01", "Elektronik"));
        categories.add(new Category("CAT-02", "Aksesoris"));
        categories.add(new Category("CAT-03", "Peralatan Komputer"));
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Daftar Kategori</title>");
        out.println("</head>");
        out.println("<body>");

        out.println("<h2>Daftar Kategori</h2>");
        out.println("<ul>");

        for (Category category : categories) {
            out.println("<li>" + category.getName() + "</li>");
        }

        out.println("</ul>");
        out.println("</body>");
        out.println("</html>");
    }
}