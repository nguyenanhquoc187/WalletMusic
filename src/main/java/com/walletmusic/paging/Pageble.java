package com.walletmusic.paging;

import com.walletmusic.sort.Sorter;

public interface Pageble {
	Integer getPage();
	Integer getOffset();
	String getSearchKeyWord();
	String getSearchField();
	Integer getLimit();
	Sorter getSorter();
}
