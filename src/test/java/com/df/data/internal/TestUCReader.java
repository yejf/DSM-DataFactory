package com.df.data.internal;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.df.data.UniversalCharacter;

public class TestUCReader {

	@Test
	@Ignore
	public void testRead(){
		IDataReader<UniversalCharacter> idr = new UniversalCharacterReader();
		List<UniversalCharacter> ucList = idr.read();
		System.out.println("共计通用汉字有："+ucList.size());
		for(UniversalCharacter uc : ucList){
			System.out.println(uc);
		}
	}
}
