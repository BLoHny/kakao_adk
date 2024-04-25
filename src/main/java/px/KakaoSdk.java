package px;

import com.fasterxml.jackson.databind.ObjectMapper;
import config.PrivateConfig;
import dto.KakaoTokenResponse;
import dto.KakaoUserInfoResponse;

import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class KakaoSdk {

    public KakaoUserInfoResponse getUserInfo(PrivateConfig config, String code) throws Exception {
        GetKakaoToken getKakaoToken = new GetKakaoToken();
        KakaoTokenResponse tokenResponse = getKakaoToken.execute(config, code);

        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://kapi.kakao.com/v2/user/me"))
                .header("Authorization", "Bearer " + tokenResponse.getAccess_token())
                .build();
        try {
            HttpResponse<InputStream> response = httpClient.send(request, HttpResponse.BodyHandlers.ofInputStream());

            InputStream responseBodyStream = response.body();
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(responseBodyStream, KakaoUserInfoResponse.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getCause());
        }
    }
}
