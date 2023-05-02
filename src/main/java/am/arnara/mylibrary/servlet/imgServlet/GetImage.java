package am.arnara.mylibrary.servlet.imgServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;


@WebServlet("/img")
public class GetImage extends HttpServlet {
    private static final String UPLOAD_FOLDER = "/home/radik/IdeaProjects/jakartaProjects/myLibrary/images/";
    private static final String DEFAULT_BOOK_IMG_FOLDER = "/home/radik/IdeaProjects/jakartaProjects/myLibrary/src/main/webapp/images/bookImages/";



    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String picName = req.getParameter("picName");

        File imageFile = new File(UPLOAD_FOLDER + picName);
        if (imageFile.exists()) {
            readFile(resp, imageFile);

        }else {
            File defaultImg = new File(DEFAULT_BOOK_IMG_FOLDER + picName);
            readFile(resp, defaultImg);
        }
    }

    private static void readFile(HttpServletResponse resp, File imageFile) {
        try (FileInputStream fileInputStream = new FileInputStream(imageFile)) {
            OutputStream outStream = resp.getOutputStream();

            byte[] buffer = new byte[4096];
            int bytesRead;

            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
