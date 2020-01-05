package com.example.demo.rest;

import com.example.demo.util.DateUtil;
import com.example.demo.util.FileZipUtil;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.io.*;
import java.net.URLEncoder;
import java.text.MessageFormat;

@Component
@Path("/file")
public class FileRest {

    @POST
    @Path("/export")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public void download(@Context HttpServletRequest request, @Context HttpServletResponse response) throws Exception{
        InputStream fis = null;
        OutputStream toClient = null;
        File file = null;
        try{
            String sessionCode="1000083";
            String srcimgdir= MessageFormat.format(FileZipUtil.imgPath,sessionCode);
            String srcjsondir=MessageFormat.format(FileZipUtil.jsonPath,sessionCode);
            String[] srcPaths = new String[]{srcimgdir,srcjsondir};
            String targetdir=FileZipUtil.targetPath;
            String zipFileNmae=sessionCode+ DateUtil.getDay()+".zip";
            FileZipUtil.compressedFile(srcPaths,targetdir,zipFileNmae);
            String zipDownloadPath= targetdir + File.separator +zipFileNmae;
            // String zipDownloadPath="E:\\biengine\\temp\\test.zip";
             file = new File(zipDownloadPath);
            fis = new BufferedInputStream(new FileInputStream(file));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            response.reset();
            response.setCharacterEncoding("UTF-8");
            String filename = file.getName().replaceAll("\\s", "");
            filename = URLEncoder.encode(filename,"UTF-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename));
            response.addHeader("Content-Length", "" + file.length());
            toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
        }catch(Exception e){
            return ;
        }
        finally{
            file.deleteOnExit();
            fis.close();
            toClient.close();
        }

    }
/*
    *//**
     * 第一种方式上传
     *
     * @param fileInputStream
     * @param disposition
     * @return
     *//*
    @POST
    @Path("uploadimage1")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public String uploadimage1(@FormDataParam("file") InputStream fileInputStream,
                               @FormDataParam("file") FormDataContentDisposition disposition) throws IOException {
        ZipInputStream zipInputStream = null;
        try{
            zipInputStream = new ZipInputStream(fileInputStream);
            disposition.getFileName();

        }catch (Exception e){

        }
        finally{
            zipInputStream.close();
        }
        return "";
    }*/
}
