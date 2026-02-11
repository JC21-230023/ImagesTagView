package jp.outlook.hinadory.T_Img02;

import java.io.File;
import java.util.Arrays;

import com.adobe.internal.xmp.XMPException;
import com.adobe.internal.xmp.XMPMeta;
import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.drew.metadata.xmp.XmpDirectory;


public class App{
    public static void main( String[] args ){
    	/*
    	CtrlImg imgf=new CtrlImg();
    	Arrays.stream(imgf.getImageNames())
    		.forEach(f->{System.out.println(f);});
    	*/
    	File img=new File("./src/main/java/jp/outlook/hinadory/T_Img02/imgFold/TestSmp.png");
    	//viewSmpl1();
    	//viewSmpl2(img);
    	viewSmp3(img);
    }
    public static void viewSmpl1(){
    	try {
    	Metadata metadata = 
    		ImageMetadataReader.readMetadata(new File("./src/main/java/jp/outlook/hinadory/T_Img02/imgFold/TestSmp.png"));
    	//png画像のタグ取り出し
    	XmpDirectory xmpDir =metadata.getFirstDirectoryOfType(XmpDirectory.class);
    	if(xmpDir != null ) {
    		XMPMeta xmpM=xmpDir.getXMPMeta();
    		try {
    		    int itemCount = xmpM.countArrayItems("http://purl.org/dc/elements/1.1/", "subject");
    		    for (int i = 1; i <= itemCount; i++) {
    		        String tag = xmpM.getArrayItem("http://purl.org/dc/elements/1.1/", "subject", i).getValue();
    		        System.out.println("タグ: " + tag);
    		    }
    		} catch (XMPException e) {
    		    e.printStackTrace();
    		}
    	}else{
    		System.out.println("xmp未確認");
    	}//pngのタグ取り出しここまで
    	}catch (Exception e) {
			e.printStackTrace();
			Arrays.stream(new File("./src/main/java/jp/").listFiles())
				.map(f -> f.getName())
				.forEach(f ->{
					System.out.println(f);
				});
			
		}

    }
    
    public static void viewSmpl2(File img){//リファクタリング
    	final String URL="http://purl.org/dc/elements/1.1/";
    	try {
    	Metadata metadata = 
    		ImageMetadataReader.readMetadata(img);
    	//png画像のタグ取り出し
    	XmpDirectory xmpDir =metadata.getFirstDirectoryOfType(XmpDirectory.class);
    	if(xmpDir != null ) {
    		XMPMeta xmpM=xmpDir.getXMPMeta();
    		try {
    		    int itemCount = xmpM.countArrayItems(URL, "subject");
    		    for (int i = 1; i <= itemCount; i++) {
    		        String tag = xmpM.getArrayItem(URL,"subject",i).getValue();
    		        System.out.println("タグ: " + tag);
    		    }
    		} catch (XMPException e) {
    		    e.printStackTrace();
    		}
    	}//pngのタグ取り出しここまで
    	}catch (Exception e) {
			e.printStackTrace();
		}
    }
    public static void viewSmp3(File img) {
    	try {
    	Metadata metadata = ImageMetadataReader.readMetadata(img);
    	for (Directory dir : metadata.getDirectories()) {
    	    for (Tag tag : dir.getTags()) {
    	        System.out.println(tag);
    	    }
    	}
    	}catch(Exception e) {
    		e.printStackTrace();
    	}

    }
}

/* pngは無理みたい
IptcDirectory iptc = metadata.getFirstDirectoryOfType(IptcDirectory.class);
if(iptc != null) {
	List<String> keyW =iptc.getKeywords();
	System.out.println("tag: "+keyW);
}*/


/*全体の情報(sizeとか)
for (com.drew.metadata.Directory directory : metadata.getDirectories()) {
    for (javax.swing.text.html.HTML.Tag tag : directory.getTags()) {
        System.out.println(tag);
    }
}*/
