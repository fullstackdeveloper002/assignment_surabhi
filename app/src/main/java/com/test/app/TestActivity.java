package com.test.app;

import static com.test.app.ConstantVariables.ConstVar.Image1;
import static com.test.app.ConstantVariables.ConstVar.Image2;
import static com.test.app.ConstantVariables.ConstVar.Image3;
import static com.test.app.ConstantVariables.ConstVar.Image4;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.test.app.adapter.AdapterTest;
import com.test.app.databinding.TestLayoutBinding;
import com.test.app.model.TestModel;

import java.util.ArrayList;

public class TestActivity extends AppCompatActivity {

    private AdapterTest mAdapter;
    private ArrayList<TestModel> mList;
    private TestLayoutBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.test_layout);

        addDataToList();

        mAdapter = new AdapterTest(this, mList);
        binding.recyclerView.setAdapter(mAdapter);

        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) binding.recyclerView
                .getLayoutManager();

        binding.recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            int ydy = 0;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int pos = ((LinearLayoutManager) binding.recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                binding.toolbar.setTitle(mList.get(pos).getSection_name());


                for (int i = 0; i < mList.size(); i++) {
                    TestModel mModel = mList.get(i);
                    if (pos == i) {
                        mModel.setVisible(true);
                    } else {
                        mModel.setVisible(false);
                    }
                }
                mAdapter.notifyDataSetChanged();


            }
        });

    }

    private void addDataToList() {
        mList = new ArrayList<>();
        mList.add(new TestModel("0", "Section 1", Image1, Image2, Image3));
        mList.add(new TestModel("1", "Section 2", Image2, Image3, Image4));
        mList.add(new TestModel("2", "Section 3", Image3, Image4, Image2));
        mList.add(new TestModel("3", "Section 4", Image1, Image3, Image4));
        mList.add(new TestModel("4", "Section 5", Image2, Image1, Image3));
        mList.add(new TestModel("5", "Section 6", Image2, Image1, Image3));
        mList.add(new TestModel("6", "Section 7", Image2, Image1, Image3));
        mList.add(new TestModel("7", "Section 8", Image2, Image1, Image3));
    }
}
