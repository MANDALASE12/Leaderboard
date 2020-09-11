package com.kalu.cynthia.ui.main;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.kalu.cynthia.models.SkillLeader;

import java.util.List;

public class SkillIqLeaderViewModel extends ViewModel {

    private MutableLiveData<List<SkillLeader>> mMutableLiveData = new MutableLiveData<>();

    private LiveData<List<SkillLeader>> sList = Transformations.map(mMutableLiveData, new Function<List<SkillLeader>, List<SkillLeader>>() {
        @Override
        public List<SkillLeader> apply(List<SkillLeader> input) {
            return input;
        }
    });

    public void setSkillIqLeaders(List<SkillLeader> skillIqLeaders){

        mMutableLiveData.setValue(skillIqLeaders);
    }

    public LiveData<List<SkillLeader>> getSkillIqLeaders(){
        return sList;
    }
}

