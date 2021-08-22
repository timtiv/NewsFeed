package com.tim.newsfeed.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class News implements Parcelable {
    String id;
    String title;
    String subtitle;
    String author;
    Date createDate;
    String content;
    Provider provider;
    Category category;

    public News(String title, String subtitle, String author, Date createDate, String content, Provider provider, Category category) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.subtitle = subtitle;
        this.author = author;
        this.createDate = createDate;
        this.content = content;
        this.provider = provider;
        this.category = category;
    }

    protected News(Parcel in) {
        id = in.readString();
        title = in.readString();
        subtitle = in.readString();
        author = in.readString();
        content = in.readString();
        provider = in.readParcelable(Provider.class.getClassLoader());
        category = in.readParcelable(Category.class.getClassLoader());
        createDate = new Date(in.readLong());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(subtitle);
        dest.writeString(author);
        dest.writeString(content);
        dest.writeParcelable(provider, flags);
        dest.writeParcelable(category, flags);
        dest.writeLong(createDate.getTime());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<News> CREATOR = new Creator<News>() {
        @Override
        public News createFromParcel(Parcel in) {
            return new News(in);
        }

        @Override
        public News[] newArray(int size) {
            return new News[size];
        }
    };

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getAuthor() {
        return author;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public String getContent() {
        return content;
    }

    public Provider getProvider() {
        return provider;
    }

    public Category getCategory() {
        return category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        News news = (News) o;
        return Objects.equals(id, news.id) &&
                Objects.equals(title, news.title) &&
                Objects.equals(subtitle, news.subtitle) &&
                Objects.equals(author, news.author) &&
                Objects.equals(createDate, news.createDate) &&
                Objects.equals(content, news.content) &&
                provider == news.provider &&
                category == news.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, subtitle, author, createDate, content, provider, category);
    }

}
