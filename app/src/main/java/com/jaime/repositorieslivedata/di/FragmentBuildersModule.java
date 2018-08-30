package com.jaime.repositorieslivedata.di;

import com.jaime.repositorieslivedata.view.project.ProjectDetailsView;
import com.jaime.repositorieslivedata.view.project.ProjectListView;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract ProjectDetailsView contributeProjectDetailsFragment();

    @ContributesAndroidInjector
    abstract ProjectListView contributeProjectListFragment();
}
