/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sres.servlet;

import com.sres.model.Rates;
import com.sres.persistence.DatabaseManager;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspFactory;
import javax.servlet.jsp.PageContext;

/**
 *
 * @author Chiquitica
 */
public class AddRateServlet extends HttpServlet {
   
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
        String id = request.getParameter("id");
        String cuantification = request.getParameter("cuantification");
        String qualification = request.getParameter("qualification");
        DatabaseManager db = DatabaseManager.getInstance();
        if (db != null) {
            Rates rate = Rates.find_by_id(id);
            rate.setCuantification(Double.parseDouble(cuantification));
            rate.setQualification(qualification);
            if (rate.save()) {
                request.setAttribute("id", rate.getActivity_id());
                JspFactory _jspxFactory = JspFactory.getDefaultFactory();
                PageContext pageContext = _jspxFactory.getPageContext(this, request, response,
                        null, true, 16384, true);
                pageContext.forward("/professor/answers.jsp");
                //response.sendRedirect(request.getContextPath() + "/professor/");
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
