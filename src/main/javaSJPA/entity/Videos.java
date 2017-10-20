package entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Videos {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    @Column(name = "name", nullable = true, length = 255)
    private String name;

    @Basic
    @Column(name = "date", nullable = true)
    private Timestamp date;

    @Basic
    @Column(name = "video", nullable = true, length = 255)
    private String video;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    @Override
    public String toString() {
        return "Videos{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", video='" + video + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Videos videos = (Videos) o;

        if (id != videos.id) return false;
        if (name != null ? !name.equals(videos.name) : videos.name != null) return false;
        if (date != null ? !date.equals(videos.date) : videos.date != null) return false;
        if (video != null ? !video.equals(videos.video) : videos.video != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (video != null ? video.hashCode() : 0);
        return result;
    }
}
