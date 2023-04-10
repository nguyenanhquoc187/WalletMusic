package com.walletmusic.paging;

import com.walletmusic.sort.Sorter;

public class PageRequest implements Pageble {
	
	private Integer page;
	private Integer maxPageItem;
	private String searchField;
	private String search;
	private Sorter sorter;
	
	public PageRequest(Integer page, Integer maxPageItem, Sorter sorter) {
		this.page = page;
		this.maxPageItem = maxPageItem;
		this.sorter = sorter;
	}

	public PageRequest(Integer page, Integer maxPageItem, Sorter sorter, String search, String searchField) {
		this.page = page;
		this.maxPageItem = maxPageItem;
		this.sorter = sorter;
		this.search = search;
		this.searchField = searchField;
	}

	@Override
	public Integer getPage() {
		return this.page;
	}

	@Override
	public Integer getOffset() {
		if (this.page != null && this.maxPageItem != null) {
			return (this.page - 1) * this.maxPageItem;
		}
		return null;
	}

	@Override
	public String getSearchKeyWord() {
		return search;
	}

	@Override
	public void setSearchKeyWord(String keyWord) {
		this.search = keyWord;
	}

	@Override
	public String getSearchField() {
		return searchField;
	}

	@Override
	public Integer getLimit() {
		return this.maxPageItem;
	}

	@Override
	public Sorter getSorter() {
		if (this.sorter != null) {
			return this.sorter;
		}
		return null;
	}

}
