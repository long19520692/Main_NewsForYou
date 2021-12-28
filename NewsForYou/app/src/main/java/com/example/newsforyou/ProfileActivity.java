package com.example.newsforyou;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.newsforyou.Class.CheckValid;
import com.example.newsforyou.Class.User;
import com.example.newsforyou.Fragments.HomeFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Calendar;

public class ProfileActivity extends AppCompatActivity {

    private ImageButton btnBack;
    private TextView tvProfile;
    private ImageView ivAvatar;
    private Button btnChangeAvatar;
    private EditText edtUsername;
    private EditText edtEmail;
    private EditText edtBirthday;
    private Button btnSave;
    private Button btnChangePassword;

    StorageReference storageReference;
    StorageReference avatarRef;

    private FirebaseUser user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_profile);

        storageReference = FirebaseStorage.getInstance().getReference();
        initUserInformation();

        avatarRef = storageReference.child(user.getEmail() + ".jpg");
        avatarRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(ivAvatar);
            }
        });

        initUI();
        initListener();
    }

    private void initUI() {
        btnBack = (ImageButton) findViewById(R.id.btn_back);
        tvProfile = (TextView) findViewById(R.id.tv_profile);
        ivAvatar = (ImageView) findViewById(R.id.iv_avatar);
        btnChangeAvatar = (Button) findViewById(R.id.btn_change_avt);
        edtUsername = (EditText) findViewById(R.id.edt_username);
        edtEmail = (EditText) findViewById(R.id.edt_email);
        edtBirthday = (EditText) findViewById(R.id.edt_birthday);
        btnSave = (Button) findViewById(R.id.btn_save_change);
        btnChangePassword = (Button) findViewById(R.id.btn_change_password);

        showProfile();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void initListener() {
        btnChangeAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mở Gallery
                Intent openGalleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(openGalleryIntent, 1000);

            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storageReference = FirebaseStorage.getInstance().getReference();

                updateProfile();
            }
        });

        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth auth = FirebaseAuth.getInstance();
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String emailAddress = user.getEmail();

                auth.sendPasswordResetEmail(emailAddress)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(ProfileActivity.this, "Đã gửi mail đặt lại mật khẩu.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000) {
            if (resultCode == Activity.RESULT_OK) {
                try {
                    Uri imageUri = data.getData();
                    final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                    //final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                    // ivAvatar.setImageBitmap(selectedImage);

                    uploadImageToFirebase(imageUri);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(ProfileActivity.this, "Lỗi!", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    private void uploadImageToFirebase(Uri imageUri) {
        StorageReference fileRef = storageReference.child(user.getEmail() + ".jpg");
        fileRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.get().load(uri).into(ivAvatar);
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ProfileActivity.this, "Lưu thất bại", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initUserInformation() {
        user = FirebaseAuth.getInstance().getCurrentUser();
    }

    private void showProfile() {
        if (user == null) {
            return;
        }

        String name = user.getDisplayName();
        String email = user.getEmail();

        edtUsername.setText(name);
        edtEmail.setText(email);
    }

    private void updateProfile() {
        String newName = edtUsername.getText().toString().trim();

        if(!CheckValid.isValidName(newName)){
            Toast.makeText(ProfileActivity.this, "Tên người dùng không được để trống",
                    Toast.LENGTH_SHORT).show();
        } else {
        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(newName)
                .build();

        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task1) {
                        if (task1.isSuccessful()) {
                            Toast.makeText(ProfileActivity.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ProfileActivity.this, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        }
    }
}
