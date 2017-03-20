package bugbusterzcorp.wildtechquizz;

/**
 * Created by apprenti on 17/03/17.
 */

public class UserClass {

    private String mEmail;
    private String mUsername;


    private UserClass(){

    }

    public UserClass(String email, String username){
        mEmail = email;
        mUsername = username;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String mUsername) {
        this.mUsername = mUsername;
    }


}
