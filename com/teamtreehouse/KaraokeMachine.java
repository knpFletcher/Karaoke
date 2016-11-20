package com.teamtreehouse;

import com.teamtreehouse.model.Song;
import com.teamtreehouse.model.Songbook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class KaraokeMachine{
  private Songbook mSongbook;
  private BufferedReader mReader;
  private Queue<Song> mSongQueue;

  private Map<String, String> mMenu;

  public KaraokeMachine(Songbook songbook){
    mSongbook = songbook;
    mReader = new BufferedReader (new InputStreamReader(System.in));
    mSongQueue = new ArrayDeque<Song>();
    mMenu = new HashMap<String, String>();
    mMenu.put("add", "Add a new song to the songbook");
    mMenu.put("play", "Play next song in the queue");
    mMenu.put("choose", "Choose a song to sing");
    mMenu.put("quit", "Give up, exit the program");
  }

  private String promptAction() throws IOException {
    System.out.printf("There are %d songs available and %d in the queue.  Your options are:  %n",
                      mSongbook.getSongCount(),
                      mSongQueue.size());
    for(Map.Entry<String, String> option: mMenu.entrySet()){
      System.out.printf("%s - %s %n",
                        option.getKey(),
                        option.getValue());
    }
    System.out.print("What do you want to do:  ");
    String choice = mReader.readLine();
    return choice.trim().toLowerCase();
  }

  public void run(){
    String choice = "";
    do{
      try{
          choice = promptAction();
          switch(choice){
            case "add":
              Song song = promptNewSong();
              mSongbook.addSong(song);
              System.out.printf("%s added! %n%n", song);
              break;
            case "choose":
              String artist = promptArtist();
              Song artistSong = promptSongForArtist(artist);
              mSongQueue.add(artistSong);
              System.out.printf("You chose: %s%n", artistSong);
              break;
            case "play":
              playNext();
              break;
            case "quit":
              System.out.println("Thanks for playing!");
              break;
            default:
              System.out.printf("Unknown chioce: ''%s'. Try Again %n%n%n", choice);
              break;
          }
      } catch(IOException ioe){
        System.out.println("Problem with input");
        ioe.printStackTrace();
      }

      }while(!choice.equals("quit"));
    }

    private Song promptNewSong() throws IOException{
      System.out.print("Enter the Artist's name:  ");
      String artist = mReader.readLine();
      System.out.print("Enter the title:  ");
      String title = mReader.readLine();
      System.out.print("Enter a video url:  ");
      String videoUrl = mReader.readLine();

      return new Song(artist, title, videoUrl);
    }

    private String promptArtist() throws IOException{
      System.out.println("Available artists: ");
      List<String> artists = new ArrayList<>(mSongbook.getArtist());
      int index = promptForIndex(artists);
      return artists.get(index);
    }

    private Song promptSongForArtist(String artist) throws IOException{
      List<Song> songs = mSongbook.getSongsForArtist(artist);
      List<String> songTitles = new ArrayList<>();
      for(Song song : songs){
        songTitles.add(song.getTitle());
      }
      System.out.printf("Available songs for %s: %n", artist);
      int index = promptForIndex(songTitles);
      return songs.get(index);
    }

    private int promptForIndex(List<String> options) throws IOException{
      int counter = 1;
      for (String option : options){
        System.out.printf("%d.)  %s %n", counter, option);
        counter++;
      }
      System.out.print("Your choice:  ");
      String optionAsString = mReader.readLine();
      int choice = Integer.parseInt(optionAsString.trim());
      return choice - 1;
    }

    public void playNext(){
      Song song = mSongQueue.poll();
      if(song == null){
        System.out.println("Sorry, there are no songs in the queue." +
                            " Please choose from the menu to add songs");

      } else {
        System.out.printf("%n%n Open %s to hear %s by %s %n%n",
                          song.getVideoUrl(),
                          song.getTitle(),
                          song.getArtist());
      }
    }

}
