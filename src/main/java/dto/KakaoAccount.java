package dto;

public class KakaoAccount {
    private Profile profile;
    private String email;

    public KakaoAccount(Profile profile, String email) {
        this.profile = profile;
        this.email = email;
    }

    public Profile getProfile() {
        return profile;
    }

    public String getEmail() {
        return email;
    }

    public KakaoAccount() {

    }
}
