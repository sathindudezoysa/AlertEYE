package com.example.alerteye;
import org.web3j.abi.datatypes.primitive.Int;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;

import java.math.BigInteger;
import java.sql.Struct;
import java.util.concurrent.ExecutionException;

public class BlockchainConnector {

    private Web3j web3j;


    public BlockchainConnector(){
    }

    //Setting up conncetion with the blockchain
    public void connect() throws ExecutionException, InterruptedException {
        web3j = Web3j.build(new HttpService(MainActivity.url));
        Web3ClientVersion clientVersion = web3j.web3ClientVersion().sendAsync().get();
    }

    public String getAccountAddress(Credentials user_credentials){
        return user_credentials.getAddress().toString();
    }

    //Getting the account balance of the user
    public double getBalance(String accountAddress) throws ExecutionException, InterruptedException {
        web3j = Web3j.build(new HttpService(MainActivity.url));
        EthGetBalance balance = web3j.ethGetBalance(accountAddress, DefaultBlockParameterName.LATEST).sendAsync().get();

        double eth_balance = Convert.fromWei(balance.getBalance().toString(), Convert.Unit.ETHER).doubleValue();

        return eth_balance;
    }

    //Sending Reward to the user
    public void sendReward(Credentials sender_credentials, String resiver_account,Integer amount){
        new BackgroundTask(sender_credentials, resiver_account).execute(amount);

    }
}


