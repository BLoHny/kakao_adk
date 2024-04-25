package dto;

public class Profile {
    private String profile_image_url;
    private String nickname;

    public String getProfile_image_url() {
        return profile_image_url;
    }

    public String getNickname() {
        return nickname;
    }

    public Profile(String profile_image_url, String nickname) {
        this.profile_image_url = profile_image_url;
        this.nickname = nickname;
    }

    public Profile() {

    }
}
