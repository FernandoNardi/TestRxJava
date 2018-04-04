package br.nardi.testrxjava.examples.DirChapter5;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;

/**
 * Created by Nardi on 03/04/2018.
 */

@SuppressLint("RestrictedApi")
public class LocationManager implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    GoogleApiClient googleApiClient;

    LocationManager(Context context) {
        this.googleApiClient = new GoogleApiClient.Builder(context)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    public void connect() {
        this.googleApiClient.connect();
    }

    public boolean isConnected() {
        return this.googleApiClient.isConnected();
    }

//    Observable<Location> locationObservable = Observable.create(emitter -> {
//        if (!isConnected()) {
//            emitter.onError(
//                    new Exception("GoogleApiClient not connected")
//            );
//            return;
//        }
//
//        LocationRequest locationRequest = new LocationRequest();
//        locationRequest.setInterval(1000);
//        locationRequest.setPriority(
//                LocationRequest.PRIORITY_HIGH_ACCURACY
//        );
//
//        // Check location permissions
//        LocationSettingsRequest.Builder builder =
//                new LocationSettingsRequest.Builder()
//                        .addLocationRequest(locationRequest);
//        PendingResult<LocationSettingsResult> pendingResult =
//                LocationServices.SettingsApi.checkLocationSettings(
//                        googleApiClient,
//                        builder.build()
//                );
//        pendingResult.setResultCallback(result -> {
//            Status status = result.getStatus();
//
//            switch (status.getStatusCode()) {
//                case LocationSettingsStatusCodes.SUCCESS:
//                    startLocationUpdates(
//                            googleApiClient,
//                            emitter,
//                            locationRequest
//                    );
//                    break;
//                default:
//                    // Resolution required from user
//                    break;
//            }
//        });
//    }).share();
//
//    Observable<Location> observeLocation() {
//        return locationObservable;
//    }

    public Observable<Location> observeLocation() {
        return Observable.create(emitter -> {
           if(!isConnected()) {
               emitter.onError(new Exception("GoogleApiClient not connected"));
               return;
           }

           LocationRequest locationRequest = new LocationRequest();
           locationRequest.setInterval(1000);
           locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

            LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);

            PendingResult<LocationSettingsResult> pendingResult = LocationServices.SettingsApi.checkLocationSettings(googleApiClient, builder.build());

            pendingResult.setResultCallback(result -> {
                Status status = result.getStatus();

                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:
                        startLocationUpdates(
                                googleApiClient,
                                emitter,
                                locationRequest);
                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        break;
                }
            });
        });
    }

    @SuppressWarnings({"MissingPermission"})
    private void startLocationUpdates(GoogleApiClient googleApiClient, ObservableEmitter<Location> emitter, LocationRequest request) {
        LocationListener locationListener = location -> {
            if (!emitter.isDisposed()) {
                emitter.onNext(location);
            }
        };

        emitter.setCancellable(() -> {
            LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, locationListener);
        });

        LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, request, locationListener);

    }
}
