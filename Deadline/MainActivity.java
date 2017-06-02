package app.dealine.andreas.deadline;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;

import com.facebook.share.Sharer;

import com.facebook.share.model.ShareLinkContent;

import com.facebook.share.widget.ShareDialog;


import java.util.Arrays;




public class MainActivity extends AppCompatActivity {

    private static final String TAG = "ShareOnFacebook";
    private TextView info;
    private TextView infoTwo;
    private LoginButton loginButton;
    private CallbackManager callbackManager;
    ProfilePictureView profilePictureView;
    private static final String TOKEN = "access_token";
    private static final String EXPIRES = "expires_in";
    private static final String KEY = "facebook-credentials";

    boolean loggedIn;





    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        getFriends();


        if (isLoggedIn()) {
            LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "user_friends"));
        }

        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        setContentView(R.layout.activity_main);
        info = (TextView) findViewById(R.id.info);
        infoTwo = (TextView) findViewById(R.id.textView3);
        loginButton = (LoginButton) findViewById(R.id.login_button);
        Button button = (Button) findViewById(R.id.button4);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                share();
                // do the "on click" action here
            }
        });

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {


            @Override
            public void onSuccess(LoginResult loginResult) {
                loggedIn = true;


                info.setText(
                        "Hi "
                                + Profile.getCurrentProfile().getFirstName() + "!"
                                + "\n"

                );
                profilePictureView = (ProfilePictureView) findViewById(R.id.friendProfilePicture);

                profilePictureView.setProfileId(loginResult.getAccessToken().getUserId());


            }


            @Override
            public void onCancel() {
                info.setText("Login attempt canceled.");
            }

            @Override
            public void onError(FacebookException e) {
                info.setText("Login attempt failed.");
            }
        });

    }

    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    void shareOnWall() {
        ShareDialog shareDialog = new ShareDialog(this);
        callbackManager = CallbackManager.Factory.create();
        shareDialog.registerCallback(callbackManager, new

                FacebookCallback<Sharer.Result>() {
                    @Override
                    public void onSuccess(Sharer.Result result) {
                        Log.d(TAG, "onSuccess: ");
                        Toast.makeText(MainActivity.this, "onSuccess", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancel() {
                        Log.d(TAG, "onCancel: ");
                        Toast.makeText(MainActivity.this, "onCancel", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(FacebookException error) {
                        Log.d(TAG, "onError: ");
                        Toast.makeText(MainActivity.this, "onError" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });

        if (ShareDialog.canShow(ShareLinkContent.class)) {
            ShareLinkContent linkContent = new ShareLinkContent.Builder()
                    .setContentTitle("Hello Facebook")
                    .setContentDescription("Deadline")
                    .setContentUrl(Uri.parse("http://facebook.com"))
                    .setQuote("Deadline objective: " )
                    .build();

            shareDialog.show(linkContent);
        }
    }
    public boolean isLoggedIn() {
        loggedIn = true;
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        return accessToken != null;
    }
    public void share(){

        shareOnWall();
    }

    public void getFriends(){
        new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/{user-id}/friends",
                null,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
            infoTwo.setText(Profile.getCurrentProfile().getFirstName());
                    }
                }
        ).executeAsync();
    }



}






