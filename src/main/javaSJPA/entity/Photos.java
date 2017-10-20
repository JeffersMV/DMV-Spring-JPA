package entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Photos {

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
    @Column(name = "photo", nullable = true, length = 255)
    private String photo;

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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "Photos{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", photo='" + photo + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Photos photos = (Photos) o;

        if (id != photos.id) return false;
        if (name != null ? !name.equals(photos.name) : photos.name != null) return false;
        if (date != null ? !date.equals(photos.date) : photos.date != null) return false;
        if (photo != null ? !photo.equals(photos.photo) : photos.photo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (photo != null ? photo.hashCode() : 0);
        return result;
    }
}
