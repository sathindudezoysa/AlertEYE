package com.example.alerteye;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.math.BigInteger;
import java.text.DecimalFormat;


public class Profile extends Fragment {

    BlockchainConnector blockchainConnector = new BlockchainConnector();

    public Profile() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        TextView balance_text = view.findViewById(R.id.balance_text);

        try {
            double balance = Math.floor(blockchainConnector.getBalance(blockchainConnector.getAccountAddress(MainActivity.user_credentials)) * 100) /100  ;
            balance_text.setText(Double.toString(balance));

        }catch (Exception e){
            Toast toast = Toast.makeText(getActivity(), "Cant connect to your Account", Toast.LENGTH_LONG);
            toast.show();

        }


        // Inflate the layout for this fragment
        return view;
    }
}