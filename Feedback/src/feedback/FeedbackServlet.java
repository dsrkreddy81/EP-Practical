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

public class FeedbackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public FeedbackServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String feedback = request.getParameter("feedback");
		
		PrintWriter pw=response.getWriter();
		
		HttpSession hs=request.getSession();
		String s=String.valueOf( hs.getAttribute("regno"));
		if(s!=null) {
		DAO dao=new DAO();
		try {
			int i=dao.feedback(Integer.parseInt(s), feedback);
			if(i>0) {
				RequestDispatcher rd=request.getRequestDispatcher("final.html");
				rd.include(request, response);
			}
			else {
				RequestDispatcher rd=request.getRequestDispatcher("error.html");
				rd.include(request, response);
			}
		}
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		else {
			pw.print("Feedback error");
		}
	}

}
