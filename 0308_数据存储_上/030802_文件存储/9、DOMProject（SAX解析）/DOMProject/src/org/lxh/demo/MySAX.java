package org.lxh.demo;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MySAX extends DefaultHandler {
	private List<LinkMan> all = null ;	// 保存多条数据
	private LinkMan man = null ;
	private String elementName = null ;	// 保存节点的名称
	
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		if(this.elementName != null) {	// 已经取得了元素名称
			String data = new String(ch,start,length) ;
			if("name".equals(this.elementName)) {
				this.man.setName(data) ;
			} else if ("email".equals(this.elementName)) {
				this.man.setEmail(data) ;
			}
		}
	}

	
	
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if("linkman".equals(localName)) {
			this.all.add(this.man) ;
			this.man = null ;	// 准备保存下次的数据
		}
		this.elementName = null;// 把元素名称清空
	}

	@Override
	public void startDocument() throws SAXException {
		this.all = new ArrayList<LinkMan>() ;	// 表示开始解析文档，所以要设置集合
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if("linkman".equals(localName)) {	// 是一个linkman节点
			this.man = new LinkMan() ;	// 实例化LinkMan对象
		}
		this.elementName = localName ;	// 保存元素名称
	}

	public List<LinkMan> getAll() {
		return all;
	}
	
}
