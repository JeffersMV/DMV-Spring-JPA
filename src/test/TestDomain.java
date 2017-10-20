import entity.Audios;
import entity.Photos;
import entity.Videos;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import repository.AudiosRepository;
import repository.PhotosRepository;
import repository.VideosRepository;

import java.sql.Timestamp;
import java.util.Date;

public class TestDomain {
    @Test
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("test-spring-data-jpa.xml");
        AudiosRepository audiosRepository = context.getBean(AudiosRepository.class);
        Audios audios = new Audios();
        audios.setAudio("Audio_Test");
        audios.setDate(new Timestamp(new Date().getTime()));
        audios.setName("SecondAudio");
        audiosRepository.save(audios);
        System.out.println(audios.toString());

        PhotosRepository photosRepository = context.getBean(PhotosRepository.class);
        Photos photos = new Photos();
        photos.setName("Photo_Test");
        photos.setDate(new Timestamp(new Date().getTime()));
        photos.setPhoto("SecondPhoto");
        photosRepository.save(photos);
        System.out.println(photos.toString());

        VideosRepository videosRepository = context.getBean(VideosRepository.class);
        Videos videos = new Videos();
        videos.setName("Video_Test");
        videos.setDate(new Timestamp(new Date().getTime()));
        videos.setVideo("SecondVideo");
        videosRepository.save(videos);
        System.out.println(videos.toString());
    }
}
