package com.mkfnx.officehoursnearsoft.home;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.mkfnx.officehoursnearsoft.R;
import com.mkfnx.officehoursnearsoft.data.Venue;
import com.mkfnx.officehoursnearsoft.data.VenueFeaturedPhotos;
import com.mkfnx.officehoursnearsoft.data.VenuePhoto;
import com.mkfnx.officehoursnearsoft.util.ActivityUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mkfnx on 13/01/17.
 */

public class VenuesAdapter extends RecyclerView.Adapter<VenuesAdapter.ViewHolder> {

    private List<Venue> venues;
    private VenueItemListener venueItemListener;
    private RequestManager glideRequestManager;

    public VenuesAdapter(List<Venue> venues, VenueItemListener venueItemListener, RequestManager glideRequestManager) {
        setVenues(venues);
        this.venueItemListener = venueItemListener;
        this.glideRequestManager = glideRequestManager;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private View view;

        @BindView(R.id.venue_imageview)
        ImageView venueImageView;
        @BindView(R.id.venue_textview)
        TextView venueTextView;
        @BindView(R.id.venue_cardview)
        CardView venueCardView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.view = itemView;
        }
    }

    public void setVenues(List<Venue> venues) {
        this.venues = venues;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_venue, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Venue venue = venues.get(position);

        holder.venueTextView.setText(venue.getName());
        loadVenueImage(venue, holder.venueImageView);
        holder.venueCardView.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        venueItemListener.onVenueClick(venue);
                    }
                }
        );
    }

    @Override
    public int getItemCount() {
        return venues.size();
    }

    public interface VenueItemListener {
        void onVenueClick(Venue venueClicked);
    }

    private void loadVenueImage(Venue venue, ImageView venueImageView) {
        if ( venue.getPhotoUrl() != null ) {
            glideRequestManager
                    .load(venue.getPhotoUrl())
                    .placeholder(R.mipmap.ic_launcher)
                    .into(venueImageView);
        }
    }
}
