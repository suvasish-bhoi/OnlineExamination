package com.oec.util;

import java.util.Random;

import com.oec.dao.InstituteDAO;
import com.oec.vo.InstituteVO;

public class InstituteUtil {
	
	private static InstituteDAO instDao;
	static{
		instDao = new InstituteDAO();
	}
	
	public static String generatePassword(InstituteVO instituteVo){
		String email = instituteVo.getEmail().substring(0,4);
		String mobile = instituteVo.getMobile().substring(0,4);
		return email+mobile;
	}
	
	public static int generateClientId(){
		int client_id = Integer.parseInt(new Double(Math.random()).toString().substring(3,7));
		if(instDao.checkClientId(client_id)){
			generateClientId();
		}
		return client_id;
	}
	
	public static int getRandomNumber(){
		return new Random().nextInt();
	}

}
