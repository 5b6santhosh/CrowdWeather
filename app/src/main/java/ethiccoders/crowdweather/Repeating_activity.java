package ethiccoders.crowdweather;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

/**
 * Created by Santhosh on 5/26/2016.
 */
public class Repeating_activity extends AppCompatActivity {



    TextView mTextFieldCondition;
    Firebase mRef;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.repeating_activity_layout);


        android.app.AlertDialog.Builder myalert =new android.app.AlertDialog.Builder(this);
        myalert.setMessage("you are a winner")
                .setPositiveButton("continue..", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setTitle("wellcome")
                .setIcon(R.drawable.alert)
                .create();
        myalert.show();
    }


    @Override
    protected void onStart() {
        super.onStart();
        mTextFieldCondition = (TextView) findViewById(R.id.textViewCondition);

        mRef = new Firebase("https://crowd-weathers.firebaseio.com/condition");
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String text = dataSnapshot.getValue(String.class);
                mTextFieldCondition.setText(text);

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }

        });

    }
}