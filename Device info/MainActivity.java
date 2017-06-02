package info.device.andreas.deviceinfo;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.nfc.Tag;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private AdView mAdView;

    String myDevice = Build.DEVICE;
    String myDeviceModel = android.os.Build.MODEL;
    String myDeviceBoard = Build.BOARD;
    String myDeviceBootload = Build.BOOTLOADER;
    String myDeviceBrand = Build.BRAND;
    String myDeviceDisplay = Build.DISPLAY;
    String myDeviceFingerprint = Build.FINGERPRINT;
    String myDeviceHardware = Build.HARDWARE;
    String myDeviceHost = Build.HOST;
    String myDeviceId = Build.ID;
    String myDeviceManufacturer = Build.MANUFACTURER;
    String myDeviceProduct = Build.PRODUCT;
    String myDeviceSerial = Build.SERIAL;
    String myDeviceTags = Build.TAGS;
    String myDeviceType = Build.TYPE;
    String myDeviceUser = Build.USER;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        TextView device = (TextView) findViewById(R.id.Device);
        device.setText("Current device: " + myDevice);

        TextView Model = (TextView) findViewById(R.id.Model);
        Model.setText("Model name: " + myDeviceModel);

        TextView Board = (TextView) findViewById(R.id.Board);
        Board.setText("Device board: " + myDeviceBoard);

        TextView Bootload = (TextView) findViewById(R.id.Bootload);
        Bootload.setText("Bootload: " + myDeviceBootload);

        TextView Brand = (TextView) findViewById(R.id.Brand);
        Brand.setText("Device brand: " + myDeviceBrand);

        TextView Display = (TextView) findViewById(R.id.Display);
        Display.setText("Device display: " + myDeviceDisplay);

        TextView Fingerprint = (TextView) findViewById(R.id.Fingerprint);
        Fingerprint.setText("Device fingerprint: " + myDeviceFingerprint);

        TextView Hardware = (TextView) findViewById(R.id.Hardware);
        Hardware.setText("Device hardware: " + myDeviceHardware);

        TextView Host = (TextView) findViewById(R.id.Host);
        Host.setText("Host: " + myDeviceHost);

        TextView Id = (TextView) findViewById(R.id.Id);
        Id.setText("Device id: " + myDeviceId);

        TextView Manufacturer = (TextView) findViewById(R.id.Manufacturer);
        Manufacturer.setText("Manufacturer: " + myDeviceManufacturer);

        TextView Product = (TextView) findViewById(R.id.Product);
        Product.setText("Product: " + myDeviceProduct);

        TextView Serial = (TextView) findViewById(R.id.Serial);
        Serial.setText("Serial: " + myDeviceSerial);

        TextView Tags = (TextView) findViewById(R.id.Tags);
        Tags.setText("Device tag: " + myDeviceTags);

        TextView Type = (TextView) findViewById(R.id.Type);
        Type.setText("Type: " + myDeviceType);

        TextView User = (TextView) findViewById(R.id.User);
        User.setText("User: " + myDeviceUser);


    }


}
