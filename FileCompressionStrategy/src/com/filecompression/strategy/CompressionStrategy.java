package com.filecompression.strategy;

import java.io.File;

public interface CompressionStrategy 
{
	String compress(File inputDir,File outputCompressFile);
}
