package com.egzepsn.rsc.rscapp.modules.main.teams;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.EditText;

import com.egzepsn.rsc.rscapp.R;
import com.egzepsn.rsc.rscapp.models.CreateTeam;
import com.egzepsn.rsc.rscapp.models.CreateTeamResponse;
import com.egzepsn.rsc.rscapp.models.JoinTeam;
import com.egzepsn.rsc.rscapp.models.JoinTeamResponse;
import com.egzepsn.rsc.rscapp.rest.ApiModule;
import com.egzepsn.rsc.rscapp.rest.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by kiki3 on 27.11.2016..
 */

public class NewTeamDialog extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("New team");
        builder.setView(LayoutInflater.from(getActivity()).inflate(R.layout.dialog_new_team, null));

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final ApiService apiService = ApiModule.createService(ApiService.class);
                EditText teamName = (EditText) getDialog().findViewById(R.id.new_team_name);

                Call<CreateTeamResponse> call = apiService.createTeam(new CreateTeam(teamName.getText().toString(), getArguments().getInt("eventId")));
                call.enqueue(new Callback<CreateTeamResponse>() {
                    @Override
                    public void onResponse(Call<CreateTeamResponse> call, Response<CreateTeamResponse> response) {
                        CreateTeamResponse createTeamResponse = response.body();
                        ApiService service = ApiModule.createService(ApiService.class);
                        Call<JoinTeamResponse> responseCall = service.joinTeam(new JoinTeam(createTeamResponse.getEventId(), createTeamResponse.getTeamId(), createTeamResponse.getPassword()));

                        responseCall.enqueue(new Callback<JoinTeamResponse>() {
                            @Override
                            public void onResponse(Call<JoinTeamResponse> call, Response<JoinTeamResponse> response) {
                                Log.d("ONRESPONSE", "JOINED");
                            }

                            @Override
                            public void onFailure(Call<JoinTeamResponse> call, Throwable t) {

                            }
                        });
                    }

                    @Override
                    public void onFailure(Call<CreateTeamResponse> call, Throwable t) {
                    }
                });
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dismiss();
            }
        });

        return builder.create();
    }
}
