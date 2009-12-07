/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sres.servlet;

import com.sres.model.StudentSubject;
import com.sres.model.User;
import com.sres.persistence.DatabaseManager;
import com.sres.util.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author bbto
 */
public class AddSubjectServlet extends HttpServlet {

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
        String professor = request.getParameter("professor");
        String competition = request.getParameter("competition");
        String students[] = request.getParameterValues("students");
        DatabaseManager db = DatabaseManager.getInstance();
        if (db != null) {
            ArrayList fields = new ArrayList();
            fields.add(competition);
            fields.add(professor);
            int id = db.insertAndReturnId("subjects", "(competition_id,professor_id,creation_date)", "(" + Util.concat(fields, ",") + ",now())");
            if (id == 0) {
                //TODO: Show error message
                response.sendRedirect(request.getContextPath() + "/error.jsp");
                return;
            } else {
                for (int i = 0; i < students.length; i++) {
                    StudentSubject ss = new StudentSubject(true);
                    ss.setSubject_id(id);
                    ss.setUser_id(Integer.parseInt(students[i]));
                    if (!ss.save()) {
                        //TODO: Show error message
                        response.sendRedirect(request.getContextPath() + "/error.jsp");
                        return;
                    }
                }
            }
            response.sendRedirect(request.getContextPath() + "/professor/subjects.jsp");
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
