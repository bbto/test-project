package com.sres.servlet;

import com.sres.model.User;
import com.sres.persistence.DatabaseManager;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author bbto
 */
public class AuthServlet extends HttpServlet {

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

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        DatabaseManager db = DatabaseManager.getInstance();
        if (db != null) {
            password = db.getEncriptedPassword(password);
            User user = User.find_by_email(email);
            if (user != null) {
                String user_password = User.getPassword(user);
                if (password.equals(user_password)) {
                    HttpSession session = ((HttpServletRequest) request).getSession(false);
                    session.setAttribute(User.SESSION_ATTRIBUTE, user);
                    if (user.isAdmin()) {
                        response.sendRedirect(request.getContextPath() + "/admin/");
                    }
                    if (user.isProfessor()) {
                        response.sendRedirect(request.getContextPath() + "/professor/");
                    }
                    if (user.isStudent()) {
                        response.sendRedirect(request.getContextPath() + "/student/");
                    }
                } else {
                    //TODO: set up and send error message
                    response.sendRedirect(request.getContextPath() + "/login.jsp");
                }
            } else {
                //TODO: set up and send error message
                response.sendRedirect(request.getContextPath() + "/login.jsp");
            }
        } else {
            //TODO: set up and send error message
            response.sendRedirect(request.getContextPath() + "/error.jsp");
        }
        db.close();
    }

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
        return "Servlet used to athenticate users.";
    }// </editor-fold>
}
