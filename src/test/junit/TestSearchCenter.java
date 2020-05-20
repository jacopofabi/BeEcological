package test.junit;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import logic.bean.CenterBean;
import logic.controller.CenterController;

public class TestSearchCenter {
	
	private CenterBean centerBean;
	private CenterBean center;
	private CenterController centerController = new CenterController();
	
	@Before
	public void prepareData() {
		String centername = "Centro smaltimento Acacia";
		String phone = "0599665854";
		String address = "Via dei rovi";
		String num = "21";
		String city = "Alatri";
		String cap = "03011";
		
		center = new CenterBean();
		center.setCbName(centername);
		center.setCbPhone(phone);
		center.setCbAddress(address +" "+ num);
		center.setCbCity(city);
		center.setCbCap(cap);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testSearchCenter() {
		String centername = "Centro smaltimento Acacia";
		centerBean = new CenterBean();
		centerBean.setCbName(centername);
		
		List<CenterBean> centerList = centerController.centerList(centerBean);
		Object[] expected = {center.getCbName(), center.getCbPhone(), center.getCbAddress(), center.getCbCity(), center.getCbCap(), 
				center.getCbAddress()};
		Object[] result = {centerList.get(0).getCbName(), centerList.get(0).getCbPhone(), centerList.get(0).getCbAddress(), 
				centerList.get(0).getCbCity(), centerList.get(0).getCbCap(), centerList.get(0).getCbAddress()};
		
		String message = "expected: "+expected+" new: "+centerList;
		assertEquals(message, expected, result);
	}
}