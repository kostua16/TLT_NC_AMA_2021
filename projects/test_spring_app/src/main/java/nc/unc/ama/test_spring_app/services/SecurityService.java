package nc.unc.ama.test_spring_app.services;

public interface SecurityService {
    boolean isAuthenticated();
    void autoLogin(String username, String password);
}
