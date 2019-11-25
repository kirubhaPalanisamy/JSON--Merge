import org.json.simple.JSONArray; 
import org.json.simple.JSONObject; 
import org.json.simple.parser.*;
import java.io.FileWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.util.*;

public class MergerUtility {
	// This check function is to validate if the given string is a valid file/directory
	static int check(String filename)                                          
	{
		int flag =0;
		File filechk = new File(filename);
		if(filechk.isFile())
		{
			flag = 1;
		}
		else if(filechk.isDirectory())
		{
			flag = 2;
		}
		if(flag!=1 && flag !=2)
		{
			System.out.println(filename+"is not a valid file/directory");
		}
		return flag;
	}

	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException{
		//Declaration of variables
		
		Scanner in = new Scanner(System.in);
		int noOfFiles,flag,i[] = {0},cnt=1;
		double fileSize;
		String key[]= {" "},dir,filename;
		JSONObject filen[] = new JSONObject[10];
	    JSONArray arr[] = new JSONArray[10];
	    JSONArray json = new JSONArray();
	    JSONObject newFile = new JSONObject();

		
		//Get the number of files the user wants to merge
		
		System.out.println("Enter the number of files you want to merge : ");
		noOfFiles = in.nextInt();
		
		//Get the path of the directory where all JSON files are
		do {
			System.out.println("Enter the path where all the JSON files are stored(Format : C:\\xamppp\\\\htdocs\\\\project\\) : " );
			dir = in.next();
			// replacing all \,\\ braces with / in path 
			dir = dir.replaceAll("\\\\","/");
			//checking if given path is a valid directory
			flag = check(dir);
		}while(flag!=2);
	    
		//Get the input base file name(prefix)
		System.out.println("Enter the prefix of all file names : ");
	    filename = in.next();
	    
	    //Merging the Files
	    for(i[0]=1;i[0]<=noOfFiles;i[0]++)
		{  	
	    	filen[i[0]] = (JSONObject) new JSONParser().parse(new FileReader(dir+filename+i[0]+".json"));
	    	filen[i[0]].keySet().forEach(keyStr ->
	    		{   key[0] = (String)keyStr;
	    			arr[i[0]] = (JSONArray) filen[i[0]].get(keyStr);
	    			for(int j=0;j<arr[i[0]].size();j++)
	    			{
	    			json.add(arr[i[0]].get(j));
	    			}
	    		});
		}
	    
	    //Creating and adding the merged file to the same directory as the files were initally present
	 	newFile.put(key[0],json);
		System.out.print(newFile);
		try (FileWriter file = new FileWriter(dir+"merge"+(cnt++)+".json")) {
		file.write(newFile.toJSONString());
		File fl = new File(dir+"merge"+cnt+".json"); 
		fileSize = fl.length();
		cnt++;
		}
	    

		
	}

}
