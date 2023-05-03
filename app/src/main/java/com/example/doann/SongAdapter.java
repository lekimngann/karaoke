package com.example.doann;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doann.DetailActivity;
import com.example.doann.R;
import com.example.doann.Song;

import java.util.ArrayList;
import java.util.List;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongViewHoder> implements Filterable  {

    private List<Song> mListSong;

    private List<Song> mFullSongList;
    private Context mContext;

    public SongAdapter(Context context, List<Song> mListSong) {

        this.mContext = context;
        this.mListSong = mListSong;
        this.mFullSongList = mListSong;
    }



    @NonNull
    @Override
    public SongViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_song, parent,false);

        return new SongViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SongViewHoder holder, int position) {
        final Song song = mListSong.get(position);
        if(song == null){
            return;
        }
        holder.imgAvatar.setImageResource(song.getResourceId());
        holder.tvName.setText(song.getName());
        holder.tvLoi.setText(song.getLoi());

        holder.layoutitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickGoToDetail(song);
            }
        });
    }

    private void onClickGoToDetail(Song song){
        Intent intent = new Intent(mContext, DetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_song", song);
        intent.putExtras(bundle);
        mContext.startActivity(intent);


    }

    @Override
    public int getItemCount() {
        if (mListSong != null){
            return mListSong.size();
        }
        return 0;
    }


    public class SongViewHoder extends RecyclerView.ViewHolder{

        private RelativeLayout layoutitem;
        private ImageView imgAvatar;
        private TextView tvName;
        private TextView tvLoi;

        public SongViewHoder(@NonNull View itemView) {
            super(itemView);
            layoutitem = itemView.findViewById(R.id.layout_item);
            imgAvatar = itemView.findViewById(R.id.img_avatar);
            tvName = itemView.findViewById(R.id.tv_name);
            tvLoi = itemView.findViewById(R.id.tv_loi);
        }

    }
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String strSearch = constraint.toString();
                if (strSearch.isEmpty()){
                    mListSong = mFullSongList;
                }else {
                    List<Song> list = new ArrayList<>();
                    for(Song song : mFullSongList) {
                        if (song.getName().toLowerCase().contains(strSearch.toLowerCase())){
                            list.add(song);
                        }
                    }
                    mListSong = list;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mListSong;

                return filterResults;

            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mListSong = (List<Song>) results.values;
                notifyDataSetChanged();
            }
        };
    }

}
