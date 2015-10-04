package servlet;

import com.google.gson.JsonObject;
import managers.RunManager;
import managers.UserManager;
import models.Question;
import models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Diese Klasse dient als Schnittstelle zur Appliktion. Sie kann via HTTP mit REST aufgerufen werden.
 * <p>
 * Mit dieser Schnittstelle kann die sinnvollste nächste Frage abgeholt werden.
 * <p>
 * Weitere Informationen über was reinkommen soll und rausgeht können in der Schnittstellendokumentation gefunden werden
 *
 * @author Nathan Bourquin
 */
@WebServlet(
        name = "NextQuestionServlet",
        urlPatterns = {"/nextQuestion/user/*"}
)
public class NextQuestionServlet extends ServletAbstract {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("user") == null) {
            resp.getWriter().println("You are not authorized to view this data.");
        } else {
            String path = req.getPathInfo();
            if (path != null) {
                User user = new UserManager().get(getId(path));
                final Question question = new RunManager(user).getNextQuestion();

                final String answer = gson.toJson(question);
                sendResponse(answer, resp);
            }
        }
    }

}
