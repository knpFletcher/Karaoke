import com.teamtreehouse.KaraokeMachine;
import com.teamtreehouse.model.Song;
import com.teamtreehouse.model.Songbook;

public class Karaoke{

  public static void main (String[] args){
    Songbook songbook = new Songbook();
    songbook.importFrom("songs.txt");
    KaraokeMachine machine = new KaraokeMachine(songbook);
    machine.run();
    System.out.println("Saving...");
    songbook.exportTo("songs.txt");
    
  }
}
