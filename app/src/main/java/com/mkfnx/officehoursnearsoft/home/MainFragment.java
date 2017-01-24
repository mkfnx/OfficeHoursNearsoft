package com.mkfnx.officehoursnearsoft.home;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.mkfnx.officehoursnearsoft.OfficeHoursNearsoftApp;
import com.mkfnx.officehoursnearsoft.R;
import com.mkfnx.officehoursnearsoft.data.Venue;
import com.mkfnx.officehoursnearsoft.details.VenueDetailsActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements HomeContract.View {

    private static final String TAG = MainFragment.class.getSimpleName();

    @Inject
    HomeContract.Presenter presenter;
    @Inject
    RequestManager glideRequestManager;

    private VenuesAdapter venuesAdapter;
    private VenuesAdapter.VenueItemListener venueItemListener;

    @BindView(R.id.venues_recyclerview)
    RecyclerView venuesRecyclerView;
    @BindView(R.id.progressbar)
    ProgressBar progressBar;

    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    /*
     * Fragment Lifecycle
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        OfficeHoursNearsoftApp app = (OfficeHoursNearsoftApp) getActivity().getApplication();

        DaggerHomeComponent.builder()
                .applicationComponent( app.getApplicationComponent() )
                .homeModule(new HomeModule(this))
                .build()
                .inject(this);

        venueItemListener = new VenuesAdapter.VenueItemListener() {
            @Override
            public void onVenueClick(Venue venueClicked) {
                Log.d(TAG, "onVenueClick: " + venueClicked.getName());
                presenter.openVenueDetails(venueClicked);
            }
        };

        venuesAdapter = new VenuesAdapter(new ArrayList<Venue>(0), venueItemListener, glideRequestManager);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);

        venuesRecyclerView.setAdapter(venuesAdapter);
        venuesRecyclerView.setLayoutManager(
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false)
        );

        return view;
    }

    /*
     * View contract
     */
    @Override
    public void setLoadingIndicator(boolean active) {
        progressBar.setVisibility(active ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showVenues(List<Venue> venues) {
        venuesAdapter.setVenues(venues);
        venuesAdapter.notifyDataSetChanged();
    }

    @Override
    public void showVenueDetails(Venue venue) {
        Intent intent = new Intent(getContext(), VenueDetailsActivity.class);
        intent.putExtra(VenueDetailsActivity.EXTRA_SELECTED_VENUE, venue);
        startActivity(intent);
    }

    @Override
    public void showLoadingVenuesError() {
        Snackbar.make(getView(), getString(R.string.error_load_venues), Snackbar.LENGTH_LONG).show();
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
