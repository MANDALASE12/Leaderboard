package com.kalu.cynthia.ui.main;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kalu.cynthia.R;
import com.kalu.cynthia.models.SkillLeader;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SkillIqLeaderAdapter extends RecyclerView.Adapter<SkillIqLeaderAdapter.Leader> {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<SkillLeader> mSkillLeaders = new ArrayList<>();

    public SkillIqLeaderAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public Leader onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View leader = mLayoutInflater.inflate(R.layout.skill_leader, parent, false);
        return new Leader(leader);
    }

    @Override
    public void onBindViewHolder(@NonNull Leader holder, int position) {

        //we will set the data to the screen here
        String uri = mSkillLeaders.get(position).getBadgeUrl();
        Uri imgUri = Uri.parse(uri);

        Picasso.get().load(imgUri).into(holder.img);

        holder.name.setText(mSkillLeaders.get(position).getName());
        holder.score_country.setText(mSkillLeaders.get(position).getScore() + " Skill Iq Score ,"
            + mSkillLeaders.get(position).getCountry());

    }

    @Override
    public int getItemCount() {
        return mSkillLeaders.size();
    }

    public void setSkillList(List<SkillLeader> skillLeaders) {
        mSkillLeaders = skillLeaders;

    }

    public class Leader extends RecyclerView.ViewHolder {
        ImageView img;
        TextView name;
        TextView score_country;

        public Leader(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.img_skills);
            name = itemView.findViewById(R.id.txt_name_skills);
            score_country = itemView.findViewById(R.id.txt_skills_country);
        }
    }
}
