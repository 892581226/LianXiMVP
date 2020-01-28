package com.example.lianximvp.data.video;



import java.util.ArrayList;

public class UserVideoList {
    private ArrayList<UserVideoList.ArticleList> list;
    private int more;
    private int start;

    public ArrayList<ArticleList> getList() {
        return list;
    }

    public void setList(ArrayList<ArticleList> list) {
        this.list = list;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getMore() {
        return more;
    }

    public void setMore(int more) {
        this.more = more;
    }

    @Override
    public String toString() {
        return "UserVideoList{" +
                "article_list=" + list +
                ", more=" + more +
                ", start=" + start +
                '}';
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

}
