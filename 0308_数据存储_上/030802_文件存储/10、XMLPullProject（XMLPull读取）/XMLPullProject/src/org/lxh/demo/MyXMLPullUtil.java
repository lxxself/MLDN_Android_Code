package org.lxh.demo;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

public class MyXMLPullUtil {
	private InputStream input = null ;
	public MyXMLPullUtil(InputStream input) {
		this.input = input ;
	}
	public List<LinkMan> getAllLinkMan() throws Exception{
		List<LinkMan> all = null ;
		LinkMan man = null ;
		String elementName = null ;	// 保存节点的名称
		XmlPullParserFactory factory = XmlPullParserFactory.newInstance() ;
		XmlPullParser xpp = factory.newPullParser() ;
		xpp.setInput(this.input, "UTF-8") ;
		int eventType = xpp.getEventType() ;	// 取得事件码
		while(eventType != XmlPullParser.END_DOCUMENT) {	// 不是文档底部
			if(eventType == XmlPullParser.START_DOCUMENT) {	// 文档开始
				all = new ArrayList<LinkMan>() ;
			} else if (eventType == XmlPullParser.START_TAG) {	// 元素标记开始
				elementName = xpp.getName() ;	// 取得元素的名称
				if("linkman".equals(elementName)) {
					man = new LinkMan() ;
				}
			} else if (eventType == XmlPullParser.END_TAG) {	// 结束元素
				elementName = xpp.getName() ;	// 取得节点名称
				if("linkman".equals(elementName)) {
					all.add(man) ;
					man = null ;
				}
			} else if (eventType == XmlPullParser.TEXT) {	// 数据
				if("name".equals(elementName)) {
					man.setName(xpp.getText()) ;
				} else if ("email".equals(elementName)) {
					man.setEmail(xpp.getText()) ;
				}
			}
			eventType = xpp.next() ;	// 取得下一个事件码
		}
		return all ; 
	}
}
