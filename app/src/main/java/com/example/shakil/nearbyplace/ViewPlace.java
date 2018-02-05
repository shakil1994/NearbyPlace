package com.example.shakil.nearbyplace;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.shakil.nearbyplace.Common.Common;
import com.example.shakil.nearbyplace.Model.PlaceDetail;
import com.example.shakil.nearbyplace.Remote.IGoogleAPIService;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewPlace extends AppCompatActivity {

    ImageView photo;
    RatingBar ratingBar;
    TextView place_open_hour, place_address, place_name;
    Button btn_show_map;

    IGoogleAPIService mService;

    PlaceDetail mPlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_place);

        mService = Common.getGoogleAPIService();

        photo = findViewById(R.id.photo);
        ratingBar = findViewById(R.id.ratingBar);
        place_open_hour = findViewById(R.id.place_open_hour);
        place_address = findViewById(R.id.place_address);
        place_name = findViewById(R.id.place_name);
        btn_show_map = findViewById(R.id.btn_show_map);

        //Empty all view
        place_name.setText("");
        place_address.setText("");
        place_open_hour.setText("");


        btn_show_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mPlace.getResult().getUrl()));
                startActivity(mapIntent);
            }
        });

        //Photo
        if (Common.currentResult.getPhotos() != null && Common.currentResult.getPhotos().length > 0){

            //because getPhoto() is array so we will take first time
            Picasso.with(this).load(getPhotoOfPlace(Common.currentResult.getPhotos()[0].getPhoto_reference(), 1000))
                    .placeholder(R.drawable.ic_image_black_24dp)
                    .error(R.drawable.ic_error_black_24dp)
                    .into(photo);
        }

        //Rating
        if (Common.currentResult.getRating() != null && !TextUtils.isEmpty(Common.currentResult.getRating())){
            ratingBar.setRating(Float.parseFloat(Common.currentResult.getRating()));
        }
        else {
            ratingBar.setVisibility(View.GONE);
        }

        //Opening_hours
        if (Common.currentResult.getOpening_hours() != null){
            place_open_hour.setText("Open now : "+Common.currentResult.getOpening_hours().getOpen_now());
        }
        else {
            place_open_hour.setVisibility(View.GONE);
        }

        //User service to fetch address and name
        mService.getDetailPlace(getPlaceDetailUrl(Common.currentResult.getPlace_id())).enqueue(new Callback<PlaceDetail>() {
            @Override
            public void onResponse(Call<PlaceDetail> call, Response<PlaceDetail> response) {
                mPlace = response.body();
                place_address.setText(mPlace.getResult().getFormatted_address());
                place_name.setText(mPlace.getResult().getName());
            }

            @Override
            public void onFailure(Call<PlaceDetail> call, Throwable t) {

            }
        });

    }

    private String getPlaceDetailUrl(String place_id) {
        StringBuilder url = new StringBuilder("https://maps.googleapis.com/maps/api/place/details/json");
        url.append("?placeid="+place_id);
        url.append("&key="+getResources().getString(R.string.browser_key));
        return url.toString();
    }

    private String getPhotoOfPlace(String photo_reference, int maxWidth) {
        StringBuilder url = new StringBuilder("https://maps.googleapis.com/maps/api/place/photo");
        url.append("?maxwidth="+maxWidth);
        url.append("&photoreference="+photo_reference);
        url.append("&key="+getResources().getString(R.string.browser_key));
        return url.toString();
    }


}
