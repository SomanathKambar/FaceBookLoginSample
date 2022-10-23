package navigation;

import android.app.Activity;
import android.content.Intent;

import com.example.facebookloginsample.ui.login.LoginActivity;

public class ActivityRouter {
    public static int REQUEST_CODE_LOGIN = 10;
    public void goToLoginActivity(Activity activity) {
        Intent intent = new Intent(activity, LoginActivity.class);
        activity.startActivityForResult(intent, REQUEST_CODE_LOGIN);
    }
}
