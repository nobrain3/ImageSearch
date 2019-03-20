package kr.co.kjworld.imagesearch.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;
import kr.co.kjworld.imagesearch.R;
import kr.co.kjworld.imagesearch.model.response.ImageSearchResponseData;
import kr.co.kjworld.imagesearch.mycontract.ImageItemClickListener;

public class ImageSearchAdapter extends RecyclerView.Adapter<ImageSearchAdapter.ImageDataViewHolder> {
    Context mContext;
    ArrayList<ImageSearchResponseData.Document> mImageList;
    ImageItemClickListener mImageItemClickListener;

    public ImageSearchAdapter(Context context, ArrayList<ImageSearchResponseData.Document> imageList, ImageItemClickListener imageItemClickLister) {
        mContext = context;
        mImageList = imageList;
        mImageItemClickListener = imageItemClickLister;
    }

    @Override
    public ImageDataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.image_item, parent, false);
        return new ImageDataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ImageDataViewHolder holder, int position) {
        ImageSearchResponseData.Document imageData = mImageList.get(position);
        Glide.with(mContext)
                .load(imageData.getImage_url())
                .into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mImageList.size();
    }

    class ImageDataViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;

        ImageDataViewHolder(View itemView)
        {
            super(itemView);
            mImageView = itemView.findViewById(R.id.item_image_view);
        }

    }
}
