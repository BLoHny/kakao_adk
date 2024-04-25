package dto;

public class KakaoUserInfoResponse {
    private Long id;
    private String connected_at;
    private KakaoAccount kakao_account;

    public KakaoUserInfoResponse(Long id, String connected_at, KakaoAccount kakao_account) {
        this.id = id;
        this.connected_at = connected_at;
        this.kakao_account = kakao_account;
    }

    public Long getId() {
        return id;
    }

    public String getConnected_at() {
        return connected_at;
    }

    public KakaoAccount getKakao_account() {
        return kakao_account;
    }

    public KakaoUserInfoResponse() {

    }
}