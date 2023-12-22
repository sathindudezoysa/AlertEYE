package com.example.alerteye;
import org.web3j.abi.datatypes.primitive.Int;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.*;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;

import java.io.IOException;
import java.math.BigInteger;
import java.sql.Struct;
import java.util.ArrayList;
import java.util.List;
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

    // getiing user account address
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

    //getting the transation history
    public String getTransationHistory(String account_address) throws ExecutionException, InterruptedException, IOException {
        web3j = Web3j.build(new HttpService(MainActivity.url));

        List<EthBlock.TransactionResult> txs = web3j.ethGetBlockByNumber(DefaultBlockParameterName.LATEST, true).send().getBlock().getTransactions();
        txs.forEach(tx -> {
            EthBlock.TransactionObject transaction = (EthBlock.TransactionObject) tx.get();


            System.out.println(transaction.getFrom());
        });

//        String x = web3j.ethBlockNumber().sendAsync().get().getBlockNumber().toString();
        return null;
    }
}


