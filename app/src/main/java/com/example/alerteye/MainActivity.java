package com.example.alerteye;

import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import org.w3c.dom.Text;
import org.web3j.crypto.Credentials;

import java.net.CacheRequest;

public class MainActivity extends AppCompatActivity {

    public static String url;
    public static String host_account = "0x7F78ce7ef9eEC832f3c1d92C56a1e580ceF4f6D1";
    public static String resiver_account;

    public static Credentials user_credentials;
    public static Credentials host_credentials = Credentials.create("0xad5cdfbb1265b39b71b3bc9ada055c4f3c08946c821485a760c5cddd3d0481bc");

    public static String username;

    BlockchainConnector blockchainConnector = new BlockchainConnector();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}