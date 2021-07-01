package com.example.QLKho.Controller;

import com.example.QLKho.Model.Brand;
import com.example.QLKho.Model.BrandDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "BrandServlet", urlPatterns = {"/listbrand","/newbrand","/insertbrand","/deletebrand","/editbrand","/updatebrand"})
public class BrandServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private BrandDAO brandDAO;

    public void init() {
        brandDAO = new BrandDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        try {
            switch (action) {
                case "/newbrand":
                    showNewForm(request, response);
                    break;
                case "/insertbrand":
                    insertBrand(request, response);
                    break;
                case "/deletebrand":
                    deleteBrand(request, response);
                    break;
                case "/editbrand":
                    showEditForm(request, response);
                    break;
                case "/updatebrand":
                    updateBrand(request, response);
                    break;
                default:
                    listBrand(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listBrand(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        List<Brand> listBrand = brandDAO.getAllBrands();
        request.setAttribute("listBrand", listBrand);
        RequestDispatcher dispatcher = request.getRequestDispatcher("brand-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("brand-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Brand existingBrand = brandDAO.getBrandById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("brand-form.jsp");
        request.setAttribute("brand", existingBrand);
        dispatcher.forward(request, response);
    }

    private void insertBrand(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String name = request.getParameter("name");
        int is_enabled = Integer.parseInt(request.getParameter("is_enabled"));
        String description = request.getParameter("description");
        Brand newBrand = new Brand(name, is_enabled, description);
        brandDAO.insertBrand(newBrand);
        response.sendRedirect("listbrand");
    }

    private void updateBrand(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        int is_enabled = Integer.parseInt(request.getParameter("is_enabled"));
        String description = request.getParameter("description");
        Brand brand = new Brand(id, name, is_enabled, description);
        brandDAO.updateBrand(brand);
        response.sendRedirect("listbrand");
    }

    private void deleteBrand(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Brand brand = new Brand(id);
        brandDAO.deleteBrand(brand);
        response.sendRedirect("listbrand");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
