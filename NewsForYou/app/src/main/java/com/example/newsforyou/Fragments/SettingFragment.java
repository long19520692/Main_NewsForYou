package com.example.newsforyou.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.newsforyou.Class.Like;
import com.example.newsforyou.ProfileActivity;
import com.example.newsforyou.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class SettingFragment extends Fragment {

    private View mView;
    private TextView tvOption;
    private ImageView ivAvatar;
    private TextView tvName;
    private ImageView ivView;
    private ImageView ivDownload;
    private ImageView ivSave;
    private ImageView ivReport;
    private TextView tvHistory;
    private TextView tvDownload;
    private TextView tvSave;
    private TextView tvReport;
    private TextView tvSetting;
    private ImageView ivNotification;
    private ImageView ivTextSize;
    private TextView tvNotification;
    private TextView tvTextSize;
    private TextView tvLogout;

    StorageReference storageReference;
    StorageReference avatarRef;

    private FirebaseUser user;
    private Like like;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_setting, container, false);

        storageReference = FirebaseStorage.getInstance().getReference();
        user = FirebaseAuth.getInstance().getCurrentUser();

        avatarRef = storageReference.child(user.getEmail() + ".jpg");
        avatarRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(ivAvatar);
            }
        });


        initUI();
        initListener();

        initName();

        return  mView;
    }

    public void initUI() {
        ivAvatar = (ImageView) mView.findViewById(R.id.iv_avatar_setting);
        tvOption = (TextView) mView.findViewById(R.id.tv_option);
        tvName = (TextView) mView.findViewById(R.id.tv_name_setting);

        tvHistory = (TextView) mView.findViewById(R.id.tv_history_setting);
        tvDownload = (TextView) mView.findViewById(R.id.tv_download_setting);
        tvSave = (TextView) mView.findViewById(R.id.tv_like_setting);
        tvReport = (TextView) mView.findViewById(R.id.tv_report_setting);
        ivSave = (ImageView) mView.findViewById(R.id.iv_like_setting);
        ivView = (ImageView) mView.findViewById(R.id.iv_history_setting);
        ivDownload = (ImageView) mView.findViewById(R.id.iv_download_setting);
        ivReport = (ImageView) mView.findViewById(R.id.iv_report_setting);

        tvSetting = (TextView) mView.findViewById(R.id.tv_setting_setting);
        tvNotification = (TextView) mView.findViewById(R.id.tv_notification_setting);
        tvTextSize = (TextView) mView.findViewById(R.id.tv_text_size_setting);
        tvLogout = (TextView) mView.findViewById(R.id.tv_logout_setting);
        ivNotification = (ImageView) mView.findViewById(R.id.iv_notification_setting);
        ivTextSize = (ImageView) mView.findViewById(R.id.iv_text_size_setting);

    }

    public void initListener() {
        ivAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProfileActivity.class);
                startActivity(intent);
            }
        });

        tvHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        tvDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        tvReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        tvNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        tvTextSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        tvLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void initName() {
        if (user == null) {
            return;
        }

        String name = user.getDisplayName();

        tvName.setText(name);
    }
}
