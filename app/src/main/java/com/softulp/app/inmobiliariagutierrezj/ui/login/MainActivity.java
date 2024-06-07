package com.softulp.app.inmobiliariagutierrezj.ui.login;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import android.Manifest;
import com.softulp.app.inmobiliariagutierrezj.R;
import com.softulp.app.inmobiliariagutierrezj.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements SensorEventListener{
    private SensorManager sensorManager;
    private Sensor accelerometer;
    private long lastUpdate;
    private static final int SHAKE_THRESHOLD = 800;
    private static final int CALL_PHONE_PERMISSION_REQUEST_CODE = 1;
    private static final int CALL_PHONE_REQUEST_CODE = 2;

    private float last_x, last_y, last_z;
    ActivityMainBinding binding;
    MainViewModel vm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager != null) {
            accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            sensorManager.registerListener((SensorEventListener) this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        }

        lastUpdate = System.currentTimeMillis();


        binding= ActivityMainBinding.inflate(getLayoutInflater());
        vm= ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainViewModel.class);
        setContentView(binding.getRoot());

        vm.getMutableMsgError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.tvMsgError.setText(s);
                binding.tvEmail.setTextColor(Color.RED);
                binding.progressBar.setVisibility(View.GONE);
                vm.setMutableLoginStatus(View.GONE);
            }
        });

        vm.getMutableVisible().observe(this, new Observer<Visible>() {
            @Override
            public void onChanged(Visible visible) {
                binding.etPass.setInputType(visible.getTipoInput());
                binding.ivIsVisible.setImageResource(visible.getImagen());
                binding.etPass.setSelection(visible.getCursorPosition());
            }
        });

        binding.btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.progressBar.setVisibility(View.VISIBLE);

                vm.validar(binding.etEmail.getText().toString(),binding.etPass.getText().toString());
            }
        });

        vm.getMutableLoginStatus().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer i) {
                binding.progressBar.setVisibility(i);
            }
        });

        binding.btnOlvidarPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, RestablecerPassActivity.class);
                startActivity(intent);
            }
        });
        binding.ivIsVisible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vm.cambiarVisible( binding.etPass.getSelectionStart());
            }
        });


    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float[] values = event.values;
            float x = values[0];
            float y = values[1];
            float z = values[2];

            long actualTime = System.currentTimeMillis();
            if ((actualTime - lastUpdate) > 100) {
                long diffTime = (actualTime - lastUpdate);
                lastUpdate = actualTime;

                float speed = Math.abs(x + y + z - last_x - last_y - last_z) / diffTime * 10000;

                if (speed > SHAKE_THRESHOLD) {
                    // Shake detected
                    makePhoneCall();
                }

                last_x = x;
                last_y = y;
                last_z = z;
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    private void makePhoneCall() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, CALL_PHONE_PERMISSION_REQUEST_CODE);
        } else {
            startCall();
        }
    }

    private void startCall() {
        String phoneNumber = "tel:2664378600";
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse(phoneNumber));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            try {
                startActivityForResult(callIntent, CALL_PHONE_REQUEST_CODE);
            } catch (Exception e) {
                Toast.makeText(this, "Failed to initiate call: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Permission to make calls is not granted", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CALL_PHONE_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startCall();
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CALL_PHONE_REQUEST_CODE) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);



        }
    }



    @Override
    protected void onPause() {
        super.onPause();
        if (sensorManager != null) {
            sensorManager.unregisterListener(this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        vm.setMutableLoginStatus(View.GONE);
        if (sensorManager != null) {
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }



}