package myname;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.servlet.jsp.jstl.core.Config;

/**
 * Servlet implementation class PassNameServlet
 */
@WebServlet("/DBProjectServlet")
@MultipartConfig
public class DBProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final String secretUserName = "admin";
	private final String secretPassword = "password";
	
	private final int sessionLength = 30; // in minutes
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DBProjectServlet() {
        super();
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		response.setContentType("text/html");
        HttpSession session = request.getSession();
        
        if(session.getAttribute("userName") == null) {
        	// if the user is not already logged in,
        	// then attempt to validate a login or redirect to the login page
        	
            if (request.getParameter("logIn") != null) {
            	// user is trying to log in... attempt to validate the login
            	String userName = request.getParameter("userName");
            	String password = request.getParameter("password");
            	
            	if (secretUserName.equals(userName) && secretPassword.equals(password)) {
            		// user has successfully logged in, set session vars and handle the request
            		session.setAttribute("userName", userName);
            		session.setAttribute("name", request.getParameter("name"));
            		session.setAttribute("language", request.getParameter("language"));
            		session.setMaxInactiveInterval(sessionLength * 60);
            		
            		handleRequest(request, response, session);
            	} else {
            		// user did not enter valid credentials
            		request.setAttribute("validationMessage", "You did not enter correct login information.");
                	gotoLoginPage(request, response);
            	}
            } else {
            	// user is not authorized yet... redirect to the login page
            	gotoLoginPage(request, response);
            }
        } else {
        	// the user is already logged in...
        	// so check for logout, or handle the request
        	if (request.getParameter("logOut") != null) {
        		// user is logging out, invalidate the session
        		session.invalidate();
        		request.setAttribute("validationMessage", "You have successfully logged out.");
        		gotoLoginPage(request, response);
        	} else {
        		// user did not request to log out
        		// update selected language if needed, and handle the request
        		if (request.getParameter("language") != null)
        			session.setAttribute("language", request.getParameter("language"));
        		handleRequest(request, response, session);
        	}
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//this.doGet(request, response);
		Part filePart = request.getPart("fileUpload");
		String filename = getFilename(filePart);
		
		InputStream fileContent = filePart.getInputStream();
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		
		int read;
		final byte[] bytes = new byte[1024];
		while ((read = fileContent.read(bytes)) != -1)
		{
			output.write(bytes, 0, read);
		}
		
		System.out.println(output.toString()); //toByteArray() for writing to a file
	}
	
	/**
	 * Forwards the user to the login page.
	 */
	protected void gotoLoginPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/include/Login.jsp");
    	rd.forward(request, response);
	}
	
	/**
	 * Handle the user's request.
	 */
	protected void handleRequest(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {
		Config.set(request, Config.FMT_LOCALE, session.getAttribute("language"));
        
        StateList stateList = new StateList();
        int id = 1;
        if (request.getParameter("country_id") != null)
        	id = Integer.valueOf(request.getParameter("country_id"));
        List<State> states = stateList.getResults(1,id);
        
        request.setAttribute("states", states);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/include/ShowNameAndStates.jsp");
        rd.forward(request, response);
        //response.getWriter().println("My Name: Tim Johnson");
	}
	
	
	private static String getFilename(Part part) {
	    for (String cd : part.getHeader("content-disposition").split(";")) {
	        if (cd.trim().startsWith("filename")) {
	            String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
	            return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.
	        }
	    }
	    return null;
	}
}
