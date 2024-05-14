<%@ page language="java" contentType="text/html; charset=UTF-8" 
import="javax.servlet.http.HttpServletRequest,
org.apache.commons.fileupload.servlet.ServletFileUpload,
org.apache.commons.fileupload.disk.DiskFileItemFactory,

org.apache.commons.fileupload.FileItem,
java.util.List,
java.util.Iterator,
java.io.File" 
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>File Upload Example</title>
</head>
<body>

<%
// Check that we have a file upload request
boolean isMultipart = ServletFileUpload.isMultipartContent(request);
if (isMultipart) {
    // Create a factory for disk-based file items
    DiskFileItemFactory factory = new DiskFileItemFactory();

    // Set factory constraints
    // Example: Size threshold is 512KB where file will be stored in memory
    // Adjust this path to an actual folder where temporary files can be stored
    int sizeThreshold = 1024 * 512; // 512KB
    File repositoryPath = new File("/path/to/temporary/folder");
    factory.setSizeThreshold(sizeThreshold);
    factory.setRepository(repositoryPath);

    // Create a new file upload handler
    ServletFileUpload upload = new ServletFileUpload(factory);

    // Set overall request size constraint
    // Example: Max file size is 1MB
    upload.setFileSizeMax(1024 * 1024); // 1MB

    try {
        // Parse the request
        List<FileItem> items = upload.parseRequest(request);
        Iterator<FileItem> iter = items.iterator();

        while (iter.hasNext()) {
            FileItem item = iter.next();

            if (!item.isFormField()) {
                // Process a file upload
                String fileName = item.getName();
                // Adjust the path to where files should be saved on your server
                File uploadedFile = new File("/path/to/destination/" + fileName);
                item.write(uploadedFile);
                out.println("File uploaded successfully: " + fileName + "<br>");
            }
        }
    } catch (Exception ex) {
        out.println("Error encountered: " + ex.getMessage());
    }
} else {
    out.println("No file uploaded");
}
%>
</body>
</html>
