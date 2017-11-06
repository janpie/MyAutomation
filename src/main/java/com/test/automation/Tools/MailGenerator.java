package com.test.automation.Tools;

import java.util.Random;

public class MailGenerator {

	Random r = new Random();
	public int mailAliasNorway = 1111 + r.nextInt(99999);
	public int mailAliasDenmark = 1111 + r.nextInt(99999);
	public int mailAliasSweden = 1111 + r.nextInt(99999);
	public int mailAliasLithuania = 1111 + r.nextInt(99999);
	public int mailAliasLatvia = 1111 + r.nextInt(99999);
	public int mailAliasEstonia = 1111 + r.nextInt(99999);
	public int mailAliasPoland = 1111 + r.nextInt(99999);

	public final String generateMailNorway() {

		String as = Integer.toString(mailAliasNorway) + "N" + "@mail.com";

		return as;
	}

	public final String generateMailDenmark() {

		String as = Integer.toString(mailAliasDenmark) + "D" + "@mail.com";

		return as;
	}

	public final String generateMailSweden() {

		String as = Integer.toString(mailAliasSweden) + "S" + "@mail.com";

		return as;
	}

	public final String generateMailLithuania() {

		String as = Integer.toString(mailAliasLithuania) + "LT" + "@mail.com";

		return as;
	}
	
	public final String generateMailLatvia() {

		String as = Integer.toString(mailAliasLatvia) + "LV" + "@mail.com";

		return as;
	}
	
	public final String generateMailEstonia() {

		String as = Integer.toString(mailAliasEstonia) + "EE" + "@mail.com";

		return as;
	}
	
	public final String generateMailPoland() {

		String as = Integer.toString(mailAliasPoland) + "PL" + "@mail.com";

		return as;
	}

}
