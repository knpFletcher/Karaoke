import com.teamtreehouse.model.Song;
import com.teamtreehouse.model.Songbook;

public class Karaoke{

  public static void main (String[] args){
    Song song = new Song("Michael Jackson",
                          "Beat It",
                          "https://www.youtube.com/watch?v=oRdxUFDoQe0");

    Songbook songbook = new Songbook();
    System.out.printf("Adding %s, %n", song);
    songbook.addSong(song);
    System.out.printf("There are %d songs.  %n",songbook.getSongCount());
  }
}
