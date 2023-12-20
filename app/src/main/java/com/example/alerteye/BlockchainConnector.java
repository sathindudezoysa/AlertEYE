package com.example.alerteye;
import org.web3j.abi.datatypes.primitive.Int;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;

import java.math.BigInteger;
import java.sql.Struct;

public class BlockchainConnector {

    private Web3j web3j;


    public BlockchainConnector(){
    }

    //Setting up conncetion with the blockchain
    public String connect(String url) throws CustomException{
        web3j = Web3j.build(new HttpService(url));
        try {
            //if the client version has an error the user will not gain access if successful the user will get connnected
            Web3ClientVersion clientVersion = web3j.web3ClientVersion().sendAsync().get();
            if (!clientVersion.hasError()) {
                return "Conncted to " + clientVersion;
            } else {
                throw new CustomException(clientVersion.getError().getMessage());
            }
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    //Getting the account balance of the user
    public BigInteger getBalance(String accountAddress) throws CustomException {
        try {
            EthGetBalance balance = web3j.ethGetBalance(accountAddress, DefaultBlockParameterName.LATEST).sendAsync().get();
            return balance.getBalance();
        }catch (Exception e){
            throw new CustomException(e.getMessage());
        }

    }

    //Sending Reward to the user
    public void sendReward(Integer amount){

        new BackgroundTask().execute(amount);

    }
}


