package com.filecompression.strategy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;

public class JarCompression implements CompressionStrategy
{
	@Override
	public String compress(File inputDir, File outputCompressFile) 
	{
		List<File> files = this.listChildFiles(inputDir);
		byte[] buffer = new byte[1024];
		 String inputDirPath = inputDir.getAbsolutePath();
		try 
		{
			JarOutputStream jarOutputStream = new JarOutputStream(new FileOutputStream(outputCompressFile),new Manifest());
			for(File file : files)
			{
				String path = file.getAbsolutePath();
				FileInputStream fis = new FileInputStream(path);
				String entryName = path.substring(inputDirPath.length() + 1);
				
				JarEntry jar = new JarEntry(entryName);
				jar.setTime(file.lastModified());
				jarOutputStream.putNextEntry(jar);
				
				
				int len;
				while((len = fis.read(buffer))!=-1)
				{
					jarOutputStream.write(buffer, 0, len);
				}
				fis.close();
			}
			jarOutputStream.close();
		} 
		catch (IOException e) 
		{	e.printStackTrace(); }
		return "Jar compression Done Successfully";
	}
	private List<File> listChildFiles(File inputDir)
	{
		List<File> files = new ArrayList<File>();
		File[] fileList = inputDir.listFiles();
		for(File file : fileList)
		{
			files.add(file);
		}
		return files;
	}
}
