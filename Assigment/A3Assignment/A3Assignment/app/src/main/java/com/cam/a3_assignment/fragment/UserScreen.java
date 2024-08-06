package com.cam.a3_assignment.fragment;

import static androidx.core.content.ContextCompat.checkSelfPermission;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.cam.a3_assignment.R;
import com.cam.a3_assignment.Signup;
import com.cam.a3_assignment.model.Account;
import com.cam.a3_assignment.model.Response;
import com.cam.a3_assignment.services.HttpReq;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserScreen#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserScreen extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public UserScreen() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_user_screen.
     */
    // TODO: Rename and change types and number of parameters
    public static UserScreen newInstance(String param1, String param2) {
        UserScreen fragment = new UserScreen();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private ImageView avatar;
    private File file;
    private ActivityResultLauncher<Intent> getImage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_screen, container, false);
        Account account = (Account) getArguments().getSerializable("account");
        HttpReq httpReq = new HttpReq();

        avatar = view.findViewById(R.id.avatar);
        Button btnUpdate = view.findViewById(R.id.btnUpdateInfo);
        EditText edtNewEmail = view.findViewById(R.id.txtNewEmail);
        EditText edtNewName = view.findViewById(R.id.txtNewUsername);

        Glide.with(getContext()).load(account.getAvatar()).centerCrop().into(avatar);
        edtNewEmail.setText(account.getEmail());
        edtNewName.setText(account.getName());

        avatar.setOnClickListener(v -> chooseImage());

        System.out.println(account.getAvatar());

        btnUpdate.setOnClickListener(v -> {
            String newEmail = edtNewEmail.getText().toString();
            String newName = edtNewName.getText().toString();
            if(!(newName.isEmpty() || newEmail.isEmpty())){
                Map<String, RequestBody> requestBodyMap = new HashMap<>();
                requestBodyMap.put("username", getRequestBody(newName));
                requestBodyMap.put("email", getRequestBody(newEmail));
                MultipartBody.Part multipartbody;
                if(file != null){
                    RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), file);
                    multipartbody = MultipartBody.Part.createFormData("avatar", file.getName(), requestFile);
                }else{
                    multipartbody = null;
                }
                httpReq.callApi().updateAccount(account.getId(), requestBodyMap, multipartbody).enqueue(responseUpdate);
            }else{
                Toast.makeText(getContext(), "Thiếu Thông Tin", Toast.LENGTH_SHORT).show();
            }
        });

        getImage = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), o -> {
            if (o.getResultCode() == Activity.RESULT_OK){
                Intent data = o.getData();
                Uri imagePath = data.getData();
                file = createFileFromUri(imagePath, "avatar");
                Glide.with(getContext())
                        .load(file)
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true)
                        .into(avatar);
            }
        });
        return view;
    }

    private RequestBody getRequestBody(String input){
        return RequestBody.create(MediaType.parse("multipart/fom-data"), input);
    }

    private void chooseImage(){
        if (getContext().checkSelfPermission(android.Manifest.permission.READ_MEDIA_IMAGES) == PackageManager.PERMISSION_GRANTED){
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            getImage.launch(intent);
        }else{
            requestPermissions(new String[]{Manifest.permission.READ_MEDIA_IMAGES}, 1);
        }
    }



    private File createFileFromUri(Uri path, String name){
        File _file = new File(getContext().getCacheDir() , name+".png");
        try {
            InputStream in = getContext().getContentResolver().openInputStream(path);
            OutputStream out = new FileOutputStream(_file);
            byte[] buff = new byte[1024];
            int len;
            while ((len = in.read(buff))>0){
                out.write(buff, 0, len);
            }
            in.close();
            out.close();
            return _file;
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return null;
    }

    Callback<Response<Account>> responseUpdate = new Callback<Response<Account>>() {
        @Override
        public void onResponse(Call<Response<Account>> call, retrofit2.Response<Response<Account>> response) {
            if(response.isSuccessful()){
                if(response.body().getStatus() == 200){
                    Toast.makeText(getContext(), "Đổi Thành Công", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(), "Đổi Thành Bại", Toast.LENGTH_SHORT).show();
                }
            }
        }

        @Override
        public void onFailure(Call<Response<Account>> call, Throwable throwable) {

        }
    };
}