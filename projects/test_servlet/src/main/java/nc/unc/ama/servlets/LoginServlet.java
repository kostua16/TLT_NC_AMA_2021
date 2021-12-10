package nc.unc.ama.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import nc.unc.ama.services.UsersDatabase;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
    IOException {
    response.sendRedirect(request.getContextPath() + "/index.jsp");
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    final String login = request.getParameter("login");
    final String pass = request.getParameter("pass");
    String returnTo = request.getParameter("returnTo");
    if (returnTo == null) {
      returnTo = "index.jsp";
    }
    if (login != null && pass != null) {
      if (UsersDatabase.getInstance().testUser(login, pass)) {
        final HttpSession session = request.getSession();
        session.setAttribute("login", login);
        session.setAttribute("opened", 0);
      } else {
        returnTo = "/401.jsp?returnTo="+returnTo;
      }
    }
    response.sendRedirect(request.getContextPath() + "/" + returnTo);
  }
}
