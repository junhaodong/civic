package hu.ait.civic;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import hu.ait.civic.adapter.RepresentativeAdapter;
import hu.ait.civic.data.CivicResult;
import hu.ait.civic.data.Office;
import hu.ait.civic.data.Representative;
import hu.ait.civic.network.CivicAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static final String KEY_REPRESENTATIVE = "KEY_REPRESENTATIVE";

    // Test Key
    private static final String APP_KEY = "AIzaSyDIiThtrLkqN7FZfATAfX13ZBqSX1rkIkE";

    private CoordinatorLayout layout;
    private RepresentativeAdapter adapter;
    private EditText etAddress;

    private CivicAPI civicAPI;

    // TODO / improvements: store address, filters (rep level/office role), total party counts, voter info

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = findViewById(R.id.coordinator_layout);

        adapter = new RepresentativeAdapter(new ArrayList<Representative>(), this);
        RecyclerView recyclerView = findViewById(R.id.recyclerViewRepresentatives);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.civic_api_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        civicAPI = retrofit.create(CivicAPI.class);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        etAddress = findViewById(R.id.etAddress);

        Button submitButton = findViewById(R.id.buttonSubmit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getRepresentatives();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_about) {
            showSnackBarMessage(getString(R.string.txt_about));
        }

        return super.onOptionsItemSelected(item);
    }

    public void getRepresentatives() {
        Call<CivicResult> call = civicAPI.getRepresentatives(etAddress.getText().toString(), APP_KEY);

        call.enqueue(new Callback<CivicResult>() {
            @Override
            public void onResponse(Call<CivicResult> call, Response<CivicResult> response) {
                if (!response.isSuccessful()) {
                    showSnackBarMessage("Please enter a valid address.");
                    return;
                }
                CivicResult result = response.body();
                Representative[] representatives = result.getRepresentatives();
                for (Office office : result.getOffices()) {
                    for (int index : office.getOfficialIndices()) {
                        representatives[index].setTitle(office.getName());
                    }
                }

                etAddress.setText(result.getAddress().getAddressString());
                adapter.clearRepresentatives();
                adapter.addRepresentatives(representatives);
            }

            @Override
            public void onFailure(Call<CivicResult> call, Throwable t) {
                showSnackBarMessage("Request Failed. Please enter a valid address.");
            }
        });
    }

    public void showRepresentativeDetails(Representative representative) {
        Intent intentStart = new Intent(MainActivity.this, RepresentativeDetailsActivity.class);

        String address = null;
        if (representative.getAddresses() != null) {
            address = representative.getAddresses()[0].getAddressString();
        }
        String phone = null;
        if (representative.getPhones() != null) {
            phone = representative.getPhones()[0];
        }
        String url = null;
        if (representative.getUrls() != null) {
            url = representative.getUrls()[0];
        }

        String[] repData = {representative.getName(),
                representative.getTitle(),
                representative.getParty(),
                representative.getPhotoUrl(),
                address,
                phone,
                url};

        intentStart.putExtra(KEY_REPRESENTATIVE, repData);
        startActivity(intentStart);
    }

    private void showSnackBarMessage(String message) {
        Snackbar.make(layout, message, Snackbar.LENGTH_LONG).show();
    }
}
