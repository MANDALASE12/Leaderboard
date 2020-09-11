package com.kalu.cynthia.ui.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.kalu.cynthia.R;
import com.kalu.cynthia.data_operation.ServiceBuilder;
import com.kalu.cynthia.data_operation.Submit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConfirmFragmet extends DialogFragment {

    ImageView cancel;
    Button post;

    public ConfirmFragmet() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_confirm_fragmet, container, false);


        Bundle getDetails = getArguments();

        String firstName = getDetails.getString("fname");
        String lastName = getDetails.getString("lname");
        String emailAddress = getDetails.getString("eaddress");
        String projectLink = getDetails.getString("plink");

        post =  view.findViewById(R.id.btn_post);
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Submit submit = ServiceBuilder.submitBuildService(Submit.class);
                Call<Void> post = submit.submitProject(firstName, lastName, emailAddress, projectLink);

                post.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            SuccessfulDialog successfulDialog = new SuccessfulDialog();
                            successfulDialog.show(getParentFragmentManager(), "SuccessfulDialog");
                        }
                        else {
                            FailedDialog failedDialog = new FailedDialog();
                            failedDialog.show(getParentFragmentManager(),"FailedDialog");

                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        FailedDialog failedDialog = new FailedDialog();
                        failedDialog.show(getParentFragmentManager(),"FailedDialog");

                    }
                });

            }
        });

        cancel = view.findViewById(R.id.img_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



    }
}
