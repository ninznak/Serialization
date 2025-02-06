import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        GameProgress progress1 = new GameProgress(34, 44, 6, 5.5);
        GameProgress progress2 = new GameProgress(55, 35, 2, 9.5);
        GameProgress progress3 = new GameProgress(40, 50, 8, 15.3);

        GameProgress.saveGame("D:/GamesNetology/savegames/save1.dat", progress1);
        GameProgress.saveGame("D:/GamesNetology/savegames/save2.dat", progress2);
        GameProgress.saveGame("D:/GamesNetology/savegames/save3.dat", progress3);

        String[] filesForSave = {
                "D:/GamesNetology/savegames/save1.dat",
                "D:/GamesNetology/savegames/save2.dat",
                "D:/GamesNetology/savegames/save3.dat"
        };

        String zipAddressFile = "D:/GamesNetology/savegames/savegames.zip";

        GameProgress.zipFiles(zipAddressFile, filesForSave);

        GameProgress.deleteFiles(filesForSave);
    }
}