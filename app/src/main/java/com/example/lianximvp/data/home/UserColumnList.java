package com.example.lianximvp.data.home;


import java.util.ArrayList;

public class UserColumnList {

    private ArrayList<Lists> list;

    public ArrayList<Lists> getList() {
        return list;
    }

    public void setList(ArrayList<Lists> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "UserColumnList{" +
                "list=" + list +
                '}';
    }

    public class Lists{
        private String name;
        private String id;
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "Lists{" +
                    "name='" + name + '\'' +
                    ", id='" + id + '\'' +
                    '}';
        }
    }



}
