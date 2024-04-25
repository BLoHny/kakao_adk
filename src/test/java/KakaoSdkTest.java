import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import config.PrivateConfig;
import dto.KakaoUserInfoResponse;
import org.junit.jupiter.api.Test;
import px.KakaoSdk;

import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class KakaoSdkTest {

    @Test
    public void test() throws Exception {
        KakaoSdk kakaoSdk = new KakaoSdk();
        PrivateConfig privateConfig = new PrivateConfig("", "http://voza-client-v1.vercel.app");
        var response = kakaoSdk.getUserInfo(privateConfig, "_GMYTcYp1wcdLRNUwSitStJSmiMOe1gEzf7f6AiqRA5diUFl98TuFpxNyBwKKcjZAAABjxL0mI0hI_W2iNNaeg");
        System.out.println(response);
    }

    @Test
    void hi() throws Exception {
        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://kapi.kakao.com/v2/user/me"))
                .header("Authorization", "Bearer " + "2or5lC0wt83VUATWE7mlkvXNWYyuX1fMUOcKKiUNAAABjxL3QMDkNSpXBP-m7Q")
                .build();
        try {
            HttpResponse<InputStream> response = httpClient.send(request, HttpResponse.BodyHandlers.ofInputStream());

            InputStream responseBodyStream = response.body();
            ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            KakaoUserInfoResponse info = objectMapper.readValue(responseBodyStream, KakaoUserInfoResponse.class);
            System.out.println(info.getKakao_account().getEmail());
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getCause());
        }
    }
}
