package com.example.qrgenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.qrgenerator.databinding.ActivityMainBinding;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.generateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                //Get input value from edit text
                String sText = binding.etInput.getText().toString().trim();
                //Initialize multi format writer
                MultiFormatWriter writer = new MultiFormatWriter();

                try {
                    //Initialize bit matrix
                    BitMatrix matrix = writer.encode(sText, BarcodeFormat.QR_CODE,350,350);
                    //Initialize barcode encoder
                    BarcodeEncoder encoder = new BarcodeEncoder();
                    //Initialize bitmap
                    Bitmap bitmap = encoder.createBitmap(matrix);
                    //Set bitmap on image view
                    binding.ivOutput.setImageBitmap(bitmap);
                    //Initialize input manager
                    InputMethodManager manager = (InputMethodManager) getSystemService(
                            Context.INPUT_METHOD_SERVICE
                    );
                    //Hide soft keyboard
                    manager.hideSoftInputFromWindow(binding.etInput.getApplicationWindowToken(), 0);
                } catch (WriterException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}