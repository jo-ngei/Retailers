package com.app.retailers.api.yelp.retailers;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class RetailerDetailFragment extends Fragment implements View.OnClickListener{
    private static final int MAX_WIDTH = 400;
    private static final int MAX_HEIGHT = 300;
    private Retailer retailer;

    @Bind(R.id.retailerImageView)
    ImageView mImageLabel;
    @Bind(R.id.retailerNameTextView)
    TextView mNameLabel;
    @Bind(R.id.cuisineTextView) TextView mCategoriesLabel;
    @Bind(R.id.ratingTextView) TextView mRatingLabel;
    @Bind(R.id.websiteTextView) TextView mWebsiteLabel;
    @Bind(R.id.phoneTextView) TextView mPhoneLabel;
    @Bind(R.id.addressTextView) TextView mAddressLabel;
    @Bind(R.id.saveRetailerButton) TextView mSaveRetailerButton;


    public static RetailerDetailFragment newInstance(Retailer retailer) {
        RetailerDetailFragment retailerDetailFragment = new RetailerDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("retailer", Parcels.wrap(retailer));
        retailerDetailFragment.setArguments(args);
        return retailerDetailFragment;
    }

    public RetailerDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        retailer = Parcels.unwrap(getArguments().getParcelable("retailer"));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_restaurant_detail, container, false);

        ButterKnife.bind(this, view);

        Picasso.with(view.getContext())
                .load(retailer.getImageUrl())
                .resize(MAX_WIDTH, MAX_HEIGHT)
                .centerCrop()
                .into(mImageLabel);

        mNameLabel.setText(retailer.getName());
        mCategoriesLabel.setText(android.text.TextUtils.join(", ", retailer.getCategories()));
        mRatingLabel.setText(Double.toString(retailer.getRating()) + "/5");
        mPhoneLabel.setText(retailer.getPhone());
        mAddressLabel.setText(android.text.TextUtils.join(", ", retailer.getAddress()));

        mWebsiteLabel.setOnClickListener(this);
        mPhoneLabel.setOnClickListener(this);
        mAddressLabel.setOnClickListener(this);

        mSaveRetailerButton.setOnClickListener(this);

        return  view;
    }

    @Override
    public void onClick(View v){
        if (v == mWebsiteLabel){
            Intent webIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse(retailer.getWebsite()));
            startActivity(webIntent);
        }

        if (v == mPhoneLabel){
            Intent phoneIntent = new Intent(Intent.ACTION_DIAL,
                    Uri.parse("tel:" + retailer.getPhone()));
            startActivity(phoneIntent);
        }

        if (v == mAddressLabel){
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:" + retailer.getLatitude()
                    + "," + retailer.getLongitude() + "?q=(" + retailer.getName() + ")"));
            startActivity(mapIntent);
        }

        if (v == mSaveRetailerButton){
//            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//            String uid = user.getUid();
//
//            DatabaseReference restaurantRef = FirebaseDatabase
//                    .getInstance()
//                    .getReference(Constants.FIREBASE_CHILD_RESTAURANTS)
//                    .child(uid);
//
//            DatabaseReference pushRef = restaurantRef.push();
//            String pushId = pushRef.getKey();
//            retailer.setPushId(pushId);
//            pushRef.setValue(retailer);
//
//            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();

        }
    }

}
