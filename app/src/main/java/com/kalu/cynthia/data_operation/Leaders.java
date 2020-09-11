package com.kalu.cynthia.data_operation;

import com.kalu.cynthia.models.HourlyLeader;
import com.kalu.cynthia.models.SkillLeader;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Leaders {

    @GET("hours")
    Call<List<HourlyLeader>> getHourlyLeaders();

    @GET("skilliq")
    Call<List<SkillLeader>> getSkillLeaders();
}
