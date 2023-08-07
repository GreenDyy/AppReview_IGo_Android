package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.igo.R;

import java.util.List;

import Model.model_hinhanhitem;


public class PhotoAdapter extends PagerAdapter {

    private Context context;
    private List<model_hinhanhitem> photoList;

    public PhotoAdapter(Context context, List<model_hinhanhitem> photoList) {
        this.context = context;
        this.photoList = photoList;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_photo, container, false);
        ImageView imgPhoto = view.findViewById(R.id.img_photo);

        model_hinhanhitem photo = photoList.get(position);
        if(photo != null)
        {
            Glide.with(context).load(photo.getImage()).into(imgPhoto);
        }
        //add view to view group
        container.addView(view);

        return view;
    }

    @Override
    public int getCount() {
        if(photoList != null)
            return photoList.size();
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        //Remove view
        container.removeView((View) object);
    }
}
