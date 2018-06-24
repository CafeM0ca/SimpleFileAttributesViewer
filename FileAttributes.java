import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import static java.nio.file.LinkOption.NOFOLLOW_LINKS;
import java.util.Locale;
import java.util.Scanner;



interface OSdetect{
	public String getOperatingSystemName();
}

abstract class OSinfo implements OSdetect{
	
	public abstract void inputFilePath();
}

public class FileAttributes extends OSinfo implements OSdetect {
	public Path path; 
	public String getOperatingSystemName() { 
		String osName = System.getProperty("os.name").toLowerCase(Locale.ROOT); 
		if(osName.startsWith("windows")) 
			return "windows"; 
		else if(osName.startsWith("linux")) 
			return "linux"; 
		else if(osName.startsWith("mac os")) 
			return "mac"; 
		else 
			return "no apply";
	}

	FileAttributes() { 
		switch(getOperatingSystemName()) { 
			case "linux" : 
				System.out.println("Apply OS: linux");
				this.inputFilePath();
					setCreationTime();
					setLastAccessTime();
					setLastModifiedTime();
					setIsDirectory();
					setIsRegularFile();
					setIsSymbolicLink();
				
		} 
	} 
	public void inputFilePath() { 
		Scanner scan = new Scanner(System.in);	
		System.out.printf("Input File location: "); 
		String filepath = scan.nextLine(); 
		System.out.printf("Input File Name: "); 
		String filename = scan.nextLine();
		path = Paths.get(filepath,filename);
	}
	private long fsize;
	private FileTime creationTime;
	private FileTime lastAccessTime;
	private FileTime lastModifiedTime;
	private boolean isDirectory;
	private boolean isRegularFile;
	private boolean isSymbolicLink;

	public void setCreationTime() {
		try{
			fsize = (Long) Files.getAttribute(path, "basic:size", NOFOLLOW_LINKS);
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}
	public void setLastAccessTime() {
		try{
			creationTime = (FileTime)Files.getAttribute(path, "basic:creationTime", NOFOLLOW_LINKS);
		}
		catch (IOException e){
			e.printStackTrace();
		}	
	}
	public void setLastModifiedTime() {
		try{
			lastModifiedTime = (FileTime) Files.getAttribute(path, "basic:lastModifiedTime", NOFOLLOW_LINKS);
		}
		catch (IOException e){
			e.printStackTrace();
		}

	}
	public void setIsDirectory() {
		try{
			isDirectory = (Boolean) Files.getAttribute(path, "basic:isDirectory", NOFOLLOW_LINKS);
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}
	public void setIsRegularFile() {
		try{
			isRegularFile = (Boolean) Files.getAttribute(path, "basic:isRegularFile", NOFOLLOW_LINKS);
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}
	public void setIsSymbolicLink() {
		try{
			isSymbolicLink = (Boolean) Files.getAttribute(path, "basic:isSymbolicLink", NOFOLLOW_LINKS);
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}


	public final long getFsize() { return fsize; }
	public final FileTime getCreationTime() { return creationTime; }
	public final FileTime getLastAccessTime() { return lastAccessTime; }
	public final FileTime getLastModifiedTime() { return lastModifiedTime; }
	public final boolean getIsDirectory() { return isDirectory; }
	public final boolean getIsRegularFile() { return isRegularFile; }
	public final boolean getIsSymbolicLink() { return  isSymbolicLink; }
	
	public final void printFileAttributes() {
			System.out.println("[***FileInfo***]");
			System.out.printf("size(byte)               : %d \n", getFsize());
			System.out.printf("created                  : %s \n", getCreationTime());
			System.out.printf("LastAccess               : %s \n", getLastAccessTime());
			System.out.printf("LastModified             : %s \n", getLastModifiedTime());
			System.out.printf("Directory?               : %s \n", getIsDirectory());
			System.out.printf("File?                    : %s \n", getIsRegularFile());
			System.out.printf("symboliclink?            : %s \n", getIsSymbolicLink());
	}
	public static void main(String[] args){
			FileAttributes fatb = new FileAttributes();
			fatb.printFileAttributes();	
	}	
}

