package com.jaime.repositorieslivedata.view.adapters;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jaime.repositorieslivedata.R;
import com.jaime.repositorieslivedata.data.models.Project;

import java.util.ArrayList;
import java.util.List;

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ProjectViewHolder> {
    private List<Project> mProjectList;
    private OnItemClickListener mListener;


    public interface OnItemClickListener {
        void onItemClick(Project project);
    }


    public ProjectAdapter(OnItemClickListener listener) {
        mProjectList = new ArrayList<>();
        this.mListener = listener;
    }


    @NonNull
    @Override
    public ProjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.project_item, null);
        ProjectViewHolder holder = new ProjectViewHolder(view);

        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull ProjectViewHolder holder, int position) {
        holder.txvName.setText(mProjectList.get(position).getName());
        holder.txvLanguage.setText(mProjectList.get(position).getLanguage());
        holder.txvWatchers.setText(String.valueOf(mProjectList.get(position).getWatchers_count()));

        holder.bind(mProjectList.get(position), mListener);
    }


    @Override
    public int getItemCount() {
        return mProjectList.size();
    }


    public void setProjectList(final List<Project> projectList) {
        if (this.mProjectList == null) {
            this.mProjectList = projectList;
            notifyItemRangeInserted(0, projectList.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return ProjectAdapter.this.mProjectList.size();
                }

                @Override
                public int getNewListSize() {
                    return projectList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return ProjectAdapter.this.mProjectList.get(oldItemPosition).getId() ==
                            projectList.get(newItemPosition).getId();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    Project project = projectList.get(newItemPosition);
                    Project old = projectList.get(oldItemPosition);
                    return project.getId() == old.getId()
                            && project.getGit_url().equals(old.getGit_url());
                }
            });

            this.mProjectList = projectList;
            result.dispatchUpdatesTo(this);
        }
    }


    public class ProjectViewHolder extends  RecyclerView.ViewHolder {
        private TextView txvName;
        private TextView txvLanguage;
        private TextView txvWatchers;


        public ProjectViewHolder(View itemView) {
            super(itemView);

            txvName = itemView.findViewById(R.id.txv_name);
            txvLanguage = itemView.findViewById(R.id.txv_lenguage);
            txvWatchers = itemView.findViewById(R.id.txv_watchers);
        }


        public void bind(final Project project, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(project);
                }
            });
        }
    }
}
