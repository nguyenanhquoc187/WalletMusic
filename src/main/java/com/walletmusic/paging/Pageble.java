package com.walletmusic.paging;

import com.walletmusic.sort.Sorter;

public interface Pageble {
	Integer getPage();
	Integer getOffset();
	String getSearchKeyWord();
	void setSearchKeyWord(String keyWord);
	String getSearchField();
	Integer getLimit();
	Sorter getSorter();
}
