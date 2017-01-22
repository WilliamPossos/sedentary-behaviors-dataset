package com.example.william.data_set.lib;
import android.content.Context;
import android.util.Log;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;
import com.estimote.sdk.Utils;
import com.example.william.data_set.lib.BeaconID;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class NearestBeaconManager {

    private static final String TAG = "NearestBeaconManager";

    private static final Region ALL_ESTIMOTE_BEACONS = new Region("all Estimote beacons", null, null, null);

    private List<BeaconID> beaconIDs;

    private Listener listener;

    private BeaconID currentlyNearestBeaconID;
    private boolean firstEventSent = false;

    private BeaconManager beaconManager;

    public NearestBeaconManager(Context context, List<BeaconID> beaconIDs) {
        this.beaconIDs = beaconIDs;

        beaconManager = new BeaconManager(context);
        beaconManager.setRangingListener(new BeaconManager.RangingListener() {
            @Override
            public void onBeaconsDiscovered(Region region, List<Beacon> list) {
                checkForNearestBeacon(list);
            }
        });
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public interface Listener {
        void onNearestBeaconChanged(Map<String, Integer> beacon);
    }

    public void startNearestBeaconUpdates() {
        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                beaconManager.startRanging(ALL_ESTIMOTE_BEACONS);
            }
        });
    }

    public void stopNearestBeaconUpdates() {
        beaconManager.stopRanging(ALL_ESTIMOTE_BEACONS);
    }

    public void destroy() {
        beaconManager.disconnect();
    }

    private void checkForNearestBeacon(List<Beacon> allBeacons) {
        List<Beacon> beaconsOfInterest = filterOutBeaconsByIDs(allBeacons, beaconIDs);
        Map<String, Integer> beaconsInfo = findNearestBeacon(beaconsOfInterest);
        if (beaconsInfo != null || !firstEventSent) {
            updateNearestBeacon(beaconsInfo);
        }
    }

    private void updateNearestBeacon(Map<String,Integer> beaconsInfo) {
        firstEventSent = true;
        if (listener != null) {
            listener.onNearestBeaconChanged(beaconsInfo);
        }
    }

    private static List<Beacon> filterOutBeaconsByIDs(List<Beacon> beacons, List<BeaconID> beaconIDs) {
        List<Beacon> filteredBeacons = new ArrayList<>();

        for (Beacon beacon : beacons) {
            BeaconID beaconID = BeaconID.fromBeacon(beacon);
            if (beaconIDs.contains(beaconID)) {
                filteredBeacons.add(beacon);
            }
        }
        return filteredBeacons;
    }

    private static Map<String, Integer> findNearestBeacon(List<Beacon> beacons) {
        Map<String, Integer> beaconsInfo = new LinkedHashMap<String, Integer>();
        for (Beacon beacon : beacons) {
            double distance = Utils.computeAccuracy(beacon);
            Log.e("BeaconsMAC",""+beacon.getMacAddress());
            beaconsInfo.put(beacon.getMacAddress().toString(), beacon.getRssi());
        }

        return beaconsInfo;
    }
}
