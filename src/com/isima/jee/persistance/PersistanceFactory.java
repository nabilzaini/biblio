package com.isima.jee.persistance;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

public class PersistanceFactory {
	private static PersistenceManagerFactory pfm = JDOHelper.getPersistenceManagerFactory("transactions-optional");

	private PersistanceFactory() {
		// TODO Auto-generated constructor stub
	}

	public static PersistenceManagerFactory getPfm() {
		return pfm;
	}

}
