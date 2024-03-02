package ru.versoit.lyaschenko_ra_2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import ru.versoit.lyaschenko_ra_2.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private final ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    assert data != null;
                    Toast.makeText(this, "Сумма покупки: " + data.getIntExtra(ProductActivity.PRODUCT_COST, 0), Toast.LENGTH_LONG).show();
                }
            }
    );

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        bindViews();
    }

    private void bindViews() {
        binding.apples.setOnClickListener(view -> {
            navigateToProduct(ProductActivity.Products.APPLE);
        });
        binding.bananas.setOnClickListener(view -> {
            navigateToProduct(ProductActivity.Products.BANANA);
        });
        binding.lemons.setOnClickListener(view -> {
            navigateToProduct(ProductActivity.Products.LEMON);
        });
        binding.orange.setOnClickListener(view -> {
            navigateToProduct(ProductActivity.Products.ORANGE);
        });

        binding.fruits.setImageResource(R.drawable.fruits);
    }

    private void navigateToProduct(ProductActivity.Products product) {
        Intent intent = new Intent(this, ProductActivity.class);
        intent.putExtra(ProductActivity.PRODUCT_NAME_BUNDLE, product.ordinal());
        activityResultLauncher.launch(intent);
    }
}
