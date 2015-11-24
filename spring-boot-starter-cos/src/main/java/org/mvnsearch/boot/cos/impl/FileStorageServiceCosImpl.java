package org.mvnsearch.boot.cos.impl;

import com.qcloud.cos.api.BucketOperation;
import com.qcloud.cos.api.CosCloud;
import org.mvnsearch.boot.cos.CosProperties;
import org.mvnsearch.boot.cos.FileStorageService;

import javax.activation.DataSource;
import javax.activation.MimetypesFileTypeMap;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

/**
 * file storage service COS implementation
 *
 * @author linux_china
 */
public class FileStorageServiceCosImpl implements FileStorageService {
    String bucketName;
    public static MimetypesFileTypeMap fileTypeMap = new MimetypesFileTypeMap();
    public static String DEFAULT_CONTENT_TYPE = "application/octet-stream";
    public static final AtomicLong fileUploadSuccess = new AtomicLong();
    public static final AtomicLong fileUploadFail = new AtomicLong();
    public static final AtomicLong fileGetCounts = new AtomicLong();
    public static final AtomicLong fileDeleteCounts = new AtomicLong();
    /**
     * oss client
     */
    private BucketOperation bucketOperation;

    public FileStorageServiceCosImpl(CosCloud cosCloud, CosProperties cosProperties) {
        this.bucketOperation = cosCloud.getBucketOperation(cosProperties.getBucketName());
    }

    /**
     * save file
     *
     * @param ds data source
     * @return file name
     * @throws IOException IO Exception
     */
    public String save(DataSource ds) throws IOException {
        String newName;
        newName = getUuidName(ds.getName());
        upload(bucketName, newName, ds.getInputStream());
        return newName;
    }

    public String save(DataSource ds, String path) throws IOException {
        upload(bucketName, path, ds.getInputStream());
        return path;
    }

    /**
     * save file to the directory
     *
     * @param directory directory
     * @param ds        data source
     * @return file name with directory name
     * @throws IOException IO Exception
     */
    public String saveToDirectory(String directory, DataSource ds) throws IOException {
        String newName;
        newName = directory + "/" + getUuidName(ds.getName());
        upload(bucketName, newName, ds.getInputStream());
        return newName;
    }

    /**
     * delete file
     *
     * @param fileName file name
     * @throws IOException IO Exception
     */
    public void delete(String fileName) throws IOException {
        bucketOperation.deleteFile(fileName);
        fileDeleteCounts.incrementAndGet();
    }

    /**
     * get file data source
     *
     * @param fileName file name
     * @return file data source
     * @throws IOException IO Exception
     */
    public DataSource get(String fileName) throws IOException {
        return null;
    }


    public void rename(String oldName, String newName) throws IOException {

    }

    public static String getUuidName(String name) {
        String uuid = UUID.randomUUID().toString().replaceAll("\\-", "");
        String newName = uuid;
        if (name != null && name.contains(".")) {
            newName = uuid + name.substring(name.lastIndexOf(".")).toLowerCase();
        }
        return newName;
    }

    /**
     * upload to bucket
     *
     * @param bucketName  bucket name
     * @param fileName    file name
     * @param inputStream input stream
     */
    private void upload(String bucketName, String fileName, InputStream inputStream) throws IOException {
        try {
            bucketOperation.uploadStream(fileName, getContentType(fileName), inputStream);
            fileUploadSuccess.incrementAndGet();
        } catch (Exception ignore) {
            fileUploadFail.incrementAndGet();
        }
    }

    /**
     * get content type according to name or ext, default is "application/octet-stream"
     *
     * @param fileNameOrExt file name or ext name
     * @return content type
     */
    public String getContentType(String fileNameOrExt) {
        if (fileNameOrExt == null || fileNameOrExt.isEmpty()) {
            return DEFAULT_CONTENT_TYPE;
        }
        return fileTypeMap.getContentType(fileNameOrExt.toLowerCase());
    }


}
