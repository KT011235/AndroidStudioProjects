package com.example.dynamiclistview;

public class HolidaySongs {
    private String album_img;
    private String album_name;
    private String artist_name;
    private String danceability;
    private String duration_ms;

    public HolidaySongs(String album_name, String artist_name, String danceability, String duration_ms, String album_img) {
      this.album_name = album_name;
      this.artist_name = artist_name;
      this.danceability = danceability;
      this.duration_ms = duration_ms;
      this.album_img = album_img;
    }

    public String getAlbum_name() {
        return album_name;
    }

    public String getArtist_name() {
        return artist_name;
    }

    public String  getDanceability() {
        return danceability;
    }

    public String getDuration_ms() {
        int durationInt = Integer.parseInt(duration_ms) / 1000;
        int minutes = durationInt / 60;
        int seconds = durationInt % 60;
        String finalDuration = String.format("%02d:%02d", minutes, seconds);
        return finalDuration;
    }

    public String getAlbum_img() {
        return album_img;
    }
}

