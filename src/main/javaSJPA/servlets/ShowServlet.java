package servlets;

import entity.Photos;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.jpa.repository.JpaRepository;
import repository.AudiosRepository;
import repository.PhotosRepository;
import repository.VideosRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class ShowServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-data-jpa.xml");
        AudiosRepository audiosRepository = (AudiosRepository) context.getBean("audiosRepository");
        PhotosRepository photosRepository = (PhotosRepository) context.getBean("photosRepository");
        VideosRepository videosRepository = (VideosRepository) context.getBean("videosRepository");
        try {
            System.out.println(audiosRepository.findAll());
            System.out.println(photosRepository.findAll());
            System.out.println(videosRepository.findAll());
        } catch (Exception exception) {
            System.out.println("Ошибка Бинов");
        }

        if (Objects.equals(request.getParameter("action"), "audio")) {
            sendDTOListOnPage(request, response, (AudiosRepository) request.getSession().getAttribute("audiosRepository"));
        } else if (Objects.equals(request.getParameter("action"), "video")) {
            sendDTOListOnPage(request, response, (VideosRepository) request.getSession().getAttribute("videosRepository"));
        } else if (Objects.equals(request.getParameter("action"), "photo")) {
            sendDTOListOnPage(request, response, (PhotosRepository) request.getSession().getAttribute("photosRepository"));
        }else if (Objects.equals(request.getParameter("action"), "onePhoto")) {
            sendPhotoDTOOnPage(request, response);
        } else if (Objects.equals(request.getParameter("action"), "SEND")) {
            if (request.getParameter("name").isEmpty()) {
                request.getRequestDispatcher("/index.jsp?action=error&error=name_error").forward(request, response);
            } else if (request.getParameter("phone").isEmpty()) {
                request.getRequestDispatcher("/index.jsp?action=error&error=phone_error").forward(request, response);
            } else if (request.getParameter("e-mail").isEmpty()) {
                request.getRequestDispatcher("/index.jsp?action=error&error=e-mail_error").forward(request, response);
            } else {
                sentMail(request, response);
            }
        } else {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }

    private void sendDTOListOnPage(HttpServletRequest request, HttpServletResponse response, JpaRepository jpaRepository) throws ServletException, IOException {
        List dtoList = null;
        try {
            dtoList = jpaRepository.findAll();
        } catch (Exception e) {
            request.getRequestDispatcher("/index.jsp?action=error&error=connect_BD").forward(request, response);
        }
        request.setAttribute("dtoList", dtoList);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    private void sendPhotoDTOOnPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Photos photos = null;

        try {
            Long id = Long.parseLong(request.getParameter("id"));
            photos = ((PhotosRepository) request.getSession().getAttribute("photosRepository")).getOne(id);
        } catch (Exception e) {
            request.getRequestDispatcher("/index.jsp?action=error&error=connect_BD").forward(request, response);
        }
        request.setAttribute("photos", photos);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    private void sentMail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        try {
            SimpleEmail email = new SimpleEmail();
            email.setSSLOnConnect(true);
            email.setHostName("smtp.gmail.com");
            email.setSmtpPort(465);
            email.setSubject("DMV " + phone + "|" + name);
            email.setAuthenticator(new DefaultAuthenticator("User Name", "Password"));
            email.addTo("dmv@gmail.com", "DMV.com");
            email.setFrom(request.getParameter("e-mail"), name);
            email.setMsg("Text + " + phone + "|" + name);
            email.send();
        } catch (EmailException e) {
            e.getStackTrace();
            request.getRequestDispatcher("/index.jsp?action=error&error=send_e-mail").forward(request, response);
        }
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

}
