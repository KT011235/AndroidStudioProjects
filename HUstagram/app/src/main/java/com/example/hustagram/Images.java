package com.example.hustagram;

public class Images {
    protected String _id;
    protected String time;
    protected String comment;
    protected String image;

    public Images(String _id, String time, String comment, String image) {
        this._id = _id;
        this.time = time;
        this.comment = comment;
        this.image = image;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
