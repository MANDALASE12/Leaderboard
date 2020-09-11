package com.kalu.cynthia.ui.main;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.kalu.cynthia.models.HourlyLeader;

import java.util.List;

public class HourlyLeaderViewModel extends ViewModel {

    private MutableLiveData<List<HourlyLeader>> hlist = new MutableLiveData<>();

    private LiveData<List<HourlyLeader>> mHourlyLeaders = Transformations.map(hlist, new Function<List<HourlyLeader>, List<HourlyLeader>>() {
        @Override
        public List<HourlyLeader> apply(List<HourlyLeader> input) {
            return input;
        }
    });

    public void setHourlyLeaders(List<HourlyLeader> hourlyLeaders) {
        hlist.setValue(hourlyLeaders);
    }

    public LiveData<List<HourlyLeader>> getHourlyLeaders() {
        return mHourlyLeaders;
    }
}
