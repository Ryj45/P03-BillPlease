package c346.rp.edu.sg.billplease;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    ToggleButton gst;
    ToggleButton serviceCharge;
    EditText amount;
    EditText pax;
    Button cal;
    EditText disc;
    ToggleButton discCheck;
    TextView payDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gst = findViewById(R.id.toggleButtonGST);
        serviceCharge = findViewById(R.id.toggleButtonSC);
        cal = findViewById(R.id.calculate);
        amount = findViewById(R.id.amountInput);
        pax = findViewById(R.id.paxInput);
        disc =findViewById(R.id.discountInput);
        discCheck =findViewById(R.id.toggleButton3);
        payDisplay = findViewById(R.id.payDisplay);

        discCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(discCheck.isChecked())
                {
                    disc.setEnabled(true);
                }
                else{
                    disc.setEnabled(false);
                }
            }
        });

        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double pay;
                double totalAmount = Double.valueOf(amount.getText().toString());
                int peopleNo = Integer.valueOf(pax.getText().toString());

                if(discCheck.isChecked())
                {
                    double discount = Double.valueOf(disc.getText().toString());
                    if(gst.isChecked() && serviceCharge.isChecked())
                    {
                        pay = 1.17 * totalAmount *(1-discount)/peopleNo;
                    }
                    else if(gst.isChecked()== false && serviceCharge.isChecked())
                    {
                        pay = 1.1 * totalAmount *(1-discount)/peopleNo;
                    }
                    else if(gst.isChecked() && serviceCharge.isChecked()==false)
                    {
                        pay = 1.07 * totalAmount *(1-discount)/ peopleNo;
                    }
                    else
                    {
                        pay = totalAmount*(1-discount)/peopleNo;
                    }
                }
                else
                {
                    if(gst.isChecked() && serviceCharge.isChecked())
                    {
                        pay = 1.17 * totalAmount/peopleNo;
                    }
                    else if(gst.isChecked()== false && serviceCharge.isChecked())
                    {
                        pay = 1.1 * totalAmount/peopleNo;
                    }
                    else if(gst.isChecked() && serviceCharge.isChecked()==false)
                    {
                        pay = 1.07 * totalAmount / peopleNo;
                    }
                    else
                    {
                        pay = totalAmount/peopleNo;
                    }
                }


                String display = String.format("%.2f", pay);
                payDisplay.setText(display);
            }


        });
    }
}
