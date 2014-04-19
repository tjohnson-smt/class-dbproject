/**
 * 
 */
package myname;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




/**
 * @author tim
 *
 */
@WebServlet("/myupload")
public class MyUpload extends HttpServlet {

	/**
	 * 
	 */
	public MyUpload() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException{
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
				    req.getInputStream()));
				  StringBuilder sb = new StringBuilder();
				  for (String line; (line = reader.readLine()) != null;) {
				   System.out.println(line);

				  }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
