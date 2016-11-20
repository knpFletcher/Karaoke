import com.teamtreehouse.KaraokeMachine;
import com.teamtreehouse.model.Song;
import com.teamtreehouse.model.Songbook;

public class Karaoke{

  public static void main (String[] args){
    Songbook songbook = new Songbook();
    KaraokeMachine machine = new KaraokeMachine(songbook);
    machine.run();
  }
}
