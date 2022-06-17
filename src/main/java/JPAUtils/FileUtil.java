package JPAUtils;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

public class FileUtil {

    public static File saveFileUpload(String nameFolder, Part part) {
        File folderUpload = new File("/src/main/webapp/images/" + nameFolder);
        if (!folderUpload.exists()) {
            folderUpload.mkdirs();
        }

        File file = new File(folderUpload, part.getSubmittedFileName());
//        System.out.println(file.getAbsolutePath());
        try {
            if (file.exists()){
            part.write(file.getAbsolutePath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
}