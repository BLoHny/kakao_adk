package px;

import com.fasterxml.jackson.databind.ObjectMapper;
import config.PrivateConfig;
import dto.KakaoTokenResponse;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.*;

class GetKakaoTokenTest {

    private static final String GRANT_TYPE = "authorization_code";

    @Test
    void test() {
        GetKakaoToken getKakaoToken = new GetKakaoToken();
        PrivateConfig privateConfig = new PrivateConfig("", "http://localhost:3000");
        var response = getKakaoToken.execute(privateConfig, "NEqh2qjxO6CQ0YKcOr1aAVMXHD5bRruER3w3sydEqII43R3dGQPgP5fxiMsKPXKYAAABjxLqiwsp9hBbJybEWQ");
        System.out.println(response);
    }

    @Test
    void hihi() throws Exception {

        PrivateConfig config = new PrivateConfig("", "http://voza-client-v1.vercel.app");
        HttpClient httpClient = HttpClient.newHttpClient();

        String formData = "grant_type=" + GRANT_TYPE +
                "&client_id=" + config.getCLIENT_ID() +
                "&redirect_uri=" + config.getREDIRECT_URI() +
                "&code=" + "ZPMjW2cQR-SDlpzCCZXnzp3nOTe81ObDK8oHuQl00kJQaoS8yCYcokyzoZMKPXPrAAABjxL3FFGm1x-HnlkNwQ";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://kauth.kakao.com/oauth/token"))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(formData))
                .build();

        try {
            HttpResponse<InputStream> httpResponse = httpClient.send(request, HttpResponse.BodyHandlers.ofInputStream());

            if (httpResponse.statusCode() == 200) {
                InputStream responseBody = httpResponse.body();
                ObjectMapper objectMapper = new ObjectMapper();
                System.out.println(objectMapper.readValue(responseBody, KakaoTokenResponse.class));
            } else {
                System.err.println("HTTP request failed with status code: " + httpResponse.statusCode());
                throw new Exception("hi");
            }
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
            throw new Exception(e.getCause());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}