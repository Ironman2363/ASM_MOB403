package com.example.ph25533_asm.FRAGMENT;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ph25533_asm.ADAPTER.SearchAdapter;
import com.example.ph25533_asm.DAO.FoodDao;
import com.example.ph25533_asm.MODEL.Product;
import com.example.ph25533_asm.R;
import com.example.ph25533_asm.UNTIL.Server;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment {

    private EditText ed_search;
    private RecyclerView recyclerView;
    private ArrayList<Product> list = new ArrayList<>();
    private FoodDao dao;
    private SearchAdapter adapter;

    public SearchFragment() {
        // Required empty public constructor
    }


    public static SearchFragment newInstance() {
        SearchFragment fragment = new SearchFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findviewbyid(view);
        search();
    }

    private void findviewbyid(View view){
        ed_search = view.findViewById(R.id.ed_search);
        recyclerView = view.findViewById(R.id.recycelview_search);
        dao = new FoodDao();
        adapter= new SearchAdapter(getContext());
    }

    private void search(){
        ed_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                String value = ed_search.getText().toString().trim();
                if (actionId == EditorInfo.IME_ACTION_SEARCH){
                    dao.getsearch(getContext(), Server.linksearchproduct,value, adapter);
                    LinearLayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false);
                    recyclerView.setLayoutManager(manager);
                    recyclerView.setAdapter(adapter);
                }

                return false;
            }
        });
    }
}