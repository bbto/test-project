/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sres.servlet;

import com.sres.persistence.DatabaseManager;
import com.sres.util.Scribd;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

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
        String document = request.getParameter("document");

        System.out.println(name);
        System.out.println(description);
        System.out.println(type);
        System.out.println(link);

        File[] files = uploadAttachedFiles(request);
        if(files!=null) {
            uploadToScribd(files[0]);
        }

        /*<%@ page import="java.util.List" %>
        <%@ page import="java.util.Iterator" %>
        <%@ page import="java.io.File" %>
        <%@ page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
        <%@ page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
        <%@ page import="org.apache.commons.fileupload.*"%>
        <%@ page contentType="text/html;charset=UTF-8" language="java" %>
        <center><table border="2">
        <tr><td><h1>Your files  uploaded </h1></td></tr>
        <%*/
        /*%>
        </table>
        </center>*/



        DatabaseManager db = DatabaseManager.getInstance();
        if (db != null) {
            /*User user = new User(true);
            user.setFirstname(name);
            user.setEmail(email);
            user.setLastname(lastname);
            user.setPassword(password);
            user.setRole(1);
            if (user.save()) {
            response.sendRedirect(request.getContextPath() + "/admin/professors.jsp");
            } else {
            response.sendRedirect(request.getContextPath() + "/error.jsp");
            }*/
        } else {
            response.sendRedirect(request.getContextPath() + "/error.jsp");
        }
    }

    private void uploadToScribd(File file) {
        String scribd_api_key = "1jxl2rw3bydvz7rrhbbsr";
        String scribd_secret = "sec-se1l6r5sg9e5nn0ao0n6sib22";
        Scribd scribd = new Scribd(scribd_api_key, scribd_secret);
        String username = "gealgaro";
        String password = "123456789";
        if(scribd.login(username, password)) {
            String filename = file.getAbsolutePath();
            Hashtable data = scribd.upload(filename, null);
            System.out.println(data.toString());
        }
    }

    private File[] uploadAttachedFiles(HttpServletRequest request) {
        File [] files = null;
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if (isMultipart) {
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            List items = null;
            try {
                int i = 0;
                items = upload.parseRequest(request);
                files = new File[items.size()];
                Iterator itr = items.iterator();
                while (itr.hasNext()) {
                    FileItem item = (FileItem) itr.next();
                    if (item.isFormField()) {
                    } else {
                        try {
                            String itemName = item.getName();
                            //File savedFile = new File(config.getServletContext().getRealPath("/") + "uploadedFiles/" + itemName);
                            File savedFile = File.createTempFile("tmp", itemName);
                            item.write(savedFile);
                            //out.println("<tr><td><b>Your file has been saved at the loaction:</b></td></tr><tr><td><b>" + config.getServletContext().getRealPath("/") + "uploadedFiles" + "\\" + itemName + "</td></tr>");
                            files[0] = savedFile;

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            } catch (FileUploadException ex) {
                ex.printStackTrace(System.err);
            }
        }
        return files;
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
