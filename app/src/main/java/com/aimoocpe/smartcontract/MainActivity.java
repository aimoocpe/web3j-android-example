package com.aimoocpe.smartcontract;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.aimoocpe.contracts.TutorialToken;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.Web3jFactory;
import org.web3j.protocol.http.HttpService;

import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    Button btnClick;
    TextView tvAddress;
    TextView tvBalance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvAddress = findViewById(R.id.tvAddress);
        tvBalance = findViewById(R.id.tvBalance);

        btnClick = findViewById(R.id.btnClick);
        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Web3j web3 = Web3jFactory.build(new HttpService("http://10.0.2.2:8545"));
                try {
                    // create credential by secret key (Owner)
                    Credentials credential = Credentials.create("02d78849d22f79922a1e8b9e8767ac9ced43307862463415d6409f2c30a21a2b");
                    // get address owner
                    String owner = web3.ethAccounts().sendAsync().get().getAccounts().get(0);
                    // load contract by contract address
                    TutorialToken tutorial = TutorialToken.load("0xc8b5f7d1e1af6f0270233b7b403a961d5cdfc62d",web3,credential,new BigInteger("100000"),new BigInteger("400000"));
                    // get function balanceOf from contract
                    BigInteger result = tutorial.balanceOf(owner).sendAsync().get();
                    tvAddress.setText("Address : "+owner);
                    tvBalance.setText("Balance : "+result.intValue());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
