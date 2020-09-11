package com.kalu.cynthia.ui.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kalu.cynthia.R;
import com.kalu.cynthia.data_operation.Leaders;
import com.kalu.cynthia.data_operation.ServiceBuilder;
import com.kalu.cynthia.models.SkillLeader;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SkillIqLeadersFragment extends Fragment {



    private SkillIqLeaderViewModel mSkillIqLeaderViewModel = new SkillIqLeaderViewModel();
    private SkillIqLeaderAdapter mSkillIqLeaderAdapter;

    public SkillIqLeadersFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_skill_iq_leaders, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Leaders skillLeaders = ServiceBuilder.leaderBuildService(Leaders.class);
        Call<List<SkillLeader>> sLeaders = skillLeaders.getSkillLeaders();
        sLeaders.enqueue(new Callback<List<SkillLeader>>() {
            @Override
            public void onResponse(Call<List<SkillLeader>> call, Response<List<SkillLeader>> response) {
                if (!response.isSuccessful()){
                    Log.d("skill conn not successful", String.valueOf(response.code()));
                    return;
                }

                mSkillIqLeaderViewModel.setSkillIqLeaders(response.body());

                RecyclerView recyclerView = view.findViewById(R.id.recycler_skills);
                mSkillIqLeaderAdapter = new SkillIqLeaderAdapter(getContext());
                recyclerView.setAdapter(mSkillIqLeaderAdapter);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(linearLayoutManager);

                mSkillIqLeaderViewModel.getSkillIqLeaders().observe(getViewLifecycleOwner(), new Observer<List<SkillLeader>>() {
                    @Override
                    public void onChanged(List<SkillLeader> skillLeaders) {
                        mSkillIqLeaderAdapter.setSkillList(skillLeaders);
                    }
                });



            }

            @Override
            public void onFailure(Call<List<SkillLeader>> call, Throwable t) {

                Log.d("skill failed",t.getMessage());
            }
        });
    }
}
