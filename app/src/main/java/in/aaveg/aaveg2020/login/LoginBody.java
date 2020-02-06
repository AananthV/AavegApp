package in.aaveg.aaveg2020.login;

import com.google.gson.annotations.SerializedName;

public class LoginBody {
    @SerializedName("message")
    String message;
    @SerializedName("user_id")
    String user_id;
    @SerializedName("APIToken")
    String APIToken;
    @SerializedName("hostel_chosen")
    Boolean hostel_chosen;

    @SerializedName("hostel")
    String hostel;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getAPIToken() {
        return APIToken;
    }

    public void setAPIToken(String APIToken) {
        this.APIToken = APIToken;
    }

    public Boolean getHostel_chosen() {
        return hostel_chosen;
    }

    public void setHostel_chosen(Boolean hostel_chosen) {
        this.hostel_chosen = hostel_chosen;
    }

    public String getHostel() {
        return hostel;
    }

    public void setHostel(String hostel) {
        this.hostel = hostel;
    }

    public LoginBody(String message, String user_id, String APIToken, Boolean hostel_chosen, String hostel) {
        this.message = message;
        this.user_id = user_id;
        this.APIToken = APIToken;
        this.hostel_chosen = hostel_chosen;
        this.hostel = hostel;
    }
}
