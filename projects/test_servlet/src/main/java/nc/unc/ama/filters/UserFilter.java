package nc.unc.ama.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import nc.unc.ama.entities.UserInfo;
import nc.unc.ama.services.UsersDatabase;

@WebFilter(filterName = "UserFilter", urlPatterns = {"*"})
public class UserFilter implements Filter {

  @Override
  public void init(FilterConfig config) {
  }

  @Override
  public void destroy() {
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    final String login = (String) httpRequest.getSession(false).getAttribute("login");
    if (login != null) {
      final UserInfo user = UsersDatabase.getInstance().findUser(login);
      if (user != null) {
        httpRequest.getServletContext().setAttribute("user", user);
      }
    }
    httpRequest.getServletContext().setAttribute("UserFilterExecuted", true);
    chain.doFilter(httpRequest, response);
  }
}
