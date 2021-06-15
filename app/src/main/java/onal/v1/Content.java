package onal.v1;

import android.provider.BaseColumns;

public class Content {
    private int id;
    private String directory;
    private String name;

    public static final class ContentTable implements BaseColumns {
        public final static String TABLE_NAME = "content";
        public final static String ID = BaseColumns._ID;
        public final static String DIRECTORY = "directory";
        public final static String NAME = "name";
    }

    public Content(int id, String directory, String name) {
        this.id = id;
        this.directory = directory;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Content{" +
                "id=" + id +
                ", directory='" + directory + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
