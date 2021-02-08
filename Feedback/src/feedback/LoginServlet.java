package feedback;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        
//        int regno=Integer.parseInt(request.getParameter("regno"));
        String email= request.getParameter("email");
        String password = request.getParameter("password");
//        System.out.println(email);
//        System.out.println(password);
        RegisterBean rb=new RegisterBean();
		rb.setEmail(email);
		rb.setPassword(password);
        DAO dao=new DAO();
//        try {
//			System.out.println(dao.login(rb));
//		} catch (ClassNotFoundException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} catch (SQLException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
        try {
            if(dao.login(rb)){
            	HttpSession hs=request.getSession();
    			hs.setAttribute("regno", dao.getRegno(email));
    			
                 RequestDispatcher rs = request.getRequestDispatcher("feedback.html");
                 
                 rs.include(request, response);
                 out.print("Welcome "+dao.getRegno(email));
            }
            else{
                out.print("Username or Password incorrect");
                response.sendRedirect("login.html");
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

