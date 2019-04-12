package com.filecompression.strategy;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class StrategyClient extends JFrame
{
	JLabel fileLabel = new JLabel();
	JLabel destFile = new JLabel();
	public static void main(String[] args)
	{
		new StrategyClient().setVisible(true);
	}
	public StrategyClient()
	{
		setBounds(350,200,700,400);
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JButton chooseDir = new JButton("Choose...");
		chooseDir.setBounds(50, 50, 100, 30);
		add(chooseDir);
		
		chooseDir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int approve = fileChooser.showOpenDialog(chooseDir);
				if(approve == JFileChooser.APPROVE_OPTION)
				{
					File file = fileChooser.getSelectedFile();
					fileLabel.setText(file.toString());
					fileLabel.setBounds(50,100,200,30);
					repaint();
				}
			}
		});
		List<String> strategy = new ArrayList<String>();
		strategy.add("zip compression");
		strategy.add("jar compression");

		JComboBox<ArrayList> chooseStrategy = new JComboBox(strategy.toArray());
		chooseStrategy.setBounds(180,50,150,30);
		add(chooseStrategy);
		
		JButton saveAs = new JButton("Save As");
		saveAs.setBounds(350,50,150,30);
		add(saveAs);
		JButton operation = new JButton("Compress");
		operation.setBounds(530,50,150,30);
		add(operation);
		
		saveAs.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				int save = fileChooser.showSaveDialog(saveAs);
				if(save==JFileChooser.APPROVE_OPTION)
				{
					if(chooseStrategy.getSelectedItem().equals("zip compression"))
					{
						destFile.setText(fileChooser.getSelectedFile().toString()+".zip");
					}
					else if(chooseStrategy.getSelectedItem().equals("jar compression"))
					{
						destFile.setText(fileChooser.getSelectedFile().toString()+".jar");
					}
					destFile.setBounds(350,100,300,30);
					repaint();
				}
			}
		});
		
		operation.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				Compression compress = new Compression();
				if(chooseStrategy.getSelectedItem().equals("zip compression"))
				{
					compress.setCompressionStrategy(new ZipCompression());
				}
				else if(chooseStrategy.getSelectedItem().equals("jar compression"))
				{
					compress.setCompressionStrategy(new JarCompression());
				}
				compress.setInputDir(fileLabel.getText().toString());
				compress.setOutputCompressFile(destFile.getText().toString());
				JOptionPane.showMessageDialog(null, compress.getCompressed());
			}
		});
	}
	
	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		add(fileLabel);
		add(destFile);
	}
}