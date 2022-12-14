package Controller; 
import java.io.*;


/**
	* This abstract contains the file paths which store data files 
	* This abstract class contains only two methods - to read a serializable object from a file 
	* to write a serializable object into a file.
 */
public abstract class DataController implements Controller{
		

		/**
		 * This method reads a serializble object from a given file name.
		 * @param fileName the file address to be read
		 * @return serializable object read from the file. null if file or class cannot be found
		 * @throws FileNotFoundException if the file cannot be found
	 	 * @throws IOException if there are errors in file handling 
		 * @throws ClassNotFoundException if the class is not found. 
		 */
		public static Object readFile(String fileName) throws FileNotFoundException, IOException, ClassNotFoundException {
			Object obj; 
			FileInputStream fileInput; 
			ObjectInputStream objInput; 
			
			try{
				fileInput = new FileInputStream(fileName); 
				objInput = new ObjectInputStream(fileInput); 
				obj = objInput.readObject(); 
				objInput.close(); 
				return obj; 
			}
			catch (EOFException e){
				return null; 
			}

		}



		
	/**
	 * This method writes a serializble object into a given file. 
	 * @param fileName the file address to write the data into
	 * @param data the data to be written into the file 
	 * @throws FileNotFoundException if the file cannot be found
	 * @throws IOException if there are erors in file handling 
	 */
		public static void writeFile(String fileName, Object data) throws FileNotFoundException,IOException {
				FileOutputStream fileOutput; 
				ObjectOutputStream objOutput; 
					
				fileOutput = new FileOutputStream(fileName); 
				objOutput  = new ObjectOutputStream(fileOutput); 
				objOutput.writeObject(data); 
				objOutput.close(); 
		
		}

}
