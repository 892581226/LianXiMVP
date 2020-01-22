package com.example.lianximvp.data.home;

import java.util.ArrayList;

public class UserColumnListLan {

    private ArrayList<BannerList> banner_list;
    private ArrayList<ArticleList> article_list;
    private ArrayList<Flashlist> flash_list;

    public ArrayList<Flashlist> getFlash_list() {
        return flash_list;
    }

    public void setFlash_list(ArrayList<Flashlist> flash_list) {
        this.flash_list = flash_list;
    }

    public ArrayList<BannerList> getBanner_list() {
        return banner_list;
    }

    public void setBanner_list(ArrayList<BannerList> banner_list) {
        this.banner_list = banner_list;
    }

    public ArrayList<ArticleList> getArticle_list() {
        return article_list;
    }

    public void setArticle_list(ArrayList<ArticleList> article_list) {
        this.article_list = article_list;
    }

    @Override
    public String toString() {
        return "UserColumnListLan{" +
                "banner_list=" + banner_list +
                ", article_list=" + article_list +
                '}';
    }

    public class BannerList {
        private String id;
        private String theme;
        private String image_url;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTheme() {
            return theme;
        }

        public void setTheme(String theme) {
            this.theme = theme;
        }

        public String getImage_url() {
            return image_url;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }

        @Override
        public String toString() {
            return "BannerList{" +
                    "id='" + id + '\'' +
                    ", theme='" + theme + '\'' +
                    ", image_url='" + image_url + '\'' +
                    '}';
        }
    }

    public class ArticleList {
        private String id;
        private String theme;
        private String content;
        private String description;
        private String image_url;
        private String view_type;
        private String column_name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTheme() {
            return theme;
        }

        public void setTheme(String theme) {
            this.theme = theme;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getImage_url() {
            return image_url;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }

        public String getView_type() {
            return view_type;
        }

        public void setView_type(String view_type) {
            this.view_type = view_type;
        }

        public String getColumn_name() {
            return column_name;
        }

        public void setColumn_name(String column_name) {
            this.column_name = column_name;
        }
    }

    public class Flashlist {

        private String id;
        private String theme;
        private String description;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTheme() {
            return theme;
        }

        public void setTheme(String theme) {
            this.theme = theme;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        @Override
        public String toString() {
            return "Flashlist{" +
                    "id='" + id + '\'' +
                    ", theme='" + theme + '\'' +
                    ", description='" + description + '\'' +
                    '}';
        }
    }
}
