package com.cam.a3_assignment.fragment;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.cam.a3_assignment.Adapter.SearchAdapter;
import com.cam.a3_assignment.R;
import com.cam.a3_assignment.model.Product;
import com.cam.a3_assignment.model.Response;
import com.cam.a3_assignment.services.HttpReq;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchScreen#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchScreen extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SearchScreen() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_search_screen.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchScreen newInstance(String param1, String param2) {
        SearchScreen fragment = new SearchScreen();
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

    private RecyclerView searchList;
    private EditText edtSearch;
    private HttpReq httpReq;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_screen, container, false);
        searchList = view.findViewById(R.id.searchList);
        edtSearch = view.findViewById(R.id.txtSearch);
        httpReq = new HttpReq();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        searchList.setLayoutManager(linearLayoutManager);

        edtSearch.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH){
                InputMethodManager imm = (InputMethodManager) ((Activity) getContext()).getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                httpReq.callApi().searchProduct(edtSearch.getText().toString()).enqueue(responseSearch);
                return true;
            }
            return false;
        });
        return view;
    }

    Callback<Response<ArrayList<Product>>> responseSearch = new Callback<Response<ArrayList<Product>>>() {
        @Override
        public void onResponse(Call<Response<ArrayList<Product>>> call, retrofit2.Response<Response<ArrayList<Product>>> response) {
            if(response.isSuccessful()){
                if (response.body().getStatus() == 200){
                    searchList.setAdapter(new SearchAdapter(getContext(), response.body().getData()));
                }
            }
        }

        @Override
        public void onFailure(Call<Response<ArrayList<Product>>> call, Throwable throwable) {

        }
    };

}