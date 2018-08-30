package com.jaime.repositorieslivedata.viewModels;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.util.ArrayMap;

import com.jaime.repositorieslivedata.di.ViewModelSubComponent;

import java.util.Map;
import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
public class ProjectViewModelFactory implements ViewModelProvider.Factory {
    private final ArrayMap<Class, Callable<? extends ViewModel>> mCreators;


    @Inject
    public ProjectViewModelFactory(ViewModelSubComponent viewModelSubComponent) {
        mCreators = new ArrayMap<>();

        // View models cannot be injected directly because they won't be bound to the owner's
        // view model scope.
        mCreators.put(ProjectViewModel.class, viewModelSubComponent::projectViewModel);
        mCreators.put(ProjectListViewModel.class, viewModelSubComponent::projectListViewModel);
    }


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        Callable<? extends ViewModel> creator = mCreators.get(modelClass);

        if (creator == null) {
            for (Map.Entry<Class, Callable<? extends ViewModel>> entry : mCreators.entrySet()) {
                if (modelClass.isAssignableFrom(entry.getKey())) {
                    creator = entry.getValue();
                    break;
                }
            }
        }

        if (creator == null)
            throw new IllegalArgumentException("Unknown model class " + modelClass);

        try {
            return (T) creator.call();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
