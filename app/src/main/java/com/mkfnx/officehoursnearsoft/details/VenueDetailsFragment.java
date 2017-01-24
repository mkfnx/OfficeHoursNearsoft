package com.mkfnx.officehoursnearsoft.details;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mkfnx.officehoursnearsoft.OfficeHoursNearsoftApp;
import com.mkfnx.officehoursnearsoft.R;
import com.mkfnx.officehoursnearsoft.data.Venue;
import com.mkfnx.officehoursnearsoft.util.ActivityUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class VenueDetailsFragment extends Fragment implements DetailsContract.View {

    private static final String ARG_SELECTED_VENUE = "SELECTED_VENUE";

    @Inject
    DetailsContract.Presenter presenter;

    @BindView(R.id.venue_imageview)
    ImageView venueImageView;
    @BindView(R.id.venue_name_textview)
    TextView venueTextView;
    @BindView(R.id.venue_description_textview)
    TextView venueDescriptionTextView;

    public VenueDetailsFragment() {
        // Required empty public constructor
    }

    public static VenueDetailsFragment newInstance(Venue venue) {
        Bundle args = new Bundle();
        args.putParcelable(ARG_SELECTED_VENUE, venue);

        VenueDetailsFragment fragment = new VenueDetailsFragment();
        fragment.setArguments(args);

        return fragment;
    }

    /*
     * Lifecycle
     */

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        OfficeHoursNearsoftApp app = (OfficeHoursNearsoftApp) getActivity().getApplication();

        Venue venue = getArguments().getParcelable(ARG_SELECTED_VENUE);

        DaggerDetailsComponent.builder()
                .applicationComponent( app.getApplicationComponent() )
                .detailsModule(new DetailsModule(this, venue))
                .build()
                .inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_details, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }

    /*
     * View contract
     */
//    @Override
//    public void setLoadingIndicator(boolean active) {
//    }


    @Override
    public void showVenueDetails(Venue venue) {
        venueTextView.setText(venue.getName());
        venueDescriptionTextView.setText(venue.getUrl());

        String venuePhotoUrl = ActivityUtils.buildVenuePhotoUrl(venue);

        if ( venuePhotoUrl != null ) {
            Glide.with(this)
                    .load(venuePhotoUrl)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(venueImageView);
        }
    }

    @Override
    public void setPresenter(DetailsContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
