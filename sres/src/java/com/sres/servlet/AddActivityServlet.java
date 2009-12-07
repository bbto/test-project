/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sres.servlet;

import com.sres.model.User;
import com.sres.persistence.DatabaseManager;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Chiquitica
 */
public class AddActivityServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String type = request.getParameter("type");
        String link = request.getParameter("link");
        String video = request.getParameter("video");
        String document = request.getParameter("document");
        DatabaseManager db = DatabaseManager.getInstance();
        if (db != null) {
            User user = new User(true);
            user.setFirstname(name);
            user.setEmail(email);
            user.setLastname(lastname);
            user.setPassword(password);
            user.setRole(1);
            if (user.save()) {
                response.sendRedirect(request.getContextPath() + "/admin/professors.jsp");
            } else {
                response.sendRedirect(request.getContextPath() + "/error.jsp");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/error.jsp");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code."> 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
