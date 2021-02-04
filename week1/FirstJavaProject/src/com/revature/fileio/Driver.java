package com.revature.fileio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Driver {
	
	private static final String FILE_NAME = "files/myfile.csv";
	
	private FileOutputStream fos;
	private FileInputStream fis;
	
	private FileReader fr;
	private FileWriter fw;
	
	private BufferedReader br;
	private BufferedWriter bw;

	public static void main(String[] args) {
		
		Driver d = new Driver();
		
		try {
			d.fosExample();
			d.fisExample();
			d.fileReaderWriterExample();
			d.bufferedReaderWriterExample();
			
			d.br.close();
			
			throw new ClassNotFoundException("Something went wrong!");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			
		}
		
		System.out.println("The program reached this point!");
	}

	private void fosExample() throws IOException {
		
		fos = new FileOutputStream(FILE_NAME);
		
		char rand = 0;
		
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				rand = (char) (Math.random() * 26 + 'a');
				// A random letter from a to z (lowercase)
				
				// FOS and FIS can only read/write a single byte at a time
				fos.write((byte) rand);
				
				// If we are not at the end of the row
				// write a comma
				if(j < 4) {
					fos.write((byte) ',');
				}
			}
			
			fos.write((byte) '\n');
		}
		
		fos.close();
	}
	
	private void fisExample() throws IOException {
		
		fis = new FileInputStream(FILE_NAME);
		
		byte buffer;
		// While reading, if the value is -1, then we have reached the end of the file
		
		// Continue looping as long as the byte that we read is not equal to -1
		while( (buffer = (byte) fis.read()) != -1 ) {
			
			char c = (char) buffer;
			
			if(c == ',') {
				// Do nothing if we read a comma
//			} else if(c == '\n') {
//				System.out.println();
//				// Print a newline character
			} else {
				System.out.print(c);
			}
		}
		
		System.out.println();
		
		fis.close();
	}
	
	private void fileReaderWriterExample() throws IOException {
		
		fr = new FileReader(FILE_NAME);
		fw = new FileWriter("files/myfile.upper.csv");
		
		int buffer;
		
		while( (buffer = fr.read()) != -1) {
			if(Character.isLowerCase(buffer)) {
				fw.write(buffer - 32);
				System.out.print( (char) (buffer - 32));
			} else {
				fw.write(buffer);
				System.out.print( (char) buffer);
			}
		}
		
		System.out.println();
		
		fr.close();
		fw.close();
	}
	
	private void bufferedReaderWriterExample() throws IOException {
		
		// BufferedReader/Writers are great, because they can process entire Strings at a time
		// Not just a single byte or 2
		
		br = new BufferedReader(new FileReader(FILE_NAME));
		bw = new BufferedWriter(new FileWriter("files/myfile.writer.csv"));
		
		String line;
		// null will indicate the end of the file
		
		while( (line = br.readLine()) != null) {
			String[] letters = line.split(",");
			List<Character> characters = new ArrayList<>();
			
			for(String letter : letters) {
				char c = letter.charAt(0);
				
				characters.add(c);
			}
			
			Collections.sort(characters);
			
			StringBuilder sb = new StringBuilder();
			
			for(Character c : characters) {
				sb.append(c);
				sb.append(',');
			}
			
			// Delete the last character, which was a comma
			sb.deleteCharAt(sb.length() - 1);
			
			String newline = sb.toString();
			System.out.println(newline);
			
			bw.write(newline + "\n");
		}
		
		br.close();
		bw.close();
	}
}
