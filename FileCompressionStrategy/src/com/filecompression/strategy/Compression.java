package com.filecompression.strategy;

import java.io.File;

public class Compression 
{
	private CompressionStrategy compressionStrategy;
	private File inputDir;
	private File outputCompressFile;

	public CompressionStrategy getCompressionStrategy() {
		return compressionStrategy;
	}

	public void setCompressionStrategy(CompressionStrategy compressionStrategy) {
		this.compressionStrategy = compressionStrategy;
	}

	public File getInputDir() {
		return inputDir;
	}

	public void setInputDir(String inputDir) {
		File file = new File(inputDir);
		this.inputDir = file;
	}

	public File getOutputCompressFile() {
		return outputCompressFile;
	}

	public void setOutputCompressFile(String outputCompressFile) {
		File file = new File(outputCompressFile);
		this.outputCompressFile = file;
	}
	public String getCompressed()
	{
		return getCompressionStrategy().compress(inputDir, outputCompressFile);
	}
}