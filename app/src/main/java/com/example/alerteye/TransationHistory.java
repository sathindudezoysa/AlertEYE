package com.example.alerteye;

import android.os.AsyncTask;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.core.methods.response.Transaction;
import org.web3j.protocol.http.HttpService;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class TransationHistory extends AsyncTask<String,String, List> {

    private Web3j web3j;

    @Override
    protected List doInBackground(String... strings) {
        web3j = Web3j.build(new HttpService(MainActivity.url));

        List<String[]> all_trasactions = new ArrayList<>();

        try {
            BigInteger latest_block = null;
            latest_block = web3j.ethBlockNumber().send().getBlockNumber();
//            for (BigInteger blockNumber = BigInteger.ONE; blockNumber.compareTo(latest_block) <= 0; blockNumber = blockNumber.add(BigInteger.ONE)){
//                EthBlock.Block block = web3j.ethGetBlockByNumber(DefaultBlockParameter.valueOf(blockNumber), true).send().getBlock();
//
//                List<EthBlock.TransactionResult> transactions = block.getTransactions();
//
//                for (EthBlock.TransactionResult transactionResult : transactions){
//                    EthBlock.TransactionObject transactionObject = (EthBlock.TransactionObject) transactionResult.get();
//                    String transactionHash = transactionObject.getHash();
//                    Transaction transaction = web3j.ethGetTransactionByHash(transactionHash).send().getTransaction().orElse(null);
//
//                    String[] tem_block = {transaction.getFrom(), transaction.getTo(), transaction.getValue().toString(), block.getTimestamp().toString()};
//                    all_trasactions.add(tem_block);
//                }
//
//            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return all_trasactions;
    }
}
