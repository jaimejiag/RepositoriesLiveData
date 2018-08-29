package com.jaime.repositorieslivedata.data.network;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.jaime.repositorieslivedata.data.models.Project;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class GitHubClient {
    private static GitHubClient mInstance;
    private GitHubService mGitHubService;


    private GitHubClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GitHubService.GITHUB_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mGitHubService = retrofit.create(GitHubService.class);
    }


    public synchronized static GitHubClient getInstance() {
        if (mInstance == null)
            mInstance = new GitHubClient();

        return mInstance;
    }


    public LiveData<List<Project>> getProjectList(String userId) {
        final MutableLiveData<List<Project>> data = new MutableLiveData<>();

        mGitHubService.getProjectList(userId).enqueue(new Callback<List<Project>>() {
            @Override
            public void onResponse(Call<List<Project>> call, Response<List<Project>> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Project>> call, Throwable t) {

            }
        });

        return data;
    }
}
