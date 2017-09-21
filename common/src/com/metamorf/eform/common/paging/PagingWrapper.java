/*
 * Created on Apr 17, 2004
 *
 */
package com.metamorf.eform.common.paging;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Wrap result for Paging Helper
 * @author denny
 */
public class PagingWrapper<T> implements Serializable {
    @SuppressWarnings("rawtypes")
	private static final List EMPTY_LIST = new LinkedList();

    private static final long serialVersionUID = 1322545907045080110L;
    private List<T> result = new LinkedList<T>();
    private int currentPage;
    private int noOfPage;
    /** total number of result */
    private long maxRecord;
    /** result start from */
    private int startRecordIndex;
    private int recordsPerPage;
    private long totalRecord;

    @SuppressWarnings("unchecked")
    public PagingWrapper() {
        this.result = EMPTY_LIST;
        this.currentPage = 1;
        this.noOfPage = 1;
    }

    public PagingWrapper(List<T> _result, long _totalRecords, int _start, int _size){  // NOPMD by AGE-SYSTEM on 9/30/13 10:16 AM, this is always like this from old legacy, not going to fix for now
    	this.result = _result;
    	this.maxRecord = _totalRecords;
    	this.startRecordIndex = _start;
    	this.recordsPerPage = _size;
    	
    	if (_size <= 0) return;
        int imod = _start % _size;
        int idiv = _start / _size;
        if(imod == 0){
            currentPage = idiv;
        } else{
            currentPage = idiv + 1;
        }

//        LOGGER.debug("imod {}",imod);
//        LOGGER.debug("idiv {}",idiv);
//        LOGGER.debug("current page {}",currentPage);

        //calculation to determine number of page
        imod = (int)_totalRecords % _size;
        idiv = (int)_totalRecords / _size;
        if(imod == 0){
            noOfPage = idiv;
        } else{
            noOfPage = idiv + 1;
        }

        if (_totalRecords < _start) {
            currentPage = noOfPage;
            _start = ((currentPage - 1) * _size) + 1;
        }
        
        totalRecord = _totalRecords;
    }
    
    public boolean isResultEmpty() {
        return (result == null || result.isEmpty());
    }

    /**
     * @return Returns the currentPage.
     */
    public int getCurrentPage() {
        return currentPage;
    }
    /**
     * @param currentPage The currentPage to set.
     */
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
    /**
     * @return Returns the maxRecord.
     */
    public long getMaxRecord() {
        return maxRecord;
    }
    /**
     * @param maxRecord The maxRecord to set.
     */
    public void setMaxRecord(int maxRecord) {
        this.maxRecord = maxRecord;
    }
    /**
     * @return Returns the noOfPage.
     */
    public int getNoOfPage() {
        return noOfPage;
    }
    /**
     * @param noOfPage The noOfPage to set.
     */
    public void setNoOfPage(int noOfPage) {
        this.noOfPage = noOfPage;
    }
    /**
     * @return Returns the result.
     */
    public List<T> getResult() {
        return result;
    }
    /**
     * @param result The result to set.
     */
    public void setResult(List<T> result) {
        this.result = result;
    }
    /**
     * @return Returns the startRecordIndex.
     */
    public int getStartRecordIndex() {
        return startRecordIndex;
    }
    /**
     * @param startRecordIndex The startRecordIndex to set.
     */
    public void setStartRecordIndex(int startRecordIndex) {
        this.startRecordIndex = startRecordIndex;
    }


    /**
     * @return number of records per page
     */
    public int getRecordsPerPage() {
        return recordsPerPage;
    }

    /**
     * Set number of records per page
     * @param i number of records per page
     */
    public void setRecordsPerPage(int i) {
        recordsPerPage = i;
    }

    public long getTotalRecord() {
		return totalRecord;
	}

	/**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new StringBuffer(PagingWrapper.class.getName())
            .append(" [startRecordIndex = ").append(this.startRecordIndex)
            .append(", resultEmpty = ").append(this.isResultEmpty())
            .append(", noOfPage = ").append(this.noOfPage)
            .append(", maxRecord = ").append(this.maxRecord)
            .append(", currentPage = ").append(this.currentPage)
            .append(", recordsPerPage = ").append(this.recordsPerPage)
            .append(", totalRecord = ").append(this.totalRecord)
            .append("]")
            .toString();
    }
}
