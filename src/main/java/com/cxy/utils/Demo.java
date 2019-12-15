package com.cxy.utils;


import com.aspose.slides.*;
import org.apache.ibatis.io.ResolverUtil;

import java.io.*;

public class Demo {

    private static InputStream license;

    public static boolean getLicense() {
        boolean result = false;
        try {
            license = ResolverUtil.Test.class.getClassLoader().getResourceAsStream("license.xml");// license路径
            License aposeLic = new License();
            aposeLic.setLicense(license);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void ppttopdf(String Adress) {
        if (!getLicense()) {
            return;
        }
        try {
            File file = new File("\\ppt_to_pdf\\demo.pdf");
            Presentation pres = new Presentation(Adress);
            FileOutputStream fileOS = new FileOutputStream(file);
            pres.save(fileOS, SaveFormat.Pdf);
            fileOS.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
