package com.example.alerteye;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.navigation.Navigation;
import com.kenai.jffi.Main;
import org.web3j.crypto.Credentials;

public class Login extends Fragment {




    public Login() {
        // Required empty public constructor
    }

    BlockchainConnector blockchainConnector = new BlockchainConnector();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login, container, false);
        EditText text_url = view.findViewById(R.id.text_url);
        EditText text_name = view.findViewById(R.id.text_name);
        EditText text_private_key = view.findViewById(R.id.text_private);
        TextView error_message = view.findViewById(R.id.error);

        Button button = view.findViewById(R.id.btn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                error_message.setText(text_url.getText());
                try {
//                    MainActivity.url = "http://" + text_url.getText().toString();
//                    MainActivity.user_credentials = Credentials.create(text_private_key.getText().toString());
//                    MainActivity.username = text_name.getText().toString();

                    MainActivity.url = "http://192.168.1.11:7545";
                    MainActivity.user_credentials = Credentials.create("0x6b7d9e6e6a0adbec461dbc83be220986f2aeadf592fd6d0ccf715ccb483166fe");
                    MainActivity.user_account_address = blockchainConnector.getAccountAddress(MainActivity.user_credentials);

                    blockchainConnector.connect();
                    Toast toast = Toast.makeText(getActivity(), "Connected", Toast.LENGTH_LONG);
                    toast.show();

                    Navigation.findNavController(view).navigate(R.id.action_login_to_profile);

                }catch (Exception e) {
                    error_message.setText("failed to connect\n" + e.getMessage());

                }

            }
        });


        return view;
    }
}