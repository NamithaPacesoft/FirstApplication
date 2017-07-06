package com.example.user.myapplication.exampleprojects.socialmedia.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.myapplication.R;
import com.example.user.myapplication.constants.ConstantDetails;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.twitter.sdk.android.core.BuildConfig;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import com.twitter.sdk.android.core.internal.TwitterApi;
import com.twitter.sdk.android.core.internal.TwitterSessionVerifier;
import com.twitter.sdk.android.tweetcomposer.ComposerActivity;
import com.twitter.sdk.android.tweetcomposer.TweetComposer;
import com.twitter.sdk.android.tweetcomposer.TweetUploadService;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

/**
 * Created by User on 2017-06-29.
 */

public class FacebookActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{

    private LoginButton btnLogin;
    private TextView tvStatus;
    private CallbackManager callbackManager;
    private ProfileTracker profileTracker;

    private SignInButton btnGoogleSignIn;
    private Button btnGoogleSignOut;
    private TwitterLoginButton btnTwitterLogin;

    private GoogleApiClient mGoogleApiClient;
    private boolean isTwitterClicked=false;

  //  MyResultReceiver resultReceiver;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_facebook_button);
        initializeMyComponents();
        addActionListener();

        /*
        resultReceiver= new MyResultReceiver();
        IntentFilter intentFilterSuccess = new IntentFilter(TweetUploadService.UPLOAD_SUCCESS);
        IntentFilter intentFilterFailure = new IntentFilter(TweetUploadService.UPLOAD_FAILURE);

        registerReceiver(resultReceiver,intentFilterSuccess);
        registerReceiver(resultReceiver,intentFilterFailure);
        */

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

       // unregisterReceiver(resultReceiver);
    }

    private void initializeMyComponents(){
        callbackManager = CallbackManager.Factory.create();

        tvStatus =(TextView) findViewById(R.id.tv_delivery_status);
        btnLogin= (LoginButton) findViewById(R.id.btn_login);
        btnLogin.setReadPermissions("public_profile","email","user_birthday");

        /**
         * Google related variables
         */
        btnGoogleSignIn = (SignInButton) findViewById(R.id.btn_google_login);
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso= new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();

        // Build a GoogleApiClient with access to the Google Sign-In API and the
        // options specified by gso.
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        btnGoogleSignOut=(Button) findViewById(R.id.btn_google_logout);
        updateUI(false);


        /**
         * Twitter related variables
         */

        btnTwitterLogin = (TwitterLoginButton) findViewById(R.id.btn_twitter_login);

    }

    private void addActionListener(){

        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile profile, Profile profile1) {

            }
        };


        btnLogin.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest request=GraphRequest.newMeRequest(loginResult.getAccessToken(),new GraphRequest.GraphJSONObjectCallback() {

                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {

                        try{

                            tvStatus.setText(object.getString("name") + "\n\n" + object.getString("gender")  +"\n\n" + object.getString("birthday")  );
                        }catch(JSONException ex){
                            tvStatus.setText("Unable to retrieve the details");
                        }

                    }
                });

                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender,birthday");
                request.setParameters(parameters);
                request.executeAsync();

            }

            @Override
            public void onCancel() {
                tvStatus.setText("Login Cancelled");
            }

            @Override
            public void onError(FacebookException e) {
                tvStatus.setText(e.getMessage());
            }
        });


        /**
         * Google related login
         */
        btnGoogleSignIn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                signIn();
            }
        });


        btnGoogleSignOut.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                signOut();
            }
        });

        try{
            btnTwitterLogin.setCallback(new Callback<TwitterSession>() {

                @Override
                public void success(Result<TwitterSession> result) {
                    TwitterSession session = TwitterCore.getInstance().getSessionManager().getActiveSession();
                    TwitterAuthToken authToken = session.getAuthToken();
                    String token = authToken.token;
                    String secret = authToken.secret;

                    tvStatus.setText(result.data.getUserName());
                    TwitterAuthClient authClient = new TwitterAuthClient();
                 //   Uri imageUri = FileProvider.getUriForFile(FacebookActivity.this,
                          //  BuildConfig.APPLICATION_ID + ".file_provider",
                          //  new File("/path/to/image"));
                    final Intent intent = new ComposerActivity.Builder(FacebookActivity.this)
                            .session(session)
                            .text("Love where you work")
                            .hashtags("#twitter")
                            .createIntent();
                    startActivity(intent);

                    /*
                    authClient.requestEmail(session, new Callback<String>() {
                        @Override
                        public void success(Result<String> result) {
                            // Do something with the result, which provides the email address
                           // tvStatus.setText(result.response.message());


                        }

                        @Override
                        public void failure(TwitterException exception) {
                            // Do something on failure
                            tvStatus.setText(exception.getMessage());
                        }
                    });
*/
                }

                @Override
                public void failure(TwitterException exception) {
                    tvStatus.setText(exception.getMessage());
                }
            });

        }catch (Exception ex){
            tvStatus.setText(ex.getMessage());
        }
        /**
         * Twitter related login
         */




    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode== ConstantDetails.REQ_CODE_GOOGLE){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }else{
            callbackManager.onActivityResult(requestCode, resultCode, data);
            btnTwitterLogin.onActivityResult(requestCode,resultCode,data);
        }

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.

        Toast.makeText(this,"Not Able to connect",Toast.LENGTH_LONG).show();

    }

    private void handleSignInResult(GoogleSignInResult result){

        if(result.isSuccess()){
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount account = result.getSignInAccount();
            tvStatus.setText( "Name : " + account.getDisplayName() + "\n\n" + "email : " + account.getEmail());
            updateUI(true);
        }else {
            updateUI(false);
        }
    }

    public void signIn(){

        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, ConstantDetails.REQ_CODE_GOOGLE);
    }

    public void signOut(){

        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                // Write the code to clear the text view
                updateUI(false);
            }
        });
    }

    public void revokeAccess(){

    }

    private void updateUI(boolean isGoogleSingedIn){
        if (isGoogleSingedIn){
            btnGoogleSignOut.setVisibility(View.VISIBLE);
            btnGoogleSignIn.setVisibility(View.GONE);
        }else{
            tvStatus.setText("");
            btnGoogleSignOut.setVisibility(View.GONE);
            btnGoogleSignIn.setVisibility(View.VISIBLE);
        }
    }



    /*
    public class MyResultReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle intentExtras = intent.getExtras();
            if (TweetUploadService.UPLOAD_SUCCESS.equals(intent.getAction())) {
                Log.i(getClass().getSimpleName(), "onReceive: success" );
                Toast.makeText(FacebookActivity.this,"Success",Toast.LENGTH_LONG).show();
                // success
               // final Long tweetId = intentExtras.getLong(TweetUploadService.EXTRA_TWEET_ID);
            } else {
                Log.i(getClass().getSimpleName(), "onReceive: failure" );
                Toast.makeText(FacebookActivity.this,"Failure",Toast.LENGTH_LONG).show();
                // failure
                //final Intent retryIntent = intentExtras.getParcelable(TweetUploadService.EXTRA_RETRY_INTENT);
            }
        }
    }
*/
}
