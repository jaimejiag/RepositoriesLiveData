package com.jaime.repositorieslivedata.view.project;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jaime.repositorieslivedata.R;
import com.jaime.repositorieslivedata.data.models.Project;
import com.jaime.repositorieslivedata.view.adapters.ProjectAdapter;
import com.jaime.repositorieslivedata.viewModels.ProjectListViewModel;

import java.util.List;


public class ProjectListView extends Fragment {
    public static final String TAG = "projectlistview";
    private RecyclerView rvProject;
    private ProjectAdapter mAdapter;
    private ProjectAdapter.OnItemClickListener mItemClickListener;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initializeItemClickListener();
        mAdapter = new ProjectAdapter(mItemClickListener);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_project_list, container, false);
        rvProject = view.findViewById(R.id.project_list);

        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        rvProject.setHasFixedSize(true);
        rvProject.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvProject.setAdapter(mAdapter);

        final ProjectListViewModel viewModel = ViewModelProviders.of(this).get(ProjectListViewModel.class);
        observeViewModel(viewModel);
    }


    private void observeViewModel(ProjectListViewModel viewModel) {
        viewModel.getProjectListObservable().observe(this, new Observer<List<Project>>() {
            @Override
            public void onChanged(@Nullable List<Project> projects) {
                if (projects != null)
                    mAdapter.setProjectList(projects);
            }
        });
    }


    private void initializeItemClickListener() {
        mItemClickListener = new ProjectAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Project project) {
                Snackbar.make(getView(), "Presionado", Snackbar.LENGTH_SHORT).show();
            }
        };
    }

}
