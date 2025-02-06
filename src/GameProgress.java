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
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        deleteFiles(files);
    }

    static void openZip(String zipPath, String unzipPath) throws IOException {
        File destinationDir = new File(unzipPath);
        try (ZipInputStream zipInputStream = new ZipInputStream((new FileInputStream(zipPath))) {

            ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(zipPath));
            ZipEntry zipEntry = zipInputStream.getNextEntry();
            zipE
                zipOutStream.write(zipInputStream.readAllBytes());

        } catch(IOException e) {
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

    private static void deleteFiles(String[] filesToDelete) {
        for (String filePath : filesToDelete) {
            File file = new File(filePath);
            if (file.delete()) {
                System.out.println("Файл " + filePath + " удален.");
            } else {
                System.out.println("Не удалось удалить файл: " + filePath);
            }
        }
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