import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.json.JSONObject;

@Path("/")
public class UserAPI {
    @GET
    @Path("/{round_number}/question")
    @Produces(MediaType.APPLICATION_JSON)
    public String getQuestion(@PathParam("round_number") String round_number) {
        JSONObject question = new JSONObject();
        question.put("question_text", "Hello, World!");
        return question.toString();
    }
}