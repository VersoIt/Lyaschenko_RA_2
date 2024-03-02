package ru.versoit.lyaschenko_ra_2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.SeekBar;

import java.util.HashMap;
import java.util.Map;
import ru.versoit.lyaschenko_ra_2.databinding.ActivityProductBinding;

public class ProductActivity extends AppCompatActivity {

    enum Products {
        APPLE,
        BANANA,
        ORANGE,
        LEMON
    }

    private int amount = 0;

    private ActivityProductBinding binding;

    public static final String PRODUCT_NAME_BUNDLE = "PRODUCT_NAME";

    public static final String PRODUCT_COST = "PRODUCT_COST";

    private Products selectedProduct;

    public static final String TAG = "PRODUCT_ACTIVITY";

    private final Map<Integer, Integer> costs = new HashMap<Integer, Integer>() {{
        put(Products.APPLE.ordinal(), 30);
        put(Products.BANANA.ordinal(), 50);
        put(Products.ORANGE.ordinal(), 40);
        put(Products.LEMON.ordinal(), 30);
    }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductBinding.inflate(getLayoutInflater());
        selectedProduct = Products.values()[getIntent().getIntExtra(PRODUCT_NAME_BUNDLE, 0)];
        setContentView(binding.getRoot());
        bindViews();
    }

    private void bindViews() {
        binding.submit.setOnClickListener(view -> {
            Intent intent = new Intent();
            Integer cost = costs.get(selectedProduct.ordinal());
            if (cost == null) {
                setResult(Activity.RESULT_CANCELED, intent);
                Log.e(TAG, "Activity finished with error(s)");
                finish();
                return;
            }
            intent.putExtra(PRODUCT_COST, amount * cost);
            setResult(Activity.RESULT_OK, intent);
            finish();
        });

        binding.countSelectionProgress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                amount = progress;
                binding.submit.setEnabled(amount != 0);
                binding.amount.setText(String.valueOf(amount));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}