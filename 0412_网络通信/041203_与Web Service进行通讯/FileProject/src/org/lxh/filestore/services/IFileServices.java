package org.lxh.filestore.services;

public interface IFileServices {
	public void save(String fileName, String content) throws Exception;

	public String load(String fileName) throws Exception;
}
