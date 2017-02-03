package com.mkfnx.officehoursnearsoft.home;

import android.support.annotation.NonNull;

import com.mkfnx.officehoursnearsoft.data.Venue;
import com.mkfnx.officehoursnearsoft.data.source.VenuesDataSource;
import com.mkfnx.officehoursnearsoft.data.source.VenuesRepository;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by mkfnx on 13/01/17.
 */

public class HomePresenter implements HomeContract.Presenter {

    private final VenuesRepository venuesRepository;

    private HomeContract.View view;

    private final CompositeDisposable compositeDisposable;

    private Disposable loadVenuesSubscription;

    public HomePresenter(VenuesRepository venuesRepository, HomeContract.View view, CompositeDisposable compositeDisposable) {
        this.venuesRepository = venuesRepository;
        this.view = view;
        this.compositeDisposable = compositeDisposable;

        view.setPresenter(this);
    }

    @Override
    public void result(int requestCode, int resultCode) {
    }

    @Override
    public void loadVenues() {
        view.setLoadingIndicator(true);

        loadVenuesSubscription = venuesRepository.getVenues()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        venues -> {
                            view.showVenues(venues);
                            view.setLoadingIndicator(false);
                        },
                        throwable -> {
                            view.setLoadingIndicator(false);
                            view.showLoadingVenuesError();
                        });

        compositeDisposable.add(loadVenuesSubscription);
    }

    @Override
    public void openVenueDetails(@NonNull Venue venue) {
        view.showVenueDetails(venue);
    }

    @Override
    public void start() {
        loadVenues();
    }

    @Override
    public void stop() {
        compositeDisposable.remove(loadVenuesSubscription);
        this.view = null;
    }
}
