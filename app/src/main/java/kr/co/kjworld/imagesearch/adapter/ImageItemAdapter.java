package kr.co.kjworld.imagesearch.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import kr.co.kjworld.imagesearch.R;
import kr.co.kjworld.imagesearch.model.response.ImageSearchResponseData;
import kr.co.kjworld.imagesearch.presenter.ImageItemClickListener;

public class ImageItemAdapter extends PagedListAdapter<ImageSearchResponseData.Document, ImageItemAdapter.ImageDataViewHolder> {

    Context mContext;

    public ImageItemAdapter(Context context) {
        super(DIFF_CALLBACK);
        mContext = context;
    }

    @NonNull
    @Override
    public ImageDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.image_item, parent, false);
        return new ImageDataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageDataViewHolder holder, int position) {
        ImageSearchResponseData.Document imageData = getItem(position);
        Glide.with(mContext)
                .load(imageData.getImage_url())
                .into(holder.mImageView);

    }

    private static DiffUtil.ItemCallback<ImageSearchResponseData.Document> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<ImageSearchResponseData.Document>() {
                @Override
                public boolean areItemsTheSame(ImageSearchResponseData.Document oldItem, ImageSearchResponseData.Document newItem) {
                    return oldItem.getImage_url().equals(newItem.getImage_url());
                }

                @Override
                public boolean areContentsTheSame(ImageSearchResponseData.Document oldItem, ImageSearchResponseData.Document newItem) {
                    return oldItem.equals(newItem);
                }
            };


    class ImageDataViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;

        ImageDataViewHolder(View itemView)
        {
            super(itemView);
            mImageView = itemView.findViewById(R.id.item_image_view);
        }

    }
}
