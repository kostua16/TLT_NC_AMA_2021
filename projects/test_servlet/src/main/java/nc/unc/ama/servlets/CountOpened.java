package nc.unc.ama.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CountOpened extends HttpServlet {

  private static Integer counter = 0;

  // Для получения данных
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html");
    PrintWriter printWriter = response.getWriter();
    final HttpSession session = request.getSession();
    String user = (String) session.getAttribute("login");
    Integer openedByUser = (Integer) session.getAttribute("opened");
    if (user == null) {
      user = "Unknown";
    }
    if (openedByUser == null) {
      openedByUser = 0;
    }
    counter++;
    openedByUser++;
    session.setAttribute("opened", openedByUser);
    printWriter.write("<html>");
    printWriter.write("<h1>Hello " + user + "!</h1><br/>");
    printWriter.write("<h3> Page opened by all users for " + counter + " times!</h3><br/>");
    printWriter.write("<h3> Page opened by you for " + openedByUser + " times!</h3><br/>");
    printWriter.write("<h3> <a href='index.jsp'>Home</a></h3><br/>");
    printWriter.write("</html>");
    printWriter.close();
  }
}
