package com.sres.filter;

import com.sres.model.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author bbto
 */
public class ProfessorAuthFilter implements Filter {
    protected FilterConfig config;

    public void init(FilterConfig config) throws ServletException {
        this.config = config;
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        try {
            boolean authorized = false;
            if (request instanceof HttpServletRequest) {
                HttpSession session = ((HttpServletRequest) request).getSession(false);
                if (session != null) {
                    User current_user = (User) session.getAttribute(User.SESSION_ATTRIBUTE);
                    if ((current_user != null) && current_user.isProfessor()) {
                        authorized = true;
                    }
                }
            }

            if (authorized) {
                chain.doFilter(request, response);
            } else if (config != null) {
                /*ServletContext context = config.getServletContext();
                String login_page = context.getInitParameter("login_page");
                if (login_page != null && !"".equals(login_page)) {
                context.getRequestDispatcher(login_page).forward(request, response);
                }*/
                showWarning(response);
            } else {
                throw new ServletException("Unauthorized access");
            }
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        } catch (ServletException ex) {
            ex.printStackTrace(System.err);
        }
    }

    public void destroy() {
        config = null;
    }

    private void showWarning(ServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String docType =
                "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
                "Transitional//EN\">\n";
        out.println(docType +
                "<HTML>\n" +
                "<HEAD><TITLE>Forbidden</TITLE></HEAD>\n" +
                "<BODY BGCOLOR=\"WHITE\">\n" +
                "<H1>Access Prohibited</H1>\n" +
                "Sorry, access is not allowed.\n" +
                "</BODY></HTML>");
    }

}
