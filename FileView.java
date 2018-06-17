import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
class FileView{
    private String path;
    private Path file;
    private BasicFileAttributes battr;
    
    public FileView(String _p){
        path = _p;
        file = Path.get(_p);
        battr = Files.readAttributes(file,BasicFileAttributes.class);
    }
    final public getCreationTime(){
        return battr.creationTime();
    }
    final public getLastAccessTime(){
        return battr.lastAccessTime();
    }
    final public getLastModifiedTime(){
        return battr.lastModifiedTime();
    }
    final public getSize(){
        return battr.size();
    }
}

