package controller.login;

//import Respiratory.User.UserDTO;
import common.GoogleSupport;
import model.GoogleDTO;
import common.Captcha;
import common.SendEmail;
import dal.SignUpDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import model.User;

public class GoogleSignInController extends HttpServlet {
    Captcha c = new Captcha();
    String rd = c.generateCaptcha();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String code = request.getParameter("code");
        String accessToken = GoogleSupport.getToken(code);
        GoogleDTO userToken = GoogleSupport.getUserInfo(accessToken);
        SignUpDAO se = new SignUpDAO();
        User u = se.checkUser(userToken.getEmail());
        String email = userToken.getEmail();
        String name = userToken.getName();
        if (u == null) {
            se.signup(userToken.getName(), userToken.getEmail(), rd);
             u = se.checkUser(userToken.getEmail());
              request.getSession().setAttribute("user", u);
            request.getRequestDispatcher("view/homepage.jsp").forward(request, response);
//            SendEmail sm = new SendEmail();
//            String rd = sm.sendEmail(email);
//            request.getSession().setAttribute("rd", rd);
//            request.getSession().setAttribute("email", email);
//            request.getSession().setAttribute("name", name);
//
//            request.getRequestDispatcher("view/login/regisgoogle.jsp").forward(request, response);

        } else {
            request.getSession().setAttribute("user", u);

            request.getRequestDispatcher("view/homepage.jsp").forward(request, response);
        }

//        String URL = "view.jsp";
//        String username = userToken.getId();
//        String email = userToken.getEmail();
//        String fullname = userToken.getName();
//        String picture = userToken.getPicture();
//        try {
//            UserDTO user = new UserDTO(username, email, null,
//                    fullname, picture, "User", null, null, null, null);
//            HttpSession session = request.getSession(true);
//            session.setAttribute("user", user);
//            URL = "view.jsp";
//        } finally {
//            RequestDispatcher rd = request.getRequestDispatcher(URL);
//            rd.forward(request, response);
//        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
