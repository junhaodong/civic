package hu.ait.civic;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;


public class RepresentativeDetailsActivity extends AppCompatActivity {
    private TextView tvRepTitle;
    private TextView tvParty;
    private CircleImageView ivRepPhoto;
    private TextView tvAddress;
    private TextView tvPhone;
    private TextView tvUrl;

    private void init() {
        tvRepTitle = findViewById(R.id.tvRepTitle);
        tvParty = findViewById(R.id.tvParty);
        ivRepPhoto = findViewById(R.id.ivRepPhoto);
        tvAddress = findViewById(R.id.tvAddress);
        tvPhone = findViewById(R.id.tvPhone);
        tvUrl = findViewById(R.id.tvUrl);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_representative_details);

        init();

        String[] repData = getIntent().getStringArrayExtra(MainActivity.KEY_REPRESENTATIVE);

        setTitle(repData[0]);
        tvRepTitle.setText(repData[1]);
        tvParty.setText(repData[2]);
        if (repData[3] != null) {
            Glide.with(this).load(repData[3]).into(ivRepPhoto);
        }
        tvAddress.setText(repData[4]);
        tvPhone.setText(repData[5]);
        tvUrl.setText(repData[6]);

        // TODO: Single source this
        if (repData[2] != null && repData[2].equals("Republican")) {
            ivRepPhoto.setBorderColor(Color.parseColor("#E91D0E"));
        } else if (repData[2] != null && repData[2].equals("Democratic")) {
            ivRepPhoto.setBorderColor(Color.parseColor("#3333FF"));
        } else {
            ivRepPhoto.setBorderColor(Color.parseColor("#a4a4a4"));
        }
    }
}
