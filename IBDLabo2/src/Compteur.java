import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.http.*;


class Compteur extends HttpServlet{
	private static final long serialVersionUID = -6042065654936722748L;

	public static void main(String ... args){
		new Compteur();
	}
	
	public void init(ServletConfig conf) throws ServletException{
			super.init(conf);
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException{
		
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		out.println("<html><title>Salut!</title><body>");
		out.println("<p> Vous avez visité cette page " + setSession(req.getSession()) + " fois</p></body></hmtl>");
		out.close();
		
	}
	
	public Integer setSession(HttpSession ses){
		Integer count = (Integer) ses.getAttribute("Counter");
		if(count != null){
			ses.setAttribute("Counter", ++count);
			return count + 1;
		}else{
			ses.setAttribute("Counter", 1);
			return 1;
		}
		
	}
	
}
