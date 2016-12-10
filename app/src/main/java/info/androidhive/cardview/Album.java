package info.androidhive.cardview;

/**
 * Created by Lincoln on 18/05/16.
 */
public class Album {
    private String name;
    private String des;
    private String place;
    private int numOfSongs;
    private int thumbnail;

    public Album() {
    }

    public Album(String name, String des , String place, int thumbnail) {
        this.name = name;
        this.des = des;
        this.place = place;
        this.numOfSongs = numOfSongs;
        this.thumbnail = thumbnail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumOfSongs() {
        return numOfSongs;
    }

    public void setNumOfSongs(int numOfSongs) {
        this.numOfSongs = numOfSongs;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
