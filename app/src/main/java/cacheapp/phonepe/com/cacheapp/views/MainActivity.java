package cacheapp.phonepe.com.cacheapp.views;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import cacheapp.phonepe.com.cacheapp.R;
import cacheapp.phonepe.com.cacheapp.databinding.ActivityMainBinding;
import cacheapp.phonepe.com.cacheapp.viewmodels.MainViewModel;
import rx.subscriptions.CompositeSubscription;

public class MainActivity extends AppCompatActivity {

    private CompositeSubscription subscriptions;
    private MainViewModel mainViewModel;
    ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setListeners();
        subscriptions = new CompositeSubscription();
        mainViewModel = new MainViewModel();
        mainViewModel.initializeMap(10);
        registerSubscriptions();
    }

    private void setListeners() {
        /**
         * In this onClickListener we see whether the number is in the cache if the Edit text has a non-empty value
         * {@link MainViewModel#checkInCache(Integer)} does the checking
         */
        activityMainBinding.contentMain.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String numberString = activityMainBinding.contentMain.entryEditText.getText().toString();
                if (numberString.isEmpty()) {
                    Toast.makeText(MainActivity.this, getResources().getString(R.string.enter_number),
                            Toast.LENGTH_SHORT).show();
                            return;
                }
                mainViewModel.checkInCache(Integer.valueOf(numberString));
                activityMainBinding.contentMain.entryEditText.setText("");
            }
        });
    }

    private void registerSubscriptions() {
        /**
         * Displays messages based on whether number exists in the cache or not
         */
        subscriptions.addAll(
                mainViewModel.getInCacheObservable().subscribe(
                        status -> {
                            String message = status ? getResources().getString(R.string.number_exists) :
                                    getResources().getString(R.string.number_does_not_exist);
                            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                        }
                )
        );
    }

    /**
     * Un subscribing all subscriptions
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        subscriptions.unsubscribe();
    }

}
