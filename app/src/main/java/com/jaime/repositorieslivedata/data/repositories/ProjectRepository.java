package com.jaime.repositorieslivedata.data.repositories;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.jaime.repositorieslivedata.data.models.Project;
import com.jaime.repositorieslivedata.data.network.GitHubClient;

import java.util.ArrayList;
import java.util.List;

public class ProjectRepository {
    private static ProjectRepository mInstance;


    public static ProjectRepository getInstance() {
        if (mInstance == null)
            mInstance = new ProjectRepository();

        return mInstance;
    }


    public LiveData<List<Project>> getProjectList(String userId) {
        return GitHubClient.getInstance().getProjectList(userId);
    }
}
