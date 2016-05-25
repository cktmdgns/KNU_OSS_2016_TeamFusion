/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2014-2015 Perples, Inc.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package mobilelecture.cdp12_app;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.perples.recosdk.RECOBeacon;
import com.perples.recosdk.RECOBeaconRegion;
import com.perples.recosdk.RECOErrorCode;
import com.perples.recosdk.RECORangingListener;

import java.util.ArrayList;
import java.util.Collection;

/**
 * RECORangingActivity class is to range regions in the foreground.
 *
 * RECORangingActivity 클래스는 foreground 상태에서 ranging을 수행합니다.
 */
public class RecoRangingActivity extends RecoActivity implements RECORangingListener {

    private RecoRangingListAdapter mRangingListAdapter;
    private ListView mRegionListView;

    private String return_location_ID = "0";
    private double temp_location = 100.0;

    //비콘 관련 변수들
    //This is a default proximity uuid of the RECO
    public static final String RECO_UUID = "24DDF411-8CF1-440C-87CD-E368DAF9C93E";

    // SCAN_RECO_ONLY:
    // If true, the application scans RECO beacons only, otherwise it scans all beacons.
    // It will be used when the instance of RECOBeaconManager is created.
    // true일 경우 레코 비콘만 스캔하며, false일 경우 모든 비콘을 스캔합니다.
    // RECOBeaconManager 객체 생성 시 사용합니다.

    public static final boolean SCAN_RECO_ONLY = true;


    // ENABLE_BACKGROUND_RANGING_TIMEOUT:
    // If true, the application stops to range beacons in the entered region automatically in 10 seconds (background),
    // otherwise it continues to range beacons. (It affects the battery consumption.)
    // It will be used when the instance of RECOBeaconManager is created.
    // 백그라운드 ranging timeout을 설정합니다.
    // true일 경우, 백그라운드에서 입장한 region에서 ranging이 실행 되었을 때, 10초 후 자동으로 정지합니다.
    // false일 경우, 계속 ranging을 실행합니다. (배터리 소모율에 영향을 끼칩니다.)
    // RECOBeaconManager 객체 생성 시 사용합니다.

    public static final boolean ENABLE_BACKGROUND_RANGING_TIMEOUT = true;


    // DISCONTINUOUS_SCAN:
    // There is a known android bug that some android devices scan BLE devices only once.
    // (link: http://code.google.com/p/android/issues/detail?id=65863)
    // To resolve the bug in our SDK, you can use setDiscontinuousScan() method of the RECOBeaconManager.
    // This method is to set whether the device scans BLE devices continuously or discontinuously.
    // The default is set as FALSE. Please set TRUE only for specific devices.
    // 일부 안드로이드 기기에서 BLE 장치들을 스캔할 때, 한 번만 스캔 후 스캔하지 않는 버그(참고: http://code.google.com/p/android/issues/detail?id=65863)가 있습니다.
    // 해당 버그를 SDK에서 해결하기 위해, RECOBeaconManager에 setDiscontinuousScan() 메소드를 이용할 수 있습니다.
    // 해당 메소드는 기기에서 BLE 장치들을 스캔할 때(즉, ranging 시에), 연속적으로 계속 스캔할 것인지, 불연속적으로 스캔할 것인지 설정하는 것입니다.
    // 기본 값은 FALSE로 설정되어 있으며, 특정 장치에 대해 TRUE로 설정하시길 권장합니다.

    public static final boolean DISCONTINUOUS_SCAN = false;
    private static final int REQUEST_ENABLE_BT = 1;
    private static final int REQUEST_LOCATION = 10;
    private BluetoothManager mBluetoothManager;
    private BluetoothAdapter mBluetoothAdapter;
    private View mLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reco_ranging);


        //비콘 연결

        //If a user device turns off bluetooth, request to turn it on.
        //사용자가 블루투스를 켜도록 요청합니다.
        mBluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        mBluetoothAdapter = mBluetoothManager.getAdapter();

        if (mBluetoothAdapter == null || !mBluetoothAdapter.isEnabled()) {
            Intent enableBTIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBTIntent, REQUEST_ENABLE_BT);
        }

        /**
         * In order to use RECO SDK for Android API 23 (Marshmallow) or higher,
         * the location permission (ACCESS_COARSE_LOCATION or ACCESS_FINE_LOCATION) is required.
         * Please refer to the following permission guide and sample code provided by Google.
         *
         * 안드로이드 API 23 (마시멜로우)이상 버전부터, 정상적으로 RECO SDK를 사용하기 위해서는
         * 위치 권한 (ACCESS_COARSE_LOCATION 혹은 ACCESS_FINE_LOCATION)을 요청해야 합니다.
         * 권한 요청의 경우, 구글에서 제공하는 가이드를 참고하시기 바랍니다.
         *
         * http://www.google.com/design/spec/patterns/permissions.html
         * https://github.com/googlesamples/android-RuntimePermissions
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                Log.i("RecoRangingActivity", "The location permission (ACCESS_COARSE_LOCATION or ACCESS_FINE_LOCATION) is not granted.");
                this.requestLocationPermission();
            } else {
                Log.i("RecoRangingActivity", "The location permission (ACCESS_COARSE_LOCATION or ACCESS_FINE_LOCATION) is already granted.");
            }
        }

        //비콘 끝




        //mRecoManager will be created here. (Refer to the RECOActivity.onCreate())
        //mRecoManager 인스턴스는 여기서 생성됩니다. RECOActivity.onCreate() 메소들르 참고하세요.

        //Set RECORangingListener (Required)
        //RECORangingListener 를 설정합니다. (필수)
        mRecoManager.setRangingListener(this);

        /**
         * Bind RECOBeaconManager with RECOServiceConnectListener, which is implemented in RECOActivity
         * You SHOULD call this method to use monitoring/ranging methods successfully.
         * After binding, onServiceConenct() callback method is called.
         * So, please start monitoring/ranging AFTER the CALLBACK is called.
         *
         * RECOServiceConnectListener와 함께 RECOBeaconManager를 bind 합니다. RECOServiceConnectListener는 RECOActivity에 구현되어 있습니다.
         * monitoring 및 ranging 기능을 사용하기 위해서는, 이 메소드가 "반드시" 호출되어야 합니다.
         * bind후에, onServiceConnect() 콜백 메소드가 호출됩니다. 콜백 메소드 호출 이후 monitoring / ranging 작업을 수행하시기 바랍니다.
         */
        mRecoManager.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        mRangingListAdapter = new RecoRangingListAdapter(this);
        mRegionListView = (ListView)findViewById(R.id.list_ranging);
        mRegionListView.setAdapter(mRangingListAdapter);
    }


    @Override
     protected void onDestroy() {

        Intent intent_out_location = new Intent(getApplicationContext(),MapViewActivity.class);
        intent_out_location.putExtra("current_location_ID", return_location_ID);
        intent_out_location.putExtra("current_location_LOC", temp_location + "");
        Log.i("RECORangingActivity", "" + return_location_ID + "  " + temp_location);
        intent_out_location.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent_out_location);



        super.onDestroy();
        this.stop(mRegions);
        this.unbind();
    }

    private void unbind() {
        try {
            mRecoManager.unbind();
        } catch (RemoteException e) {
            Log.i("RECORangingActivity", "Remote Exception");
            e.printStackTrace();
        }
    }

    @Override
    public void onServiceConnect() {
        Log.i("RECORangingActivity", "onServiceConnect()");
        mRecoManager.setDiscontinuousScan(RecoRangingActivity.DISCONTINUOUS_SCAN);
        this.start(mRegions);
        //Write the code when RECOBeaconManager is bound to RECOBeaconService
    }

    @Override
    public void didRangeBeaconsInRegion(Collection<RECOBeacon> recoBeacons, RECOBeaconRegion recoRegion) {
        Log.i("RECORangingActivity", "didRangeBeaconsInRegion() region: " + recoRegion.getUniqueIdentifier() + ", number of beacons ranged: " + recoBeacons.size());
        mRangingListAdapter.updateAllBeacons(recoBeacons);
        mRangingListAdapter.notifyDataSetChanged();
        //Write the code when the beacons in the region is received



        for(int i=0; i<mRangingListAdapter.getCount();i++) {
            if(temp_location > Double.valueOf(mRangingListAdapter.getAccuracy(i))) {
                temp_location = Double.valueOf(mRangingListAdapter.getAccuracy(i));
                return_location_ID = mRangingListAdapter.getMinor(i);
            }
        }

        if(recoBeacons.size() >= 3) {
            onDestroy();
            finish();
        }
    }

    @Override
    protected void start(ArrayList<RECOBeaconRegion> regions) {

        /**
         * There is a known android bug that some android devices scan BLE devices only once. (link: http://code.google.com/p/android/issues/detail?id=65863)
         * To resolve the bug in our SDK, you can use setDiscontinuousScan() method of the RECOBeaconManager.
         * This method is to set whether the device scans BLE devices continuously or discontinuously.
         * The default is set as FALSE. Please set TRUE only for specific devices.
         *
         * mRecoManager.setDiscontinuousScan(true);
         */

        for(RECOBeaconRegion region : regions) {
            try {
                mRecoManager.startRangingBeaconsInRegion(region);
            } catch (RemoteException e) {
                Log.i("RECORangingActivity", "Remote Exception");
                e.printStackTrace();
            } catch (NullPointerException e) {
                Log.i("RECORangingActivity", "Null Pointer Exception");
                e.printStackTrace();
            }
        }

    }

    @Override
    protected void stop(ArrayList<RECOBeaconRegion> regions) {
        for(RECOBeaconRegion region : regions) {
            try {
                mRecoManager.stopRangingBeaconsInRegion(region);
            } catch (RemoteException e) {
                Log.i("RECORangingActivity", "Remote Exception");
                e.printStackTrace();
            } catch (NullPointerException e) {
                Log.i("RECORangingActivity", "Null Pointer Exception");
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_LOCATION: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Snackbar.make(mLayout, R.string.location_permission_granted, Snackbar.LENGTH_LONG).show();
                } else {
                    Snackbar.make(mLayout, R.string.location_permission_not_granted, Snackbar.LENGTH_LONG).show();
                }
            }
            default:
                break;
        }
    }

    // 비콘 테스트

    /**
     * In order to use RECO SDK for Android API 23 (Marshmallow) or higher,
     * the location permission (ACCESS_COARSE_LOCATION or ACCESS_FINE_LOCATION) is required.
     * <p/>
     * This sample project requests "ACCESS_COARSE_LOCATION" permission only,
     * but you may request "ACCESS_FINE_LOCATION" permission depending on your application.
     * <p/>
     * "ACCESS_COARSE_LOCATION" permission is recommended.
     * <p/>
     * 안드로이드 API 23 (마시멜로우)이상 버전부터, 정상적으로 RECO SDK를 사용하기 위해서는
     * 위치 권한 (ACCESS_COARSE_LOCATION 혹은 ACCESS_FINE_LOCATION)을 요청해야 합니다.
     * <p/>
     * 본 샘플 프로젝트에서는 "ACCESS_COARSE_LOCATION"을 요청하지만, 필요에 따라 "ACCESS_FINE_LOCATION"을 요청할 수 있습니다.
     * <p/>
     * 당사에서는 ACCESS_COARSE_LOCATION 권한을 권장합니다.
     */
    private void requestLocationPermission() {
        if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_COARSE_LOCATION)) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_LOCATION);
            return;
        }

        Snackbar.make(mLayout, "Location permission is needed to monitor or range beacons.", Snackbar.LENGTH_INDEFINITE)
                .setAction("ok", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ActivityCompat.requestPermissions(RecoRangingActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_LOCATION);
                    }
                })
                .show();
    }


    @Override
    public void onServiceFail(RECOErrorCode errorCode) {
        //Write the code when the RECOBeaconService is failed.
        //See the RECOErrorCode in the documents.
        return;
    }

    @Override
    public void rangingBeaconsDidFailForRegion(RECOBeaconRegion region, RECOErrorCode errorCode) {
        //Write the code when the RECOBeaconService is failed to range beacons in the region.
        //See the RECOErrorCode in the documents.
        return;
    }
}
