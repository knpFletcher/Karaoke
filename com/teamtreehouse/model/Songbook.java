package com.teamtreehouse.model;

import java.util.ArrayList;
import java.util.List;

public class Songbook{
  private List<Song> mSongs;

  public Songbook(){
    mSongs = new ArrayList<Song>();
  }

  public void addSong (Song song){
    mSongs.add(song);
  }

  public int getSongCount(){
    return mSongs.size();
  }
}
