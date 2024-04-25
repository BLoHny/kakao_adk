package config;

public class PrivateConfig {
    private String CLIENT_ID;
    private String REDIRECT_URI;

    public PrivateConfig(String CLIENT_ID, String REDIRECT_URI) {
        this.CLIENT_ID = CLIENT_ID;
        this.REDIRECT_URI = REDIRECT_URI;
    }

    public String getCLIENT_ID() {
        return CLIENT_ID;
    }

    public void setCLIENT_ID(String CLIENT_ID) {
        this.CLIENT_ID = CLIENT_ID;
    }

    public String getREDIRECT_URI() {
        return REDIRECT_URI;
    }

    public void setREDIRECT_URI(String REDIRECT_URI) {
        this.REDIRECT_URI = REDIRECT_URI;
    }
}
