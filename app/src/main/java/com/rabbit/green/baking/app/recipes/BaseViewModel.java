package com.rabbit.green.baking.app.recipes;

import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.text.TextUtils;
import android.widget.ImageView;

import com.rabbit.green.baking.app.R;
import com.squareup.picasso.Picasso;

public class BaseViewModel extends BaseObservable {
    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String imageUrl) {
        if (TextUtils.isEmpty(imageUrl)) {
            imageView.setImageResource(R.drawable.recipe_background);
        } else {
            Picasso.get().load(imageUrl)
                    .error(R.drawable.recipe_background)
                    .placeholder(R.drawable.recipe_background)
                    .into(imageView);
        }
    }
}
