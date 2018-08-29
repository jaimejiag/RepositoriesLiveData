package com.jaime.repositorieslivedata.viewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.jaime.repositorieslivedata.data.models.Project;
import com.jaime.repositorieslivedata.data.repositories.ProjectRepository;

import java.util.List;

public class ProjectListViewModel extends AndroidViewModel {
    private final LiveData<List<Project>> mProjectListObservable;


    public ProjectListViewModel(Application application) {
        super(application);
        mProjectListObservable = ProjectRepository.getInstance().getProjectList("jaimejiag");
    }


    public LiveData<List<Project>> getProjectListObservable() {
        return mProjectListObservable;
    }
}
