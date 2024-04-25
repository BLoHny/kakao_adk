package px;

import com.fasterxml.jackson.databind.ObjectMapper;
import config.PrivateConfig;
import dto.KakaoTokenResponse;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class GetKakaoToken {

        private static final String GRANT_TYPE = "authorization_code";

        public KakaoTokenResponse execute(PrivateConfig config, String code) {

            HttpClient httpClient = HttpClient.newHttpClient();

            String formData = "grant_type=" + GRANT_TYPE +
                    "&client_id=" + config.getCLIENT_ID() +
                    "&redirect_uri=" + config.getREDIRECT_URI() +
                    "&code=" + code;

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://kauth.kakao.com/oauth/token"))
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .POST(BodyPublishers.ofString(formData))
                    .build();

            try {
                HttpResponse<InputStream> httpResponse = httpClient.send(request, BodyHandlers.ofInputStream());

                if (httpResponse.statusCode() == 200) {
                    InputStream responseBody = httpResponse.body();
                    ObjectMapper objectMapper = new ObjectMapper();
                    return objectMapper.readValue(responseBody, KakaoTokenResponse.class);
                } else {
                    System.err.println("HTTP request failed with status code: " + httpResponse.statusCode());
                    return null;
                }
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
                return null;
            }
        }
}
