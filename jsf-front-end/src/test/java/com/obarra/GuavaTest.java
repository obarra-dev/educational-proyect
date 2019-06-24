package com.obarra;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

public class GuavaTest {
	
	@Test
	public void joiner() {
		String numberJoined = Joiner.on(",")
		         .skipNulls()
		         .join(Arrays.asList(1,2,3,4,5,null,6));
		
		Assert.assertEquals("1,2,3,4,5,6", numberJoined);
	}
	
	@Test
	public void spliter() {
		Iterable<String> splitted = Splitter.on(',').trimResults().omitEmptyStrings()
				.split("the ,quick, ,brown, fox, jumps, over, the, lazy, little dog.");

		ArrayList<String> list = Lists.newArrayList(splitted);

		Assert.assertEquals("the", list.get(0));
		Assert.assertEquals("little dog.", list.get(8));
	}

}
