package com.jaime.repositorieslivedata.data.network;

import com.jaime.repositorieslivedata.data.models.Project;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubService {
    String GITHUB_API_URL = "https://api.github.com/";


    @GET("users/{user}/repos")
    Call<List<Project>> getProjectList(@Path("user") String user);


    @GET("/repos/{user}/{reponame}")
    Call<Project> getProjectDetails(@Path("user") String user, @Path("reponame") String projectName);
}
