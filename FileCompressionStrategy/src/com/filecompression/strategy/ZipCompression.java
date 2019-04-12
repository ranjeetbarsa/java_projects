package com.filecompression.strategy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipCompression implements CompressionStrategy
{
	
	@Override
	public String compress(File inputDir, File outputCompressFile) 
	{
        byte[] buffer = new byte[1024];
        List<File> allFiles = this.listChildFiles(inputDir);
        String inputDirPath = inputDir.getAbsolutePath();
        
        try 
        {     
            ZipOutputStream zipOs = new ZipOutputStream(new FileOutputStream(outputCompressFile));
            
            for(File file : allFiles) 
            {
                String filePath = file.getAbsolutePath();
                String entryName = filePath.substring(inputDirPath.length() + 1);
                ZipEntry ze = new ZipEntry(entryName);
                zipOs.putNextEntry(ze);
                FileInputStream fileIs = new FileInputStream(filePath);
 
                int len;
                while((len = fileIs.read(buffer))!=-1) 
                {
                    zipOs.write(buffer, 0, len);
                }
                fileIs.close();
            }
            zipOs.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Zipping Done Successfully ";
    }
	
	private List<File> listChildFiles(File dir)
    {
        List<File> allFiles = new ArrayList<File>();
 
        File[] childFiles = dir.listFiles();
        for(File file : childFiles) 
        {
            if (file.isFile()) 
            {
                allFiles.add(file);
            } 
            else 
            {
                List<File> files = this.listChildFiles(file);
                allFiles.addAll(files);
            }
        }
        return allFiles;
    }
}