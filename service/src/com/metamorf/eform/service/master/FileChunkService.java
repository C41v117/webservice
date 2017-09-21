package com.metamorf.eform.service.master;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.metamorf.eform.common.data.util.Operator;
import com.metamorf.eform.common.data.util.SearchFilter;
import com.metamorf.eform.common.data.util.SearchOrder;
import com.metamorf.eform.common.data.util.SearchOrder.Sort;
import com.metamorf.eform.common.enumer.ImageType;
import com.metamorf.eform.common.exception.SystemException;
import com.metamorf.eform.common.paging.PagingWrapper;
import com.metamorf.eform.data.access.master.IFileChunkDAO;
import com.metamorf.eform.entity.master.FileChunk;
import com.metamorf.eform.interfaces.master.IFileChunkService;

@Service
public class FileChunkService implements IFileChunkService {
	Logger logger = LoggerFactory.getLogger(FileChunkService.class);
	
	IFileChunkDAO fileChunkDAO;
	
	public FileChunkService(){}
	
	public FileChunkService(IFileChunkDAO fileChunkDAO){
		this.fileChunkDAO = fileChunkDAO;
	}

	public void setFileChunkDAO(IFileChunkDAO fileChunkDAO) {
		this.fileChunkDAO = fileChunkDAO;
	}

	public IFileChunkDAO getFileChunkDAO() {
		return fileChunkDAO;
	}

	@Override
	public List<FileChunk> findAll(int startNo, int offset, List<SearchFilter> searchFilters, List<SearchOrder> searchOrders) throws SystemException {
		return fileChunkDAO.findAll(startNo, offset, searchFilters, searchOrders);
	}

	@Override
	public FileChunk findById(Long id) throws SystemException {
		return fileChunkDAO.findFileChunkById(id);
	}

	@Override
	public FileChunk findByName(String name) throws SystemException {
		FileChunk file = fileChunkDAO.findByName(name);
		return file;
	}
	
	@Override
	public List<FileChunk> findAllByPhoneNumber(String phoneNumber) throws SystemException {
		List<SearchFilter> searchFilters = new LinkedList<SearchFilter>();
		searchFilters.add(new SearchFilter(FileChunk.CUSTOMER_PHONE_NUMBER, Operator.EQUALS, phoneNumber));
		List<SearchOrder> searchOrders = new LinkedList<SearchOrder>();
		searchOrders.add(new SearchOrder(FileChunk.ID, Sort.ASC));
		List<FileChunk> fileChunks =  fileChunkDAO.findAll(0, 0, searchFilters, searchOrders);
		return fileChunks;
	}
	
	@Override
	public void saveOrUpdate(FileChunk anObject) throws SystemException {
		fileChunkDAO.saveOrUpdate(anObject);
	
	}

	@Override
	public void delete(FileChunk anObject) throws SystemException {
		// DO NOT WRITE ANY CODE HERE SINCE APPPARAMETER IS NOT SUPPOSED TO BE DELETE
		
	}

	@Override
	public List<FileChunk> findAll() throws SystemException {
		return fileChunkDAO.findAll();
	}

	@Override
	public PagingWrapper<FileChunk> findAllWithPagingWrapper(int startNo,
			int offset, List<SearchFilter> searchFilters,
			List<SearchOrder> searchOrders) throws SystemException {
		return fileChunkDAO.findAllFileChunkWithFilter(startNo, offset, searchFilters, searchOrders);
	}

	@Override
	public List<FileChunk> findReportCollection(
			List<SearchFilter> searchFilters, List<SearchOrder> searchOrders)
			throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FileChunk> findReportCollection(int startNo, int offset,
			List<SearchFilter> searchFilters, List<SearchOrder> searchOrders)
			throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FileChunk> findNewerFileChunk(int version)
			throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public void insertFileChunk(Customer customer, ImageType type, boolean isCompleted) throws SystemException{
//		FileChunk fileChunk = new FileChunk();
//		fileChunk.setCustomerId(customer.getId());
//		fileChunk.setCustomerPhoneNumber(customer.getPhoneNumber());
//		fileChunk.setIsCompleted(isCompleted);
//		fileChunk.setType(type);
//		fileChunk.setPartTotal(0);
//		fileChunk.setPartSuccess("");
//		fileChunk.setLob(customer.getProject());
//		switch(type){
//		case SELFIE:
//			fileChunk.setName(customer.getSelfiePath());
//			break;
//		case KARTUKELUARGA:
//			fileChunk.setName(customer.getFamilyCardPath());
//			break;
//		case KTP:
//			if(customer.getFlagNewCif() == SystemConstant.IS_CIF_EXISITING){
//				fileChunk.setPartTotal(1);
//				fileChunk.setPartSuccess("1");
//				fileChunk.setIsCompleted(true);
//			}
//			fileChunk.setName(customer.getKtpImagePath());
//			break;
//		case NPWP:
//			if(customer.getFlagNewCif() == SystemConstant.IS_CIF_EXISITING){
//				fileChunk.setPartTotal(1);
//				fileChunk.setPartSuccess("1");
//				fileChunk.setIsCompleted(true);
//			}
//			fileChunk.setName(customer.getNpwpImagePath());
//			break;
//		case SIGNATURE:
//			fileChunk.setName(customer.getSignatureImagePath());
//			break;
//		case APPLICANT:
//			fileChunk.setName(customer.getApplicantImagePath());
//			break;
//		case KTP2:
//			if(customer.getFlagNewCif2() == SystemConstant.IS_CIF_EXISITING){
//				fileChunk.setPartTotal(1);
//				fileChunk.setPartSuccess("1");
//				fileChunk.setIsCompleted(true);
//			}
//			fileChunk.setName(customer.getKtpImagePath2());
//			break;
//		case NPWP2:
//			if(customer.getFlagNewCif2() == SystemConstant.IS_CIF_EXISITING){
//				fileChunk.setPartTotal(1);
//				fileChunk.setPartSuccess("1");
//				fileChunk.setIsCompleted(true);
//			}
//			fileChunk.setName(customer.getNpwpImagePath2());
//			break;
//		case SIGNATURE2:
//			fileChunk.setName(customer.getSignatureImagePath2());
//			break;
//		case APPLICANT2:
//			fileChunk.setName(customer.getApplicantImagePath2());
//			break;
//		case OTHERDOCUMENT:
//			fileChunk.setName(customer.getOtherDocumentPath());
//			break;
//		case OTHERDOCUMENT2:
//			fileChunk.setName(customer.getOtherDocumentPath2());
//			break;
//		}
//		
//		fileChunkDAO.saveOrUpdate(fileChunk);
//	}
	
	@Override
	public void resetFileChunk(String fileName) throws SystemException{
		FileChunk fileChunk = fileChunkDAO.findByName(fileName);
		if(fileChunk != null){
			fileChunk = FileChunk.reset(fileChunk);
			fileChunkDAO.saveOrUpdate(fileChunk);
		}
	}
	
	@Override
	public void resetFileChunk(Long customerId, ImageType type) throws SystemException{
		FileChunk fileChunk = fileChunkDAO.findByCustomerIdAndType(customerId, type);
		if(fileChunk != null){
			fileChunk = FileChunk.reset(fileChunk);
			fileChunkDAO.saveOrUpdate(fileChunk);
		}
	}
	
	
//	@Override
//	public void resetAllFileChunk(Long customerId, Customer customer) throws SystemException{
//		if(customer.getFlagNewCif()){
//			if(customer.getIsInvalidKtpImage()){
//				resetFileChunk(customerId, ImageType.KTP);
//			}
//		}
//		if(customer.getFlagNewCif()){
//			if(customer.getIsInvalidNpwpImage()){
//				resetFileChunk(customerId, ImageType.NPWP);
//			}
//		}
//		if(customer.getIsInvalidSignatureImage()){
//			resetFileChunk(customerId, ImageType.SIGNATURE);
//		}
//		if(customer.getIsInvalidApplicantImage() != null && customer.getIsInvalidApplicantImage()){
//			resetFileChunk(customerId, ImageType.APPLICANT);
//		}
//		
//		if(customer.getWorkflowVersion() == 2){
//			if(customer.getIsInvalidOtherDocumentPath()!= null && customer.getIsInvalidOtherDocumentPath()){
//				resetFileChunk(customerId, ImageType.OTHERDOCUMENT);
//			}
//		}
//		if(customer.getIsInvalidFamilyCardPath() != null && customer.getIsInvalidFamilyCardPath()){
//			resetFileChunk(customerId, ImageType.KARTUKELUARGA);
//		}
//		if(customer.getIsInvalidSelfiePath() != null && customer.getIsInvalidSelfiePath()){
//			resetFileChunk(customerId, ImageType.SELFIE);
//		}
//		
//		//Second Applicant Name
//		if(customer.getFlagNewCif2()){
//			if(customer.getIsInvalidKtpImage2() != null && customer.getIsInvalidKtpImage2()){
//				resetFileChunk(customerId, ImageType.KTP2);
//			}
//		}
//		if(customer.getFlagNewCif2()){
//			if(customer.getIsInvalidNpwpImage2() != null && customer.getIsInvalidNpwpImage2()){
//				resetFileChunk(customerId, ImageType.NPWP2);
//			}
//		}
//		if(customer.getIsInvalidSignatureImage2() != null && customer.getIsInvalidSignatureImage2()){
//			resetFileChunk(customerId, ImageType.SIGNATURE2);
//		}
//		if(customer.getIsInvalidApplicantImage2() != null && customer.getIsInvalidApplicantImage2()){
//			resetFileChunk(customerId, ImageType.APPLICANT2);
//		}
//		if(customer.getWorkflowVersion() == 2){
//			if(customer.getIsInvalidOtherDocumentPath2() != null && customer.getIsInvalidOtherDocumentPath2()){
//				resetFileChunk(customerId, ImageType.OTHERDOCUMENT2);
//			}
//		}
//		
//	}
	
	@Override
	public void updateFileChunkName(Long customerId, ImageType type, String newFileName) throws SystemException{
		FileChunk fileChunk = fileChunkDAO.findByCustomerIdAndType(customerId, type);
		if(fileChunk != null){
//			fileChunk.setCustomerId(customerId);
			fileChunk.setName(newFileName);
			fileChunk.setIsCompleted(false);
			fileChunk.setPartSuccess("");
			fileChunk.setPartTotal(0);
			fileChunkDAO.saveOrUpdate(fileChunk);
		}
	}
	
	/**
	 * update all type image on one customer that need to be repair > updating new customer id and filename
	 */
//	@Override
//	public void updateAllFileChunkName(Long customerId, Customer customer) throws SystemException{
//		
//		if(customer.getIsInvalidKtpImage() != null && customer.getIsInvalidKtpImage()){
//			updateFileChunkName(customerId, ImageType.KTP, customer.getKtpImagePath());
//		} 
//		if(customer.getIsInvalidNpwpImage() != null && customer.getIsInvalidNpwpImage()){
//			updateFileChunkName(customerId, ImageType.NPWP, customer.getNpwpImagePath());
//		} 
//		if(customer.getIsInvalidSignatureImage() != null && customer.getIsInvalidSignatureImage()){
//			updateFileChunkName(customerId, ImageType.SIGNATURE, customer.getSignatureImagePath());
//		}
//		if(customer.getIsInvalidApplicantImage() != null && customer.getIsInvalidApplicantImage()){
//			updateFileChunkName(customerId, ImageType.APPLICANT, customer.getApplicantImagePath());
//		}
//		if(customer.getIsInvalidOtherDocumentPath()!= null && customer.getIsInvalidOtherDocumentPath()){
//			updateFileChunkName(customerId, ImageType.OTHERDOCUMENT, customer.getOtherDocumentPath());
//		}
//		
//		// Second Applicant
//		if(customer.getIsInvalidKtpImage2() != null && customer.getIsInvalidKtpImage2()){
//			updateFileChunkName(customerId, ImageType.KTP2, customer.getKtpImagePath2());
//		} 
//		if(customer.getIsInvalidNpwpImage2() != null && customer.getIsInvalidNpwpImage2()){
//			updateFileChunkName(customerId, ImageType.NPWP2, customer.getNpwpImagePath2());
//		} 
//		if(customer.getIsInvalidSignatureImage2() != null && customer.getIsInvalidSignatureImage2()){
//			updateFileChunkName(customerId, ImageType.SIGNATURE2, customer.getSignatureImagePath2());
//		}
//		if(customer.getIsInvalidApplicantImage2() != null && customer.getIsInvalidApplicantImage2()){
//			updateFileChunkName(customerId, ImageType.APPLICANT2, customer.getApplicantImagePath2());
//		}
//		if(customer.getIsInvalidOtherDocumentPath2() != null && customer.getIsInvalidOtherDocumentPath2()){
//			updateFileChunkName(customerId, ImageType.OTHERDOCUMENT2, customer.getOtherDocumentPath2());
//		}
//	}
//	
	@Override
	public boolean checkAllCompletedFileStatus(Long customerId) throws SystemException{
		List<SearchFilter> searchFilters = new LinkedList<SearchFilter>();
		searchFilters.add(new SearchFilter(FileChunk.CUSTOMER_ID, Operator.EQUALS, customerId));
		searchFilters.add(new SearchFilter(FileChunk.IS_COMPLETED, Operator.EQUALS, false));
//		if(SystemParameter.NPWP_IMAGE_REQUIRED == false){
//			searchFilters.add(new SearchFilter(FileChunk.TYPE, Operator.NOT_EQUAL, ImageType.NPWP));
//		}
		
		List<FileChunk> fileChunks =  fileChunkDAO.findAll(0, 0, searchFilters, null);
		
		if(fileChunks == null || fileChunks.isEmpty()){
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean checkAllCompletedFileStatusByTaskId(Long taskId) throws SystemException {
		List<SearchFilter> searchFilters = new LinkedList<SearchFilter>();
		searchFilters.add(new SearchFilter(FileChunk.TASK_ID, Operator.EQUALS, taskId));
		searchFilters.add(new SearchFilter(FileChunk.IS_COMPLETED, Operator.EQUALS, false));
		
		List<FileChunk> fileChunks =  fileChunkDAO.findAll(0, 0, searchFilters, null);
		
		if(fileChunks == null || fileChunks.isEmpty()){
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void deleteByTaskId(Long taskId){
		fileChunkDAO.deleteByTaskid(taskId);
	}

	@Override
	public boolean updateFileChunkForLoan(FileChunk fileChunk,
			String referenceNo) throws SystemException {
		// TODO Auto-generated method stub
		return false;
	}
}