package com.mkfnx.officehoursnearsoft.home;

import android.support.annotation.NonNull;

import com.mkfnx.officehoursnearsoft.data.Venue;
import com.mkfnx.officehoursnearsoft.data.source.VenuesRepository;
import com.mkfnx.officehoursnearsoft.util.BaseScheduler;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by mkfnx on 13/01/17.
 */

public class HomePresenter implements HomeContract.Presenter {

    private final VenuesRepository venuesRepository;

    private HomeContract.View view;

    private final CompositeDisposable compositeDisposable;

    private final BaseScheduler scheduler;

    public HomePresenter(VenuesRepository venuesRepository, HomeContract.View view, CompositeDisposable compositeDisposable, BaseScheduler scheduler) {
        this.venuesRepository = venuesRepository;
        this.view = view;
        this.compositeDisposable = compositeDisposable;
        this.scheduler = scheduler;

        view.setPresenter(this);
    }

    public HomeContract.View getView() {
        return view;
    }

    @Override
    public void result(int requestCode, int resultCode) {
    }

    @Override
    public void loadVenues() {
        view.setLoadingIndicator(true);

        Disposable loadVenuesSubscription = venuesRepository.getVenues()
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.ui())
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
        compositeDisposable.clear();
        this.view = null;
    }
}
