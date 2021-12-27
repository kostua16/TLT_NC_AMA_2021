package bank_system;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AuthorisationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Client> clients = new ArrayList<>();
        clients.add(new Client(788, "f342", "456"));
        clients.add(new Client(100, "lk34", "123"));
        clients.add(new Client(1000, "fr78", "789"));
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Client client = Bankomat.testClient(clients, username, password);
        if (client == null) {
            request.getRequestDispatcher("/static/index.html").forward(request, response);
        } else {
            session.setAttribute("client", client);
            request.getRequestDispatcher("/static/enterToBank.html").forward(request, response);
        }
    }
}
