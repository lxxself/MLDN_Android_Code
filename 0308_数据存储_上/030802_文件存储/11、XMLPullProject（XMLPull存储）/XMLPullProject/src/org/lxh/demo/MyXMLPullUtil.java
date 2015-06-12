package org.lxh.demo;

import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

public class MyXMLPullUtil {
	private List<LinkMan> all = null;
	private OutputStream output = null;

	public MyXMLPullUtil(OutputStream output, List<LinkMan> all) {
		this.output = output;
		this.all = all;
	}

	public void save() throws Exception {
		XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
		XmlSerializer xs = factory.newSerializer();
		xs.setOutput(this.output, "UTF-8");
		xs.startDocument("UTF-8", true);
		xs.startTag(null, "addresslist");// ¸ùÔªËØ
		Iterator<LinkMan> iter = this.all.iterator();
		while (iter.hasNext()) {
			LinkMan man = iter.next();
			xs.startTag(null, "linkman");
			xs.startTag(null, "name");
			xs.text(man.getName());
			xs.endTag(null, "name");
			xs.startTag(null, "email");
			xs.text(man.getEmail());
			xs.endTag(null, "email");
			xs.endTag(null, "linkman");
		}
		xs.endTag(null, "addresslist");
		xs.endDocument();
		xs.flush();
	}
}
