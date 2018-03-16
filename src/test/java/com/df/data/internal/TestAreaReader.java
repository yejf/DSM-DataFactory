package com.df.data.internal;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.df.data.Area;

public class TestAreaReader {

	@Test
	@Ignore
	public void testRead(){
		IDataReader<Area> idr = new AreaReader();
		List<Area> areaList = idr.read();
		System.out.println(" 共计省份： "+areaList.size()+" 个");
		for(Area province : areaList){
			System.out.printf("%s 共计有[%d]个城市.\n", province.getName(),province.getChildren().size());
			List<Area> cityList = province.getChildren();
			for(Area city : cityList){
				System.out.println("\t"+city);
				List<Area> areaList2 = city.getChildren();
				if(areaList2 == null){
					System.out.println("\t\t没有区域");
				}else{
					//输出区域
					for(Area area2 : areaList2){
						System.out.println("\t\t"+area2);
					}
				}
			}
		}
	}
}
