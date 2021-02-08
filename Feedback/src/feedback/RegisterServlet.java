package feedback;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public RegisterServlet() {
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int regno=Integer.parseInt(request.getParameter("regno"));//String
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		RegisterBean rb=new RegisterBean();
		rb.setRegno(regno);
		rb.setEmail(email);
		rb.setPassword(password);
		
		DAO dao=new DAO();
		try {
			dao.create();
			int i=dao.insert(rb);
			if(i>0) {
				RequestDispatcher rd=request.getRequestDispatcher("login.html");
			    request.setAttribute("RegisterBean",rb);
			    rd.forward(request, response);
			}
			else {
				RequestDispatcher rd=request.getRequestDispatcher("error.html");
			    rd.include(request, response);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
