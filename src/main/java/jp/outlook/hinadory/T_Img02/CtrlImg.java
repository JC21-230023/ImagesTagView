package jp.outlook.hinadory.T_Img02;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.adobe.internal.xmp.XMPException;
import com.adobe.internal.xmp.XMPMeta;
import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.xmp.XmpDirectory;


public class CtrlImg {
	List<File> imgsPath;
	static File INFS=
		new File("./src/main/java/jp/outlook/T_Img02/imgFold/folderInf.json");
	
	CtrlImg(){
	}
	public String[] getImageNames() {
		String[] ans=((File) imgsPath).list();
		return ans;
	}
	public List<String[]> getImgTags(String imgName){
		String nameSpace="http://purl.org/dc/elements/1.1/";
		Function<XMPMeta, String[]> getTags=(XMPMeta xmpMD)->{
			try {return IntStream.range(1,xmpMD.countArrayItems(nameSpace,"subject"))
					.mapToObj(i -> {
						try{return xmpMD.getArrayItem(nameSpace,"subject",i).getValue();
						}catch(XMPException e){e.printStackTrace();
						return "";
						}}).toArray(String[]::new);
			}catch(XMPException e){e.printStackTrace();
			return new String[]{};
		}};
		List<String[]>ans= Arrays.asList(new String[][]{{},{}});
		
		try {
			ans=ImageMetadataReader.readMetadata(
				new File(imgName))
			.getDirectoriesOfType(XmpDirectory.class)
			.stream()
			//画像のみをフィルターで通す
			.map(xmpDir-> xmpDir.getXMPMeta())
			.map(getTags)
			.collect(Collectors.toList());
				
		}catch (Exception e) {
			// TODO: handle exception
		}
		return ans;
	}
	
}
