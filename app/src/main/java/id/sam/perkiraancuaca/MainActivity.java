package id.sam.perkiraancuaca;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import id.sam.perkiraancuaca.adapter.AdapterListSimple;
import id.sam.perkiraancuaca.model.Cuaca;
import id.sam.perkiraancuaca.service.APIClient;
import id.sam.perkiraancuaca.service.APIInterfacesRest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView lstCuaca;
    EditText txtKota;
    Button btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lstCuaca = findViewById(R.id.lstCuaca);
        txtKota = findViewById(R.id.txtKota);
        btnSearch = findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callForecastbyCity(txtKota.getText().toString());
            }
        });

    }

    APIInterfacesRest apiInterface;
    ProgressDialog progressDialog;
    public void callForecastbyCity(String kota){

        apiInterface = APIClient.getClient().create(APIInterfacesRest.class);
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();
        Call<Cuaca> call3 = apiInterface.getForecastByCity(kota,"4fd84305fb0f6c588a1f00991b3a73b5");
        call3.enqueue(new Callback<Cuaca>() {
            @Override
            public void onResponse(Call<Cuaca> call, Response<Cuaca> response) {
                progressDialog.dismiss();
                Cuaca dataWeather = response.body();
                //Toast.makeText(LoginActivity.this,userList.getToken().toString(),Toast.LENGTH_LONG).show();
                if (dataWeather !=null) {

                    //     txtKota.setText(dataWeather.getName());
                    //     txtTemperature.setText(new DecimalFormat("##.##").format(dataWeather.getMain().getTemp()-273.15));

                    AdapterListSimple adapter = new AdapterListSimple(MainActivity.this,dataWeather.getList(),dataWeather.getCity().getName());


                    lstCuaca.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    lstCuaca.setItemAnimator(new DefaultItemAnimator());
                    lstCuaca.setAdapter(adapter);
                }else{

                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(MainActivity.this, jObjError.getString("message"), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Cuaca> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),"Maaf koneksi bermasalah",Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });
    }
}