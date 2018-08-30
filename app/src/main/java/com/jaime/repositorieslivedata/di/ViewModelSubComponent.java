package com.jaime.repositorieslivedata.di;

import com.jaime.repositorieslivedata.viewModels.ProjectListViewModel;
import com.jaime.repositorieslivedata.viewModels.ProjectViewModel;

import dagger.Subcomponent;

@Subcomponent
public interface ViewModelSubComponent {

    @Subcomponent.Builder
    interface Builder {
        ViewModelSubComponent build();
    }


    ProjectListViewModel projectListViewModel();
    ProjectViewModel projectViewModel();
}
