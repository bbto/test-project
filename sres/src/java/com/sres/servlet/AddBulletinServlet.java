/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sres.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

import javax.mail.*;

import javax.mail.internet.*;   // important



import java.util.*;

/**
 *
 * @author bbto
 */
public class AddBulletinServlet extends HttpServlet {

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
        PrintWriter out = response.getWriter();


        try {

            Properties props = new Properties();

            props.put("mail.smtp.host", "localhost");   //  'localhost' for testing

            Session session1 = Session.getDefaultInstance(props, null);

            String s1 = "bulletin@sres.com"; //sender (from)

            String s2 = request.getParameter("text2");

            String s3 = request.getParameter("subject");

            String s4 = request.getParameter("body");

            Message message = new MimeMessage(session1);

            message.setFrom(new InternetAddress(s1));

            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(s2, false));

            message.setSubject(s3);

            message.setText(s4);
            
            message.setSentDate(new Date(request.getParameter("sent_on")));

            Transport.send(message);

            out.println("mail has been sent");

        } catch (Exception ex) {

            response.sendRedirect(request.getContextPath() + "/error.jsp");

        }
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
        return "Short description";
    }// </editor-fold>
}
