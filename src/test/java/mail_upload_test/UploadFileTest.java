package mail_upload_test;

import org.junit.jupiter.api.Test;


public class UploadFileTest {

    @Test
    public void test1() {
        String fileName = "114514.jpg";
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        System.out.println(suffix);
    }
}
