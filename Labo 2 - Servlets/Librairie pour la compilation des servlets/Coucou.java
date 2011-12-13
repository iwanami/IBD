import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Coucou extends HttpServlet {

  public void doGet(HttpServletRequest req, HttpServletResponse res) throws  
  ServletException, IOException {

     Calendar cal = new GregorianCalendar();
     int jour = cal.get(Calendar.DAY_OF_MONTH);
     int mois = cal.get(Calendar.MONTH);
     int annee = cal.get(Calendar.YEAR);
     int heure = cal.get(Calendar.HOUR);
     int min = cal.get(Calendar.MINUTE);
     int sec = cal.get(Calendar.SECOND);
     String date = "nous sommes le: "+jour+"."+mois+"."+annee+" et il est "+heure+":"+min+":"+sec;
     
     res.setContentType("text/html");

     PrintWriter out = res.getWriter();

     out.println("<html><head></head><body>");
	  out.println(date);
     out.println("</body></html>");

  }

}