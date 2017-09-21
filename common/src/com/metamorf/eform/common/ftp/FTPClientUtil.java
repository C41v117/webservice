package com.metamorf.eform.common.ftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.SocketException;
import java.util.Properties;
import java.util.Vector;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.metamorf.eform.common.exception.SystemException;
import com.metamorf.eform.common.util.StringFunction;

public class FTPClientUtil {
	private static final Logger logger = LoggerFactory.getLogger(FTPClientUtil.class);

	/**
	 * Upload file into FTP/SFTP server
	 * 
	 * @param protocol - Protocol (FTP or SFTP)
	 * @param address - Hostname or ip address of FTP/SFTP server
	 * @param port - Port number (usually 21 for FTP and 22 for SFTP)
	 * @param userName - User Name
	 * @param password - Password
	 * @param sourceFilePath - Source file path
	 * @param destFolderPath - Destination folder in server (could be a virtual directory path) 
	 */
	public static void uploadFile(String protocol, String address, int port, String userName, String password, String sourceFilePath, String destFolderPath) 
		throws SystemException {
		if ("FTP".equalsIgnoreCase(protocol)) {
			ftpUploadFile(address, port, userName, password, sourceFilePath, destFolderPath);
		}
		else if ("SFTP".equalsIgnoreCase(protocol)) {
			sftpUploadFile(address, port, userName, password, sourceFilePath, destFolderPath);
		}
		else {
			logger.error("Internal error", "Invalid protocol: " + protocol);
			throw new SystemException("Invalid protocol: " + protocol);
		}
	}
	
	/**
	 * Upload file into FTP/SFTP server
	 * 
	 * @param protocol - Protocol (FTP or SFTP)
	 * @param address - Hostname or ip address of FTP/SFTP server
	 * @param port - Port number (usually 21 for FTP and 22 for SFTP)
	 * @param userName - User Name
	 * @param password - Password
	 * @param sourceFilePath - Source file path in sftp server (could be a virtual directory path)
	 * @param destFolderPath - Destination folder in local
	 * @param fileName - ex: test.csv (if null the system will get all the data)
	 */
	public static void downloadFile(String protocol, String address, int port, String userName, String password, String sourceFilePath, String destFolderPath, String fileName) 
		throws SystemException {
		if ("FTP".equalsIgnoreCase(protocol)) {}
		else if ("SFTP".equalsIgnoreCase(protocol)) {
			sftpDownloadFile(address, port, userName, password, sourceFilePath, destFolderPath,fileName);
		}
		else {
			logger.error("Internal error", "Invalid protocol: " + protocol);
			throw new SystemException("Invalid protocol: " + protocol);
		}
	}
	
	public static void sftpDownloadFile(String address, int port, String userName, String password, String sourceFilePath, String destFolderPath, String fileName){
		logger.info("BEGIN SFTP Downloading file: " + destFolderPath);
		Session     session     = null;
		Channel     channel     = null;
		ChannelSftp channelSftp = null;
		try{
			JSch jsch = new JSch();
			session = jsch.getSession(userName,address,port);
			session.setPassword(password);
			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			
			session.connect();
			channel = session.openChannel("sftp");
			channel.connect();
			channelSftp = (ChannelSftp)channel;
			channelSftp.cd(sourceFilePath);
			@SuppressWarnings("unchecked")
			Vector<ChannelSftp.LsEntry> list = channelSftp.ls(fileName);
			
			if (list.size() != 0){
				for(ChannelSftp.LsEntry entry : list) {
					channelSftp.get(entry.getFilename(), destFolderPath +"/"+ entry.getFilename());
				}
			} else{
				channelSftp.get(fileName, destFolderPath +"/"+ fileName);
			}
		} catch(Exception ex){
			logger.error("System Exception: ", ex);
			throw new SystemException(ex);
		} finally {
			if(channelSftp!=null){
				if (channelSftp.isConnected()) {
		            try {
		                session.disconnect();
		                channel.disconnect();
		                channelSftp.quit();
		            } catch (Exception ioe) {
		                logger.error("Error close connection: ", ioe);
		                throw new SystemException(ioe);
		            }
		        }
			}
		}
		logger.info("END SFTP Downloading file: " + destFolderPath);
	}
	
	public static void ftpUploadFile(String address, int port, String userName, String password, String sourceFilePath, String destFolderPath)  {
		FileInputStream fis = null;

		logger.info("BEGIN FTP Uploading file: " + sourceFilePath);
		
		FTPClient ftpClient = new FTPClient();
		try {
			logger.info("Connecting to: " + userName + "@" + address + ":" + port);

			ftpClient.connect(address, port);

			logger.info("Connect status: " + ftpClient.getReplyString());

			boolean success = ftpClient.login(userName, password);

			logger.info("Login status: " + ftpClient.getReplyString());

			if (!success) {
				ftpClient.logout();
				return;
			}

			int replyCode = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(replyCode)) {
				logger.info("Reply status: " + ftpClient.getReplyString());
				ftpClient.disconnect();
				return;
			}

			File file = new File(sourceFilePath);

			fis = new FileInputStream(file);

			success = ftpClient.storeFile(StringFunction.addTrailingSlash(destFolderPath) + file.getName(), fis);
			if (success) {
				logger.info("Upload SUCCESS: " + sourceFilePath);
			}
			else {
				logger.info("Upload FAILED: " + ftpClient.getReplyString());
				throw new SystemException("Upload FAILED: " + ftpClient.getReplyString());
			}

			fis.close();
			ftpClient.logout();
		} catch (SocketException e) {
			logger.error("Connection error: ", e);
			throw new SystemException(e);
		} catch (IOException e) {
			logger.error("I/O error: ", e);
			throw new SystemException(e);
		} finally {
			try {
				if (fis != null) {
					fis.close();
				}
				ftpClient.disconnect();
			} catch (IOException e) {
				logger.error("I/O error: ", e);
			}
		}
		logger.info("END FTP Uploading file: " + sourceFilePath);
	}

	public static void sftpUploadFile(String address, int port, String userName, String password, String sourceFilePath, String destFolderPath) {
		logger.info("BEGIN SFTP Uploading file: " + sourceFilePath);

		Session session = null;
		ChannelSftp channel = null;

		try {
			JSch jsch = new JSch();
			session = jsch.getSession(userName, address, port);
			session.setPassword(password);
			Properties config = new Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.connect();

			channel = (ChannelSftp) session.openChannel("sftp");;
			channel.connect();

			File file = new File(sourceFilePath);
			if (file.listFiles() != null){
				for (File f:file.listFiles()){
					channel.put(new FileInputStream(f), StringFunction.addTrailingSlash(destFolderPath) + f.getName());
				}
			} else{
				channel.put(new FileInputStream(file), StringFunction.addTrailingSlash(destFolderPath) + file.getName());
			}

			logger.info("Upload SUCCESS: " + sourceFilePath);
		} catch (JSchException e) {
			logger.error("Connection error: ", e);
			throw new SystemException(e);
		} catch (FileNotFoundException e) {
			logger.error("I/O error: ", e);
			throw new SystemException(e);
		} catch (SftpException e) {
			logger.error("SFTP error: ", e);
			throw new SystemException(e);
		} finally {
			if (channel != null) {
				channel.exit();
			}
			if (session != null) {
				session.disconnect();
			}
		}
		logger.info("END SFTP Uploading file: " + sourceFilePath);
	}
}
