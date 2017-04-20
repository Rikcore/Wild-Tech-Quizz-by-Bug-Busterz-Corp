package bugbusterzcorp.wildtechquizz;

/**
 * Created by apprenti on 10/04/17.
 */

public class ScoreCommentUserClass {
    private String mMessage;
    private String mUserNickName;
    private String mUserID;
    private int mScore;

    private ScoreCommentUserClass(){}

    public ScoreCommentUserClass(String message, String userNickName, String userID, int score){
        mMessage= message;
        mUserNickName = userNickName;
        mUserID = userID;
        mScore = score;

    }

    public int getmScore() {
        return mScore;
    }

    public void setmScore(int mScore) {
        this.mScore = mScore;
    }

    public String getmMessage() {
        return mMessage;
    }

    public void setmMessage(String mMessage) {
        this.mMessage = mMessage;
    }

    public String getmUserNickName() {
        return mUserNickName;
    }

    public void setmUserNickName(String mUserNickName) {
        this.mUserNickName = mUserNickName;
    }

    public String getmUserID() {
        return mUserID;
    }

    public void setmUserID(String mUserID) {
        this.mUserID = mUserID;
    }

}
