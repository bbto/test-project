/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sres.servlet;

import com.sres.model.Rates;
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
public class AddAnswerServlet extends HttpServlet {

    private String activity;
    private String student_subject;
    private String answer;
    private String type;
    private String link;
    
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

        System.out.println("NOJODA ECHE");
        File[] files = uploadAttachedFiles(request);

        System.out.println(activity);
        System.out.println(student_subject);
        System.out.println(answer);
        System.out.println(type);
        System.out.println(link);

        Rates rate = new Rates(true);
        rate.setActivity_id(Integer.parseInt(activity));
        rate.setStudent_subject_id(Integer.parseInt(student_subject));
        rate.setAnswer(answer);
        rate.setType(Integer.parseInt(type));

        if (type.equals("1")) {
            if (files != null) {
                String params[] = uploadToScribd(files[0]);
                if (params != null) {
                    rate.setScribd_id(Integer.parseInt(params[0]));
                    rate.setScribd_key(params[1]);
                    if (rate.save()) {
                        response.sendRedirect(request.getContextPath() + "/student/index.jsp");
                        return;
                    }
                }
            }
        } else {
            rate.setLink(link);
            if (rate.save()) {
                response.sendRedirect(request.getContextPath() + "/student/index.jsp");
                return;
            }
        }
        response.sendRedirect(request.getContextPath() + "/error.jsp");
    } 

    private String[] uploadToScribd(File file) {
        String retval[] = null;
        String scribd_api_key = "1jxl2rw3bydvz7rrhbbsr";
        String scribd_secret = "sec-se1l6r5sg9e5nn0ao0n6sib22";
        Scribd scribd = new Scribd(scribd_api_key, scribd_secret);
        String username = "gealgaro";
        String password = "123456789";
        if (scribd.login(username, password)) {
            String filename = file.getAbsolutePath();
            Hashtable data = scribd.upload(filename, null);
            if (data != null) {
                retval = new String[2];
                retval[0] = data.get("doc_id").toString();
                retval[1] = data.get("access_key").toString();
            }
        }
        return retval;
    }

    private File[] uploadAttachedFiles(HttpServletRequest request) {
        File[] files = null;
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
                        if (item.getFieldName().equals("activity_id")) {
                            activity = item.getString();
                        } else if (item.getFieldName().equals("student_subject_id")) {
                            student_subject = item.getString();
                        } else if (item.getFieldName().equals("answer")) {
                            answer = item.getString();
                        } else if (item.getFieldName().equals("type")) {
                            type = item.getString();
                        } else if (item.getFieldName().equals("link")) {
                            link = item.getString();
                        }
                    } else {
                        try {
                            String itemName = item.getName();
                            //File savedFile = new File(config.getServletContext().getRealPath("/") + "uploadedFiles/" + itemName);
                            File savedFile = File.createTempFile("tmp", itemName);
                            item.write(savedFile);
                            //out.println("<tr><td><b>Your file has been saved at the loaction:</b></td></tr><tr><td><b>" + config.getServletContext().getRealPath("/") + "uploadedFiles" + "\\" + itemName + "</td></tr>");
                            files[0] = savedFile;
                        } catch (Exception ex) {
                            ex.printStackTrace(System.err);
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
