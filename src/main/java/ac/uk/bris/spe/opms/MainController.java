package ac.uk.bris.spe.opms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import sun.misc.BASE64Encoder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

/**
 * Created by csxds on 24/11/2017.
 */
@Controller
public class MainController implements WebMvcConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);


    @GetMapping("/listProjects")
    @ResponseBody
    public String listProjects(@Value("${openproject.api-username}") final String username, @Value("${openproject.api-password}") final String password, @Value("${openproject.host}") final String openProjectHost) throws IOException {


        URL url = new URL(String.format("https://%s/api/v3/projects/1", openProjectHost));
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        String auth = username + ":" + password;
        connection.setRequestProperty("Authorization", "Basic " + Base64.getEncoder().encodeToString(auth.getBytes()));

        connection.setRequestMethod("GET");
        connection.setDoOutput(true);

        InputStream content = (InputStream) connection.getInputStream();
        BufferedReader in =
                new BufferedReader(new InputStreamReader(content));
        StringBuilder builder = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null) {
            builder.append(line);
            logger.debug(line);
        }


        return builder.toString();
    }

}
