import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class GameProgress implements Serializable {

    private static final long serialVersionUID = 1L;

    private final int health;
    private final int weapons;
    private final int lvl;
    private final double distance;

    public GameProgress(int health, int weapons, int lvl, double distance) {
        this.health = health;
        this.weapons = weapons;
        this.lvl = lvl;
        this.distance = distance;
    }

    static void saveGame(String path, GameProgress gameProgress) {
        File saveFile = new File(path);
        try (ObjectOutputStream objectStream = new ObjectOutputStream(new FileOutputStream(saveFile))) {
            objectStream.writeObject(gameProgress);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    static void zipFiles(String zipPath, String[] files) {
        try (ZipOutputStream zipOutStream = new ZipOutputStream(new FileOutputStream(zipPath))) {
            for (String filePath : files) {
                File file = new File(filePath);
                try (FileInputStream fileInputStream = new FileInputStream(file)) {
                    ZipEntry zipEntry = new ZipEntry(file.getName());
                    zipOutStream.putNextEntry(zipEntry);
                    zipOutStream.write(fileInputStream.readAllBytes());
                    zipOutStream.closeEntry();
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        for (String filePath : files) {
            File file = new File(filePath);
            file.delete();
        }
    }

    static void openZip(String zipPath, String unzipPath) {
        File destinationDir = new File(unzipPath);

        if (!destinationDir.exists()) {
            destinationDir.mkdir();
        }

        try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(zipPath))) {
            ZipEntry zipEntry;
            while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                File outputFile = new File(destinationDir, zipEntry.getName());
                try (FileOutputStream fileOutputStream = new FileOutputStream(outputFile)) {
                    fileOutputStream.write(zipInputStream.readAllBytes());
                    fileOutputStream.flush();
                }
                zipInputStream.closeEntry();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    static GameProgress openProgress(String path) {
        File savedFile = new File(path);
        try (ObjectInputStream objectStream = new ObjectInputStream(new FileInputStream(savedFile))) {
            return (GameProgress) objectStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String toString() {
        return "GameProgress{" +
                "health=" + health +
                ", weapons=" + weapons +
                ", lvl=" + lvl +
                ", distance=" + distance +
                '}';
    }
}