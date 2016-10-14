package azureStorage;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;

//Include the following imports to use blob APIs.
import com.microsoft.azure.storage.*;
import com.microsoft.azure.storage.file.*;

public class testMain {
	
	// Configure the connection-string with your values
	public static final String storageConnectionString =
	    "DefaultEndpointsProtocol=http;" +
	    "AccountName=ecostoragehwk;" +
	    "AccountKey=RPoDvcYjGeRfwRIlxUiQzIjfdsUuJY0yCbsIOKPtSUeR03NzGdymgJYEBr8q4SpYbb/QdRtXhjSVgTcFw6P6Sw==";
	

	public static void main(String[] args) {
		// Use the CloudStorageAccount object to connect to your storage account
		try {
			System.out.println("inicio..");
		    @SuppressWarnings("unused")
			CloudStorageAccount storageAccount = CloudStorageAccount.parse(storageConnectionString);
		    
		    
		 // Create the file storage client.
		    CloudFileClient fileClient = storageAccount.createCloudFileClient();
		    
		 // Get a reference to the file share
		    CloudFileShare share = fileClient.getShareReference("grabaciones");
		    
		  //Get a reference to the root directory for the share.
		    CloudFileDirectory rootDir = share.getRootDirectoryReference();
		    
		 // Define the path to a local file.
		    final String filePath = "/Users/andresbenitez/Documents/IMG_1690.JPG";

		    CloudFile cloudFile = rootDir.getFileReference("IMG_1690.JPG");
		    cloudFile.uploadFromFile(filePath);
		    

		    //Get a reference to the sampledir directory
		    CloudFileDirectory sampleDir = rootDir.getDirectoryReference("sampledir");

		    if (sampleDir.createIfNotExists()) {
		        System.out.println("sampledir created");
		    } else {
		        System.out.println("sampledir already exists");
		    }
		    
		    /**
		     * Enumeración de archivos y directorios en un recurso compartido
		     */
		    
		  //Get a reference to the root directory for the share.
		    //CloudFileDirectory rootDir = share.getRootDirectoryReference();

		    for ( ListFileItem fileItem : rootDir.listFilesAndDirectories() ) {
		        System.out.println(fileItem.getUri());
		    }
		    
		    /**
		     * Descarga de un archivo
		     */
		    
		  //Get a reference to the root directory for the share.
		    //CloudFileDirectory rootDir = share.getRootDirectoryReference();

		    //Get a reference to the directory that contains the file
		    //CloudFileDirectory sampleDir = rootDir.getDirectoryReference("sampledir");

		    //Get a reference to the file you want to download
		    //CloudFile file = sampleDir.getFileReference("SampleFile.txt");
		    CloudFile file = rootDir.getFileReference("IMG_1690.JPG");

		    //Write the contents of the file to the console.
		    System.out.println(file.downloadText());
		    
		    /**
		     * Eliminación de un archivo
		     */
		    
		 // Get a reference to the root directory for the share.
		    //CloudFileDirectory rootDir = share.getRootDirectoryReference();

		    // Get a reference to the directory where the file to be deleted is in
		    CloudFileDirectory containerDir = rootDir.getDirectoryReference("sampledir");

//		    String filename = "IMG_1690.JPG";
//		    CloudFile file2;
//
//		    file2 = rootDir.getFileReference(filename);
//		    if ( file2.deleteIfExists() ) {
//		        System.out.println(filename + " was deleted");
//		    }
		    
		    /**
		     * Eliminación de un directorio
		     */
		 // Get a reference to the root directory for the share.
		    //CloudFileDirectory rootDir = share.getRootDirectoryReference();

		    // Get a reference to the directory you want to delete
		    //CloudFileDirectory containerDir = rootDir.getDirectoryReference("sampledir");

		    // Delete the directory
		    if ( containerDir.deleteIfExists() ) {
		        System.out.println("Directory deleted");
		    }
		    
		    /**
		     * Eliminación de un recurso compartido
		     */
		    try
		    {
		        // Retrieve storage account from connection-string.
		        //CloudStorageAccount storageAccount = CloudStorageAccount.parse(storageConnectionString);

		        // Create the file client.
		       //CloudFileClient fileClient = storageAccount.createCloudFileClient();

		       // Get a reference to the file share
		       //CloudFileShare share = fileClient.getShareReference("sampleshare");

//		       if (share.deleteIfExists()) {
//		           System.out.println("sampleshare deleted");
//		       }
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    
		    
		    System.out.println("termino...");
		} catch (java.security.InvalidKeyException | URISyntaxException | StorageException | IOException invalidKey) {
		    // Handle the exception
			System.out.println("Error: ");
		}

	}

}
