package com.example.catalog;

import com.example.catalog.Product;
import com.example.catalog.ProductRepository;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {
    private ProductRepository productRepository;

    @Override
    public void init() {
        productRepository = new ProductRepository();
        productRepository.addProduct(new Product("PRD-01", "Keyboard Mechanical", 450000.0));
        productRepository.addProduct(new Product("PRD-02", "Mouse Wireless Silent", 175000.0));
        productRepository.addProduct(new Product("PRD-03", "Ikan Iwak", 100000.0));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String id = request.getParameter("id");

        if (id == null || id.isEmpty()) {

            out.println("<html>");
            out.println("<head><title>Katalog Produk Web</title></head>");
            out.println("<body>");
            out.println("<h2>=== DAFTAR KATALOG PRODUK (WEB) ===</h2>");
            out.println("<table border='1' cellpadding='8'>");
            out.println("<tr><th>ID Produk</th><th>Nama Produk</th><th>Harga Satuan</th></tr>");
            for (Product p : productRepository.findAll()) {
                out.println("<tr>");
                out.println("<td>" + p.getId() + "</td>");
                out.println("<td>" + p.getName() + "</td>");
                out.println("<td>Rp" + p.getPrice() + "</td>");
                out.println("</tr>");
            }
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");

            return;

            }
        

        try {
            Product product = productRepository.findById(id);

            out.println("<html>");
            out.println("<head><title>Detail Produk</title></head>");
            out.println("<body>");

            out.println("<h2>=== DETAIL PRODUK ===</h2>");

            out.println("<p><b>ID Produk:</b> " + product.getId() + "</p>");
            out.println("<p><b>Nama Produk:</b> " + product.getName() + "</p>");
            out.println("<p><b>Harga:</b> Rp" + product.getPrice() + "</p>");

            out.println("</body>");
            out.println("</html>");} 
            
            catch (ProductNotFoundException e) {

            // 3. Produk gak ada
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);

            out.println("<html>");
            out.println("<head><title>Produk Tidak Ditemukan</title></head>");
            out.println("<body>");

            out.println("<h2 style='color:red;'>Produk Tidak Ditemukan</h2>");
            out.println("<p style='color:red;'>" + e.getMessage() + "</p>");
            out.println("<h3 style='color:red;'>Go back twin!</h3>");
            out.println("<img src='jew.jpg' style='width:200px'>");

            out.println("</body>");
            out.println("</html>");
        }
    }    
}