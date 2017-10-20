package servlets;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import repository.AudiosRepository;
import repository.PhotosRepository;
import repository.VideosRepository;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        try {
            ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-data-jpa.xml");
            AudiosRepository audiosRepository = context.getBean(AudiosRepository.class);
            PhotosRepository photosRepository = context.getBean(PhotosRepository.class);
            VideosRepository videosRepository = context.getBean(VideosRepository.class);

            System.out.println("[MySessionListener] Session created: "+session);

            session.setAttribute("audiosRepository", audiosRepository);
            session.setAttribute("photosRepository", photosRepository);
            session.setAttribute("videosRepository", videosRepository);
        } catch (Exception e) {
            System.out.println("[MySessionListener] Error setting session attribute: " + e.getMessage());
        }
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        System.out.println("[MySessionListener] Session invalidated: "+session);
    }
}
