package com.mkfnx.officehoursnearsoft.home;

import android.support.annotation.NonNull;

import com.mkfnx.officehoursnearsoft.data.Venue;
import com.mkfnx.officehoursnearsoft.data.source.VenuesDataSource;
import com.mkfnx.officehoursnearsoft.data.source.VenuesRepository;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by mkfnx on 13/01/17.
 */

public class HomePresenter implements HomeContract.Presenter {

    private final VenuesRepository venuesRepository;

    private final HomeContract.View view;

    public HomePresenter(VenuesRepository venuesRepository, HomeContract.View view) {
        this.venuesRepository = venuesRepository;
        this.view = view;

        view.setPresenter(this);
    }

    @Override
    public void result(int requestCode, int resultCode) {
    }

    @Override
    public void loadVenues() {
        view.setLoadingIndicator(true);

        venuesRepository.getVenues()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Consumer<List<Venue>>() {
                            @Override
                            public void accept(List<Venue> venues) throws Exception {
                                view.showVenues(venues);
                            }
                        },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                view.setLoadingIndicator(false);
                                view.showLoadingVenuesError();
                            }
                        },
                        new Action() {
                            @Override
                            public void run() throws Exception {
                                view.setLoadingIndicator(false);
                            }
                        }
                );
    }

    @Override
    public void openVenueDetails(@NonNull Venue venue) {
        view.showVenueDetails(venue);
    }

    @Override
    public void start() {
        loadVenues();
    }
}
