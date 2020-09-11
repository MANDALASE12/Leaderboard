package com.kalu.cynthia.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kalu.cynthia.R;
import com.kalu.cynthia.data_operation.Leaders;
import com.kalu.cynthia.data_operation.ServiceBuilder;
import com.kalu.cynthia.models.HourlyLeader;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A placeholder fragment containing a simple view.
 */
public class HourlyLeadersFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private HourlyLeaderViewModel mHourlyLeaderViewModel = new HourlyLeaderViewModel();
    private HourlyLeaderAdapter mHourlyLeaderAdapter;

    public HourlyLeadersFragment() {
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_hourly_leaders, container, false);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Leaders hourlyLeaders = ServiceBuilder.leaderBuildService(Leaders.class);
        Call<List<HourlyLeader>> hLeaders = hourlyLeaders.getHourlyLeaders();
        hLeaders.enqueue(new Callback<List<HourlyLeader>>() {
            @Override
            public void onResponse(Call<List<HourlyLeader>> call, Response<List<HourlyLeader>> response) {
                if (!response.isSuccessful()){
                    Log.d("not successful", String.valueOf(response.code()));
                    return;
                }


                mHourlyLeaderViewModel.setHourlyLeaders(response.body());

                mHourlyLeaderAdapter = new HourlyLeaderAdapter(getContext());

                mHourlyLeaderViewModel.getHourlyLeaders().observe(getViewLifecycleOwner(), new Observer<List<HourlyLeader>>() {
                    @Override
                    public void onChanged(List<HourlyLeader> hourlyLeaders) {
                        mHourlyLeaderAdapter.setListValues(hourlyLeaders);
                    }
                });


                RecyclerView recyclerView = view.findViewById(R.id.recycler_hours);
                recyclerView.setAdapter(mHourlyLeaderAdapter);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(linearLayoutManager);

            }

            @Override
            public void onFailure(Call<List<HourlyLeader>> call, Throwable t) {

                Log.d("connection failed", t.getMessage());
            }
        });
    }
}
