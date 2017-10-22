package nagoya.united.oiden;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.Image;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;


public class MapsActivity extends DrawerActivity implements OnMapReadyCallback,LocationListener,DialogInterface.OnClickListener{

    private GoogleMap mMap;
    private LocationManager locationManager;
    private LatLng mNowLatLng = new LatLng(35.689634, 139.692101);
    private double mMarkerSizeMag = 0.75;
    private MapInputFormManager mapInputFormManager;
    private Marker mMarker = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        mapInputFormManager = new MapInputFormManager(this);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);
        }
        else{
            locationStart();
        }

    }

    @Override
    protected void onClickMyLocation() {
        moveCameraOnTheMap(mNowLatLng);
        if(mMarker != null){
            mMarker.remove();
        }
        mMarker = mMap.addMarker(new MarkerOptions().position(mNowLatLng).title("My Location"));
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng tokyo = new LatLng(35.689634, 139.692101);
        //mMap.addMarker(new MarkerOptions().position(tokyo).title("Marker on nowPlace"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(tokyo));
        mMap.setMinZoomPreference(12f);

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener(){
        //マーカークリック時の処理
            @Override
            public boolean onMarkerClick(Marker marker) {
                return false;
            }
        });

        setMarkerOnTheMap(tokyo,createBitmap());
    }

    private void locationStart(){
        Log.d("debug","locationStart()");

        // LocationManager インスタンス生成
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        final boolean gpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        if (!gpsEnabled) {
            // GPSを設定するように促す
            Intent settingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(settingsIntent);
            Log.d("debug", "not gpsEnable, startActivity");
        } else {
            Log.d("debug", "gpsEnabled");
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);

            Log.d("debug", "checkSelfPermission false");
            return;
        }

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 50, this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 1000) {
            // 使用が許可された
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d("debug","checkSelfPermission true");

                locationStart();
                return;

            } else {
                // それでも拒否された時の対応
                Toast toast = Toast.makeText(this, "現在位置を使用できません", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }

    public void setMarkerOnTheMap(LatLng _latLng,Bitmap _bitmap){
        //LatLng latLng = new LatLng(35.689634, 139.692101);
        MarkerOptions markerOptions =new MarkerOptions();
        //Bitmap bitmap = createBitmap();
        int newWidth = (int)(_bitmap.getWidth()*mMarkerSizeMag);
        int newHeight = (int)(_bitmap.getHeight()*mMarkerSizeMag);
        Bitmap resizeBitmap = Bitmap.createScaledBitmap(_bitmap,newWidth,newHeight,false);
        final BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(resizeBitmap);
        mMap.addMarker(new MarkerOptions().position(_latLng).icon(bitmapDescriptor));
    }

    public Bitmap createBitmap() {

        // ビューを生成
        // 例として、TextViewに動的にテキストを入れる
        View v = LayoutInflater.from(this).inflate(R.layout.map_item, null);
        //TextView tv = (TextView)v.findViewById(R.id.mapMessage);
        //tv.setText("ここに動的にテキストを入れる");

        // 画面内に配置してないので、measureを読んでからBitmapに書き込む
        if (v.getMeasuredHeight() <= 0) {
            v.measure(ViewPager.LayoutParams.WRAP_CONTENT, ViewPager.LayoutParams.WRAP_CONTENT);
            Bitmap b = Bitmap.createBitmap(v.getMeasuredWidth(), v.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
            Canvas c = new Canvas(b);
            v.layout(0, 0, v.getMeasuredWidth(), v.getMeasuredHeight());
            v.draw(c);
            return b;
        }
        Log.d("mytag","return_null");
        return null;
    }


    //カメラの移動用関数
    public void moveCameraOnTheMap(LatLng latLng){
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
    }

    @Override
    public void onLocationChanged(Location location) {
        double lat = location.getLatitude();
        double lng = location.getLongitude();
        mNowLatLng = new LatLng(lat, lng);
        mMarker = mMap.addMarker(new MarkerOptions().position(mNowLatLng).title("My Location"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(mNowLatLng));
        Log.d("mytag",lat+" "+lng);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onClick(DialogInterface dialog, int which) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (mapInputFormManager != null) {
            switch (requestCode) {
                case MapInputFormManager.SELECT_IMAGE_REQUEST_CODE :
                    mapInputFormManager.onSelectImage(data);
                    return;
            }
        }
    }
}