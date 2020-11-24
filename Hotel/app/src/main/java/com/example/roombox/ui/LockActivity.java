package com.example.roombox.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.example.roombox.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;



import java.io.InputStream;

public class LockActivity extends Activity implements View.OnClickListener {
  public final static String PREF_IP = "192.168.43.133";
  public final static String PREF_PORT = "80";
  // declare buttons and text inputs
  private Button buttonPin11,buttonPin12,buttonPin13,buttonPin14,buttonPin15,buttonPin16;
  private EditText editTextIPAddress, editTextPortNumber;
  // shared preferences objects used to save the IP address and port so that the user doesn't have to
  // type them next time he/she opens the app.
  SharedPreferences.Editor editor;
  SharedPreferences sharedPreferences;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_lock);

    sharedPreferences = getSharedPreferences("HTTP_HELPER_PREFS",Context.MODE_PRIVATE);
    editor = sharedPreferences.edit();

    // assign buttons
    buttonPin11 = (Button)findViewById(R.id.buttonPin11);
    buttonPin12 = (Button)findViewById(R.id.buttonPin12);
    buttonPin13 = (Button)findViewById(R.id.buttonPin13);
    buttonPin14 = (Button)findViewById(R.id.buttonPin14);
    buttonPin15 = (Button)findViewById(R.id.buttonPin15);
    buttonPin16 = (Button)findViewById(R.id.buttonPin16);

    // assign text inputs
    editTextIPAddress = (EditText)findViewById(R.id.editTextIPAddress);


    // set button listener (this class)
    buttonPin11.setOnClickListener(this);
    buttonPin12.setOnClickListener(this);
    buttonPin13.setOnClickListener(this);
    buttonPin14.setOnClickListener(this);
    buttonPin15.setOnClickListener(this);
    buttonPin16.setOnClickListener(this);

    // get the IP address and port number from the last time the user used the app,
    // put an empty string "" is this is the first time.

  }


  @Override
  public void onClick(View view) {

    // get the pin number
    String parameterValue = "";
    // get the ip address
    String ipAddress = "192.168.43.133";
    // get the port number
    String portNumber = "80";


    // save the IP address and port for the next time the app is used
    editor.putString(PREF_IP,ipAddress); // set the ip address value to save
    editor.putString(PREF_PORT,portNumber); // set the port number to save
    editor.commit(); // save the IP and PORT

    // get the pin number from the button that was clicked
    if(view.getId()==buttonPin11.getId())
    {

      parameterValue = editTextIPAddress.getText().toString().trim(); //整合在我的-房東-房屋管理-設定密碼
    }
    else if(view.getId()==buttonPin12.getId())
    {
      parameterValue = "random"; //整合在我的-一般用戶-訪客密碼
    }
    else if(view.getId()==buttonPin14.getId())
    {
      parameterValue = "open";   //整合在我的-一般用戶-開門
    }
    else if(view.getId()==buttonPin15.getId())
    {
      parameterValue = "clos";  //整合在我的-一般用戶-關門
    }
    else if(view.getId()==buttonPin16.getId())
    {
      parameterValue = "gues";//不需要
    }
    else
    {
      parameterValue = "show";//不需要
    }



    // execute HTTP request
    if(ipAddress.length()>0 && portNumber.length()>0&&editTextIPAddress.getText().toString().trim().length()>0) {
      new HttpRequestAsyncTask(
        view.getContext(), parameterValue, ipAddress, portNumber, "pin"
      ).execute();
    }
  }

  /**
   * Description: Send an HTTP Get request to a specified ip address and port.
   * Also send a parameter "parameterName" with the value of "parameterValue".
   * @param parameterValue the pin number to toggle
   * @param ipAddress the ip address to send the request to
   * @param portNumber the port number of the ip address
   * @param parameterName
   * @return The ip address' reply text, or an ERROR message is it fails to receive one
   */
  public String sendRequest(String parameterValue, String ipAddress, String portNumber, String parameterName) {
    String serverResponse = "ERROR";

    try {
      URL website = new URL("http://"+ipAddress+":"+portNumber+"/?"+parameterName+"="+parameterValue);
      HttpURLConnection connection = (HttpURLConnection) website
        .openConnection(); // create an HTTP client
      // define the URL e.g. http://myIpaddress:myport/?pin=13 (to toggle pin 13 for example)

      connection.setDoOutput(false);
      // 设置是否从httpUrlConnection读入
      connection.setDoInput(true);
      // 设置请求方式
      connection.setRequestMethod("GET");
      // 设置是否使用缓存
      connection.setUseCaches(true);
      // 设置此 HttpURLConnection 实例是否应该自动执行 HTTP 重定向
      connection.setInstanceFollowRedirects(true);
      // 设置超时时间
      connection.setConnectTimeout(3000);
      // 连接
      connection.connect();









      //HttpGet getRequest = new HttpGet(); // create an HTTP GET object
      //  getRequest.setURI(website); // set the URL of the GET request
      // HttpResponse response = httpclient.execute(getRequest); // execute the request
      // get the ip address server's reply
      InputStream content = null;
      content = connection.getInputStream();
      BufferedReader in = new BufferedReader(new InputStreamReader(
        content
      ));
      serverResponse = in.readLine();
      // Close the connection
      content.close();

      connection.disconnect();
    } catch (MalformedURLException e) {
      // HTTP error
      serverResponse = e.getMessage();
      e.printStackTrace();
    } catch (IOException e) {
      // IO error
      serverResponse = e.getMessage();
      e.printStackTrace();
    }/* catch (URISyntaxException e) {
            // URL syntax error
            serverResponse = e.getMessage();
            e.printStackTrace();
        }*/
    // return the server's reply/response text
    return serverResponse;
  }


  /**
   * An AsyncTask is needed to execute HTTP requests in the background so that they do not
   * block the user interface.
   */
  private class HttpRequestAsyncTask extends AsyncTask<Void, Void, Void> {

    // declare variables needed
    private String requestReply,ipAddress, portNumber;
    private Context context;
    private AlertDialog alertDialog;
    private String parameter;
    private String parameterValue;

    /**
     * Description: The asyncTask class constructor. Assigns the values used in its other methods.
     * @param context the application context, needed to create the dialog
     * @param parameterValue the pin number to toggle
     * @param ipAddress the ip address to send the request to
     * @param portNumber the port number of the ip address
     */
    public HttpRequestAsyncTask(Context context, String parameterValue, String ipAddress, String portNumber, String parameter)
    {
      this.context = context;

      alertDialog = new AlertDialog.Builder(this.context)
        .setTitle("HTTP Response From IP Address:")
        .setCancelable(false)
        .create();

      this.ipAddress = ipAddress;
      this.parameterValue = parameterValue;
      this.portNumber = portNumber;
      this.parameter = parameter;
    }

    /**
     * Name: doInBackground
     * Description: Sends the request to the ip address
     * @param voids
     * @return
     */
    @Override
    protected Void doInBackground(Void... voids) {
      alertDialog.setMessage("Data sent, waiting for reply from server...");
      if(!alertDialog.isShowing())
      {
        alertDialog.show();
      }
      requestReply = sendRequest(parameterValue,ipAddress,portNumber, parameter);
      return null;
    }

    /**
     * Name: onPostExecute
     * Description: This function is executed after the HTTP request returns from the ip address.
     * The function sets the dialog's message with the reply text from the server and display the dialog
     * if it's not displayed already (in case it was closed by accident);
     * @param aVoid void parameter
     */
    @Override
    protected void onPostExecute(Void aVoid) {
      alertDialog.setCancelable(true);
      alertDialog.setMessage(requestReply);
      if(!alertDialog.isShowing())
      {
        alertDialog.show(); // show dialog


      }
      try {
        Thread.sleep(1800);
      } catch (InterruptedException e) {

        e.printStackTrace();
      }
    }

    /**
     * Name: onPreExecute
     * Description: This function is executed before the HTTP request is sent to ip address.
     * The function will set the dialog's message and display the dialog.
     */
    @Override
    protected void onPreExecute() {

      alertDialog.setMessage("Sending data to server, please wait...");
      if(!alertDialog.isShowing())
      {
        alertDialog.show();
      }
    }

  }
}
