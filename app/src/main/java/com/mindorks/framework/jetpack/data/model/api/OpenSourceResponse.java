/*
 *  Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      https://mindorks.com/license/apache-v2
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License
 */

package com.mindorks.framework.jetpack.data.model.api;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by amitshekhar on 07/07/17.
 */

public class OpenSourceResponse {

    @Expose
    @SerializedName("data")
    private List<Repo> data;

    @Expose
    @SerializedName("message")
    private String message;

    @Expose
    @SerializedName("status_code")
    private String statusCode;


    public List<Repo> getData() {
        return data;
    }

    public void setData(List<Repo> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public static class Repo implements Parcelable {

        @Expose
        @SerializedName("img_url")
        private String coverImgUrl;

        @Expose
        @SerializedName("description")
        private String description;

        @Expose
        @SerializedName("project_url")
        private String projectUrl;

        @Expose
        @SerializedName("title")
        private String title;

        protected Repo(Parcel in) {
            coverImgUrl = in.readString();
            description = in.readString();
            projectUrl = in.readString();
            title = in.readString();
        }

        public static final Creator<Repo> CREATOR = new Creator<Repo>() {
            @Override
            public Repo createFromParcel(Parcel in) {
                return new Repo(in);
            }

            @Override
            public Repo[] newArray(int size) {
                return new Repo[size];
            }
        };



        @Override
        public int hashCode() {
            int result = projectUrl.hashCode();
            result = 31 * result + coverImgUrl.hashCode();
            result = 31 * result + title.hashCode();
            result = 31 * result + description.hashCode();
            return result;
        }

        public String getCoverImgUrl() {
            return coverImgUrl;
        }

        public String getDescription() {
            return description;
        }

        public String getProjectUrl() {
            return projectUrl;
        }

        public String getTitle() {
            return title;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(coverImgUrl);
            parcel.writeString(description);
            parcel.writeString(projectUrl);
            parcel.writeString(title);


        }

        public static DiffUtil.ItemCallback<Repo> DIFF_CALLBACK = new DiffUtil.ItemCallback<Repo>() {
            @Override
            public boolean areItemsTheSame(@NonNull Repo oldItem, @NonNull Repo newItem) {
                return oldItem.projectUrl == newItem.projectUrl;
            }

            @Override
            public boolean areContentsTheSame(@NonNull Repo oldItem, @NonNull Repo newItem) {
                return oldItem.equals(newItem);
            }
        };

        @Override
        public boolean equals(Object obj) {
            if (obj == this)
                return true;

            Repo article = (Repo) obj;
            return article.projectUrl == this.projectUrl;
        }
    }
}
