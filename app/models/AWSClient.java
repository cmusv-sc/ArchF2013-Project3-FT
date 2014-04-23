package models;

import java.io.File;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;

public class AWSClient {
	private final static String accessKey = "AKIAJG3VXIGEE7FYX2QQ";
	private final static String secretKey = "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";
	private final static String s3Bucket = "einstein-workflows";
	private static AmazonS3 s3;

	private AWSClient() {
	}

	private static AmazonS3 connect() {
		AWSCredentials awsCredentials = new BasicAWSCredentials(accessKey,
				secretKey);
		s3 = new AmazonS3Client(awsCredentials);
		Region usWest1 = Region.getRegion(Regions.US_WEST_1);
		s3.setRegion(usWest1);
		return s3;
	}
	
	public static void upload(File file,String fileName){
		if(s3==null){
			s3=connect();
		}
		s3.putObject(new PutObjectRequest(s3Bucket,fileName, file));
	}
}
