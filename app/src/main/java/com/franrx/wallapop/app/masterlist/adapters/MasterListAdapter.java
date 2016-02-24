package com.franrx.wallapop.app.masterlist.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.franrx.wallapop.app.R;
import com.franrx.wallapop.app.domain.Result;
import com.franrx.wallapop.app.masterlist.viewholders.MasterListCardViewHolder;
import com.franrx.wallapop.app.masterlist.listeners.RecyclerClickListener;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;


public class MasterListAdapter extends RecyclerView.Adapter<MasterListCardViewHolder> {
    private List<Result> comics;
    private Context context;
    private final RecyclerClickListener mRecyclerListener;

    public MasterListAdapter(Context context, List<Result> comics, RecyclerClickListener recyclerClickListener) {
        this.comics = comics;
        this.context = context;
        this.mRecyclerListener = recyclerClickListener;
    }

    @Override
    public MasterListCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.master_list_item, parent, false);
        return new MasterListCardViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MasterListCardViewHolder holder, final int position) {
        final Result comic = comics.get(position);

        if (position % 2 == 0) {
            setDarkColors(holder);
        } else {
            setWhiteColors(holder);
        }

        holder.name.setText(comic.getTitle());

        //TODO: Picasso Load Image
        String url = comic.getThumbnail().getPath().concat(".").concat(comic.getThumbnail().getExtension());
        Picasso.with(context).load(url).into(holder.photo, new Callback() {
            @Override
            public void onSuccess() {
                holder.photoProgress.setVisibility(View.GONE);
            }

            @Override
            public void onError() {

            }
        });

        holder.cardContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRecyclerListener.onElementClick(position);
            }
        });
    }

    private void setDarkColors(MasterListCardViewHolder holder) {
        holder.cardContainer.setBackgroundResource(R.color.comic_cardview_grey_background);
        holder.name.setTextColor(context.getResources().getColor(R.color.comic_cardview_white_text_color));
    }

    private void setWhiteColors(MasterListCardViewHolder holder) {
        holder.cardContainer.setBackgroundResource(R.color.comic_cardview_white_background);
        holder.name.setTextColor(context.getResources().getColor(R.color.comic_cardview_black_text_color));
    }

    @Override
    public int getItemCount() {
        return comics.size();
    }

    public void setItems(List<Result> comics) {
        this.comics = comics;
    }
}