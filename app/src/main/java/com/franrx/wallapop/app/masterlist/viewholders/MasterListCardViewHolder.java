package com.franrx.wallapop.app.masterlist.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.franrx.wallapop.app.R;

public class MasterListCardViewHolder extends RecyclerView.ViewHolder {
    public ImageView photo;
    public TextView name;
    public ProgressBar photoProgress;
    public LinearLayout cardContainer;

    public MasterListCardViewHolder(View itemView) {
        super(itemView);
        cardContainer = (LinearLayout) itemView.findViewById(R.id.card_container);
        photoProgress = (ProgressBar) itemView.findViewById(R.id.photo_progress);
        photo = (ImageView) itemView.findViewById(R.id.photo);
        name = (TextView) itemView.findViewById(R.id.name);
    }
}