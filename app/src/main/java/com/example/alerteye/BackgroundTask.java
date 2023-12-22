package com.example.alerteye;

import android.os.AsyncTask;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;

import java.lang.reflect.Array;
import java.math.BigDecimal;

public class BackgroundTask extends AsyncTask<Integer,String,String>{

    private Web3j web3j;
    String url;
    String resiver_account;
    Credentials credentials;

    public BackgroundTask(Credentials sender_credentials,String account){
        url = MainActivity.url;
        resiver_account = account;
        credentials = sender_credentials;
    }

    @Override
    protected String doInBackground(Integer... integers) {
        web3j = Web3j.build(new HttpService(url));
        try {
            TransactionReceipt transactionReceipt = org.web3j.tx.Transfer.sendFunds(
                    web3j, credentials, resiver_account,
                    BigDecimal.valueOf(integers[1]), Convert.Unit.ETHER).send();
            return "Success";

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



}


