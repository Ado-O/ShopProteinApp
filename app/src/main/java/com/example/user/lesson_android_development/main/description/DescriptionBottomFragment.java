package com.example.user.lesson_android_development.main.description;

import android.app.Dialog;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.bumptech.glide.Glide;
import com.example.user.lesson_android_development.R;
import com.example.user.lesson_android_development.data.DescriptionList;
import com.example.user.lesson_android_development.data.Product;
import com.example.user.lesson_android_development.databinding.DescriptionBottomSheetBinding;
import com.example.user.lesson_android_development.main.shop.ShopActivity;
import com.example.user.lesson_android_development.main.shop.ShopViewModel;
import com.example.user.lesson_android_development.util.RecyclerViewClickListener;
import com.example.user.lesson_android_development.util.ViewModelFactory;

public class DescriptionBottomFragment extends BottomSheetDialogFragment {

    private static final String TAG = DescriptionBottomFragment.class.getSimpleName();

    private static final String PRODUCT_ITEM = "product";
    private DescriptionBottomSheetBinding mDescriptionBottomSheetBinding;
    private int mNumber = 1;
    boolean mSelected;
    Product product;

    public static DescriptionBottomFragment newInstance(Product product) {

        DescriptionBottomFragment bottomSheetFragment = new DescriptionBottomFragment();
        Bundle b = new Bundle();
        b.putParcelable(PRODUCT_ITEM, product);

        bottomSheetFragment.setArguments(b);

        return bottomSheetFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mDescriptionBottomSheetBinding = DescriptionBottomSheetBinding.inflate(inflater, container, false);

        product = (Product) getArguments().getParcelable(PRODUCT_ITEM);

        getDataSetup();
        getQuantitySetup();

        return mDescriptionBottomSheetBinding.getRoot();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        BottomSheetDialog dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);

        dialog.setOnShowListener(dialogInterface -> {
            BottomSheetDialog d = (BottomSheetDialog) dialogInterface;

            FrameLayout bottomSheet = (FrameLayout) d.findViewById(android.support.design.R.id.design_bottom_sheet);
            BottomSheetBehavior bsb = BottomSheetBehavior
                    .from(bottomSheet);

            bsb.setState(BottomSheetBehavior.STATE_EXPANDED);

        });

        return dialog;
    }

    public void getDataSetup(){

        Glide.with(getActivity())
                .load(product.getPictures())
                .into(mDescriptionBottomSheetBinding.ivProduct);

        mDescriptionBottomSheetBinding.tvTitle.setText(product.getTitle());
        mDescriptionBottomSheetBinding.tvPrice.setText(product.getPrice());
        mDescriptionBottomSheetBinding.tvDiscount.setText(product.getDiscounte());
    }

    public void getQuantitySetup() {
        //TODO plus minus logic
        mDescriptionBottomSheetBinding.number.setText("" + mNumber);

        //plus
        mDescriptionBottomSheetBinding.plus.setOnClickListener(v -> {
            mNumber = mNumber + 1;
            if (mNumber != 1) {
                mDescriptionBottomSheetBinding.setSelected(mSelected = true);
            }
            mDescriptionBottomSheetBinding.number.setText("" + mNumber);

        });

        //minus
        mDescriptionBottomSheetBinding.minus.setOnClickListener(v -> {
            if (mNumber != 1) {
                mNumber = mNumber - 1;
                mDescriptionBottomSheetBinding.setSelected(mSelected = true);
            }else {
                mDescriptionBottomSheetBinding.setSelected(mSelected = false);
            }

            mDescriptionBottomSheetBinding.number.setText("" + mNumber);
        });
    }
}
