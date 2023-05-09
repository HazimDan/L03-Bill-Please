package sg.edu.rp.c346.id22012867.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    TextView amount;
    EditText editAmount;
    TextView pax;
    EditText editPax;
    ToggleButton SVS;
    ToggleButton GST;
    TextView discount;
    EditText editDiscount;
    RadioGroup rgPayment;
    TextView number;
    EditText editNumber;
    Button split;
    Button reset;
    TextView total;
    TextView editTotal;
    TextView eachPays;
    TextView editEachPays;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amount = findViewById(R.id.amount);
        editAmount = findViewById(R.id.editAmount);
        pax = findViewById(R.id.pax);
        editPax = findViewById(R.id.editPax);
        SVS = findViewById(R.id.SVS);
        GST = findViewById(R.id.GST);
        discount = findViewById(R.id.discount);
        editDiscount = findViewById(R.id.editDiscount);
        rgPayment = findViewById(R.id.rgPayment);
        number = findViewById(R.id.number);
        editNumber = findViewById(R.id.editNumber);
        split = findViewById(R.id.split);
        reset = findViewById(R.id.reset);
        total = findViewById(R.id.total);
        editTotal = findViewById(R.id.editTotal);
        eachPays = findViewById(R.id.eachPays);
        editEachPays = findViewById(R.id.editEachPays);


        reset.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                editAmount.setText("");
                editPax.setText("");
                SVS.setChecked(false);
                GST.setChecked(false);
                editDiscount.setText("");
            }
        });
        split.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Double GSTC=1.08;
                Double SVSC=1.1;
                if(editAmount.getText().toString().trim().length()!=0 && editPax.getText().toString().trim().length()!=0){
                    double NAMT = 0.0;
                    if (SVS.isChecked() && GST.isChecked()){
                        NAMT = Double.parseDouble(editAmount.getText().toString()) * SVSC*GSTC;                    }
                    else if(!SVS.isChecked()&&  GST.isChecked()){
                        NAMT = Double.parseDouble(editAmount.getText().toString())* GSTC;                    }
                    else if(SVS.isChecked() && !GST.isChecked()){
                        NAMT = Double.parseDouble(editAmount.getText().toString())* SVSC;                    }
                    else{
                        NAMT = Double.parseDouble(editAmount.getText().toString());                    }
                    if(editDiscount.getText().toString().trim().length() != 0){
                        NAMT *= 1 - Double.parseDouble(editDiscount.getText().toString())/100;                    }
                    editTotal.setText("$" + String.format("%.2f", NAMT));
                    int NOP =Integer.parseInt(editPax.getText().toString());
                    int RPayment = rgPayment.getCheckedRadioButtonId();
                    if (NOP!=1 && RPayment == R.id.payNow) {
                        editEachPays.setText("$" + String.format("%.2f", NAMT / NOP)+ " via PayNow to " + editNumber.getText().toString()) ;                    }
                    else if(NOP==1 && RPayment == R.id.payNow) {
                        editEachPays.setText("$" + String.format("%.2f",NAMT) + " via PayNow to " + editNumber.getText().toString());                    }
                    else if(NOP!=1 && RPayment == R.id.cash){
                        editEachPays.setText("$" + String.format("%.2f", NAMT / NOP)+ " in Cash") ;                    }
                    else{
                        editEachPays.setText("$" + String.format("%.2f",NAMT) + " in Cash");                    }

                }else if(editAmount.getText().toString().trim().length()==0 && editPax.getText().toString().trim().length()!=0){


                    editAmount.setError("Please key in an amount!");
                }else if(editAmount.getText().toString().trim().length()!=0 && editPax.getText().toString().trim().length()==0){
                    editPax.setError("Please key in No Of Pax!");                }
                else{
                    editAmount.setError("Amount is required!");
                    editPax.setError("Number of pax is required!");
                }            }
        });

    }}