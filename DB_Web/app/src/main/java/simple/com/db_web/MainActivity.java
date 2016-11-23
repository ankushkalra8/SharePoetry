package simple.com.db_web;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener {
    EditText et1;
    EditText et2;
    Button bt;
    public static final String STUDENT_ID = "username";
    String student_id;
    String passcode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        et1=(EditText)findViewById(R.id.editTextUserName);
        et2=(EditText)findViewById(R.id.editTextPassword);
        bt=(Button)findViewById(R.id.button);
        bt.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        student_id = et1.getText().toString();
        Toast.makeText(getApplicationContext(), "id  " + student_id, Toast.LENGTH_LONG).show();
        passcode = et2.getText().toString();
        Toast.makeText(getApplicationContext(), "pass    "+passcode, Toast.LENGTH_LONG).show();
        login(student_id, passcode);
    }

    private void login(final String student_id, String passcode) {

        class LoginAsync extends AsyncTask<String,Void,String> {

            private Dialog loadingDialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loadingDialog = ProgressDialog.show(MainActivity.this, "Please wait", "Loading...");
            }

            @Override
            protected String doInBackground(String... params) {
                String id = params[0];
                String pass = params[1];

                InputStream is = null;
                List<NameValuePair> nameValuePairs = new ArrayList<>();
                nameValuePairs.add(new BasicNameValuePair("student_id", id));
                nameValuePairs.add(new BasicNameValuePair("password", pass));
                System.out.println(id);
                System.out.println(pass);
                String result = null;

                try{
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "http://10.0.2.2/Db/db.php");
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                    HttpResponse response = httpClient.execute(httpPost);

                    HttpEntity entity = response.getEntity();

                    is = entity.getContent();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"), 8);
                    StringBuilder sb = new StringBuilder();

                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    result = sb.toString();
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return result;
            }

            @Override
            protected void onPostExecute(String result){
                String s = result.trim();
                System.out.println("Printing s\n" + s + "\n s printed");
                loadingDialog.dismiss();
                Toast.makeText(getApplicationContext(), "check "+result, Toast.LENGTH_LONG).show();
                if(s.equalsIgnoreCase("success")){
                    Intent intent = new Intent(MainActivity.this, Second.class);
                    intent.putExtra(STUDENT_ID, student_id);
                    finish();
                    startActivity(intent);
                }else {
                    Toast.makeText(getApplicationContext(), "Invalid User Name or Password", Toast.LENGTH_LONG).show();
                }
            }
        }

        LoginAsync la = new LoginAsync();
        la.execute(student_id, passcode);
    }

    public void invokeLogin(View view) {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}
