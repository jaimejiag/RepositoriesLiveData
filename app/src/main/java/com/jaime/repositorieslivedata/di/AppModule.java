package com.jaime.repositorieslivedata.di;


import android.arch.lifecycle.ViewModelProvider;

import com.jaime.repositorieslivedata.data.network.GitHubService;
import com.jaime.repositorieslivedata.viewModels.ProjectViewModelFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(subcomponents = ViewModelSubComponent.class)
public class AppModule {

    @Singleton
    @Provides
    GitHubService provideGitHubService() {
        return new Retrofit.Builder()
                .baseUrl(GitHubService.GITHUB_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GitHubService.class);
    }


    @Singleton
    @Provides
    ViewModelProvider.Factory provideViewModelFactory(ViewModelSubComponent.Builder viewModelSubComponent) {
        return new ProjectViewModelFactory(viewModelSubComponent.build());
    }
}
