package com.mkfnx.officehoursnearsoft.home;

import com.mkfnx.officehoursnearsoft.data.FoursquareService;
import com.mkfnx.officehoursnearsoft.data.source.VenuesDataSource;
import com.mkfnx.officehoursnearsoft.data.source.VenuesRepository;
import com.mkfnx.officehoursnearsoft.data.source.remote.VenuesRemoteDataSource;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by mkfnx on 20/01/17.
 */

@Module
public class HomeModule {

    private final HomeContract.View view;

    public HomeModule(HomeContract.View view) {
        this.view = view;
    }

    @Provides
    HomeContract.View provideHomeContractView() {
        return view;
    }

    @Provides
    VenuesDataSource provideVenuesRemoteDataSource(FoursquareService foursquareService) {
        return new VenuesRemoteDataSource(foursquareService);
    }

    @Provides
    VenuesRepository provideVenuesRepository(VenuesDataSource venuesDataSource) {
        return new VenuesRepository(venuesDataSource);
    }

    @Provides
    HomeContract.Presenter provideHomeContractPresenter(VenuesRepository venuesRepository, HomeContract.View view, CompositeDisposable compositeDisposable) {
        return new HomePresenter(venuesRepository, view, compositeDisposable);
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }
}
