package lsv.grammar;
import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

/**
*This is an Antlr grammar used to generate the parser and lexer
*/
@SuppressWarnings("all")
public class FormulaParser extends Parser {
	public static final String[] tokenNames = new String[] {
		"<invalid>", "<EOR>", "<DOWN>", "<UP>", "ALL", "ATOMIC", "CLOSE", "EXISTS", 
		"FALSE", "OPEN", "TEMPORAL", "TRUE", "UNTIL", "WS", "'!'", "'&&'", "'<=>'", 
		"'=>'", "'||'"
	};
	public static final int EOF=-1;
	public static final int T__14=14;
	public static final int T__15=15;
	public static final int T__16=16;
	public static final int T__17=17;
	public static final int T__18=18;
	public static final int ALL=4;
	public static final int ATOMIC=5;
	public static final int CLOSE=6;
	public static final int EXISTS=7;
	public static final int FALSE=8;
	public static final int OPEN=9;
	public static final int TEMPORAL=10;
	public static final int TRUE=11;
	public static final int UNTIL=12;
	public static final int WS=13;

	// delegates
	public Parser[] getDelegates() {
		return new Parser[] {};
	}

	// delegators


	public FormulaParser(TokenStream input) {
		this(input, new RecognizerSharedState());
	}
	public FormulaParser(TokenStream input, RecognizerSharedState state) {
		super(input, state);
	}

	@Override public String[] getTokenNames() { return FormulaParser.tokenNames; }
	@Override public String getGrammarFileName() { return "C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g"; }



	// $ANTLR start "query"
	// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:9:1: query returns [Formula result] : formula EOF ;
	public final Formula query() throws RecognitionException {
		Formula result = null;


		Formula formula1 =null;

		try {
			// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:9:31: ( formula EOF )
			// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:9:33: formula EOF
			{
			pushFollow(FOLLOW_formula_in_query28);
			formula1=formula();
			state._fsp--;

			match(input,EOF,FOLLOW_EOF_in_query30); 
			 result = formula1;
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return result;
	}
	// $ANTLR end "query"



	// $ANTLR start "formula"
	// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:11:1: formula returns [Formula result] : ( TRUE | FALSE | OPEN first ( op_bool second )? CLOSE | OPEN first op_bool ctl_end CLOSE | neg ctl_init | OPEN ctl_init ( op_bool ctl_end )? CLOSE | EXISTS OPEN first UNTIL second CLOSE | ALL OPEN first UNTIL second CLOSE | EXISTS OPEN first UNTIL ctl_end CLOSE | ALL OPEN first UNTIL ctl_end CLOSE | EXISTS OPEN ctl_init UNTIL ctl_end CLOSE | ALL OPEN ctl_init UNTIL ctl_end CLOSE | TEMPORAL OPEN first op_bool second CLOSE | TEMPORAL OPEN first CLOSE | TEMPORAL OPEN first op_bool ctl_end CLOSE | TEMPORAL OPEN ctl_init ( op_bool ctl_end )? CLOSE );
	public final Formula formula() throws RecognitionException {
		Formula result = null;


		Token UNTIL15=null;
		Token UNTIL18=null;
		Token UNTIL21=null;
		Token UNTIL24=null;
		Token UNTIL27=null;
		Token UNTIL30=null;
		Token TEMPORAL31=null;
		Token TEMPORAL35=null;
		Token TEMPORAL37=null;
		Token TEMPORAL41=null;
		String first2 =null;
		String second3 =null;
		ParserRuleReturnScope op_bool4 =null;
		String first5 =null;
		Formula ctl_end6 =null;
		ParserRuleReturnScope op_bool7 =null;
		boolean neg8 =false;
		Formula ctl_init9 =null;
		Formula ctl_init10 =null;
		Formula ctl_end11 =null;
		ParserRuleReturnScope op_bool12 =null;
		String first13 =null;
		String second14 =null;
		String first16 =null;
		String second17 =null;
		String first19 =null;
		Formula ctl_end20 =null;
		String first22 =null;
		Formula ctl_end23 =null;
		Formula ctl_init25 =null;
		Formula ctl_end26 =null;
		Formula ctl_init28 =null;
		Formula ctl_end29 =null;
		String first32 =null;
		String second33 =null;
		ParserRuleReturnScope op_bool34 =null;
		String first36 =null;
		String first38 =null;
		Formula ctl_end39 =null;
		ParserRuleReturnScope op_bool40 =null;
		Formula ctl_init42 =null;
		Formula ctl_end43 =null;
		ParserRuleReturnScope op_bool44 =null;

		try {
			// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:11:33: ( TRUE | FALSE | OPEN first ( op_bool second )? CLOSE | OPEN first op_bool ctl_end CLOSE | neg ctl_init | OPEN ctl_init ( op_bool ctl_end )? CLOSE | EXISTS OPEN first UNTIL second CLOSE | ALL OPEN first UNTIL second CLOSE | EXISTS OPEN first UNTIL ctl_end CLOSE | ALL OPEN first UNTIL ctl_end CLOSE | EXISTS OPEN ctl_init UNTIL ctl_end CLOSE | ALL OPEN ctl_init UNTIL ctl_end CLOSE | TEMPORAL OPEN first op_bool second CLOSE | TEMPORAL OPEN first CLOSE | TEMPORAL OPEN first op_bool ctl_end CLOSE | TEMPORAL OPEN ctl_init ( op_bool ctl_end )? CLOSE )
			int alt4=16;
			switch ( input.LA(1) ) {
			case TRUE:
				{
				alt4=1;
				}
				break;
			case FALSE:
				{
				alt4=2;
				}
				break;
			case OPEN:
				{
				int LA4_3 = input.LA(2);
				if ( (LA4_3==ATOMIC) ) {
					switch ( input.LA(3) ) {
					case 15:
						{
						int LA4_13 = input.LA(4);
						if ( (LA4_13==ATOMIC) ) {
							alt4=3;
						}
						else if ( (LA4_13==ALL||(LA4_13 >= EXISTS && LA4_13 <= TRUE)||LA4_13==14) ) {
							alt4=4;
						}

						else {
							int nvaeMark = input.mark();
							try {
								for (int nvaeConsume = 0; nvaeConsume < 4 - 1; nvaeConsume++) {
									input.consume();
								}
								NoViableAltException nvae =
									new NoViableAltException("", 4, 13, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}

						}
						break;
					case 18:
						{
						int LA4_14 = input.LA(4);
						if ( (LA4_14==ATOMIC) ) {
							alt4=3;
						}
						else if ( (LA4_14==ALL||(LA4_14 >= EXISTS && LA4_14 <= TRUE)||LA4_14==14) ) {
							alt4=4;
						}

						else {
							int nvaeMark = input.mark();
							try {
								for (int nvaeConsume = 0; nvaeConsume < 4 - 1; nvaeConsume++) {
									input.consume();
								}
								NoViableAltException nvae =
									new NoViableAltException("", 4, 14, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}

						}
						break;
					case 17:
						{
						int LA4_15 = input.LA(4);
						if ( (LA4_15==ATOMIC) ) {
							alt4=3;
						}
						else if ( (LA4_15==ALL||(LA4_15 >= EXISTS && LA4_15 <= TRUE)||LA4_15==14) ) {
							alt4=4;
						}

						else {
							int nvaeMark = input.mark();
							try {
								for (int nvaeConsume = 0; nvaeConsume < 4 - 1; nvaeConsume++) {
									input.consume();
								}
								NoViableAltException nvae =
									new NoViableAltException("", 4, 15, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}

						}
						break;
					case 16:
						{
						int LA4_16 = input.LA(4);
						if ( (LA4_16==ATOMIC) ) {
							alt4=3;
						}
						else if ( (LA4_16==ALL||(LA4_16 >= EXISTS && LA4_16 <= TRUE)||LA4_16==14) ) {
							alt4=4;
						}

						else {
							int nvaeMark = input.mark();
							try {
								for (int nvaeConsume = 0; nvaeConsume < 4 - 1; nvaeConsume++) {
									input.consume();
								}
								NoViableAltException nvae =
									new NoViableAltException("", 4, 16, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}

						}
						break;
					case CLOSE:
						{
						alt4=3;
						}
						break;
					default:
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 4, 8, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}
				}
				else if ( (LA4_3==ALL||(LA4_3 >= EXISTS && LA4_3 <= TRUE)||LA4_3==14) ) {
					alt4=6;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 4, 3, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case 14:
				{
				alt4=5;
				}
				break;
			case EXISTS:
				{
				int LA4_5 = input.LA(2);
				if ( (LA4_5==OPEN) ) {
					int LA4_10 = input.LA(3);
					if ( (LA4_10==ATOMIC) ) {
						int LA4_18 = input.LA(4);
						if ( (LA4_18==UNTIL) ) {
							int LA4_25 = input.LA(5);
							if ( (LA4_25==ATOMIC) ) {
								alt4=7;
							}
							else if ( (LA4_25==ALL||(LA4_25 >= EXISTS && LA4_25 <= TRUE)||LA4_25==14) ) {
								alt4=9;
							}

							else {
								int nvaeMark = input.mark();
								try {
									for (int nvaeConsume = 0; nvaeConsume < 5 - 1; nvaeConsume++) {
										input.consume();
									}
									NoViableAltException nvae =
										new NoViableAltException("", 4, 25, input);
									throw nvae;
								} finally {
									input.rewind(nvaeMark);
								}
							}

						}

						else {
							int nvaeMark = input.mark();
							try {
								for (int nvaeConsume = 0; nvaeConsume < 4 - 1; nvaeConsume++) {
									input.consume();
								}
								NoViableAltException nvae =
									new NoViableAltException("", 4, 18, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}

					}
					else if ( (LA4_10==ALL||(LA4_10 >= EXISTS && LA4_10 <= TRUE)||LA4_10==14) ) {
						alt4=11;
					}

					else {
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 4, 10, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 4, 5, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case ALL:
				{
				int LA4_6 = input.LA(2);
				if ( (LA4_6==OPEN) ) {
					int LA4_11 = input.LA(3);
					if ( (LA4_11==ATOMIC) ) {
						int LA4_20 = input.LA(4);
						if ( (LA4_20==UNTIL) ) {
							int LA4_26 = input.LA(5);
							if ( (LA4_26==ATOMIC) ) {
								alt4=8;
							}
							else if ( (LA4_26==ALL||(LA4_26 >= EXISTS && LA4_26 <= TRUE)||LA4_26==14) ) {
								alt4=10;
							}

							else {
								int nvaeMark = input.mark();
								try {
									for (int nvaeConsume = 0; nvaeConsume < 5 - 1; nvaeConsume++) {
										input.consume();
									}
									NoViableAltException nvae =
										new NoViableAltException("", 4, 26, input);
									throw nvae;
								} finally {
									input.rewind(nvaeMark);
								}
							}

						}

						else {
							int nvaeMark = input.mark();
							try {
								for (int nvaeConsume = 0; nvaeConsume < 4 - 1; nvaeConsume++) {
									input.consume();
								}
								NoViableAltException nvae =
									new NoViableAltException("", 4, 20, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}

					}
					else if ( (LA4_11==ALL||(LA4_11 >= EXISTS && LA4_11 <= TRUE)||LA4_11==14) ) {
						alt4=12;
					}

					else {
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 4, 11, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 4, 6, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case TEMPORAL:
				{
				int LA4_7 = input.LA(2);
				if ( (LA4_7==OPEN) ) {
					int LA4_12 = input.LA(3);
					if ( (LA4_12==ATOMIC) ) {
						switch ( input.LA(4) ) {
						case 15:
							{
							int LA4_27 = input.LA(5);
							if ( (LA4_27==ATOMIC) ) {
								alt4=13;
							}
							else if ( (LA4_27==ALL||(LA4_27 >= EXISTS && LA4_27 <= TRUE)||LA4_27==14) ) {
								alt4=15;
							}

							else {
								int nvaeMark = input.mark();
								try {
									for (int nvaeConsume = 0; nvaeConsume < 5 - 1; nvaeConsume++) {
										input.consume();
									}
									NoViableAltException nvae =
										new NoViableAltException("", 4, 27, input);
									throw nvae;
								} finally {
									input.rewind(nvaeMark);
								}
							}

							}
							break;
						case 18:
							{
							int LA4_28 = input.LA(5);
							if ( (LA4_28==ATOMIC) ) {
								alt4=13;
							}
							else if ( (LA4_28==ALL||(LA4_28 >= EXISTS && LA4_28 <= TRUE)||LA4_28==14) ) {
								alt4=15;
							}

							else {
								int nvaeMark = input.mark();
								try {
									for (int nvaeConsume = 0; nvaeConsume < 5 - 1; nvaeConsume++) {
										input.consume();
									}
									NoViableAltException nvae =
										new NoViableAltException("", 4, 28, input);
									throw nvae;
								} finally {
									input.rewind(nvaeMark);
								}
							}

							}
							break;
						case 17:
							{
							int LA4_29 = input.LA(5);
							if ( (LA4_29==ATOMIC) ) {
								alt4=13;
							}
							else if ( (LA4_29==ALL||(LA4_29 >= EXISTS && LA4_29 <= TRUE)||LA4_29==14) ) {
								alt4=15;
							}

							else {
								int nvaeMark = input.mark();
								try {
									for (int nvaeConsume = 0; nvaeConsume < 5 - 1; nvaeConsume++) {
										input.consume();
									}
									NoViableAltException nvae =
										new NoViableAltException("", 4, 29, input);
									throw nvae;
								} finally {
									input.rewind(nvaeMark);
								}
							}

							}
							break;
						case 16:
							{
							int LA4_30 = input.LA(5);
							if ( (LA4_30==ATOMIC) ) {
								alt4=13;
							}
							else if ( (LA4_30==ALL||(LA4_30 >= EXISTS && LA4_30 <= TRUE)||LA4_30==14) ) {
								alt4=15;
							}

							else {
								int nvaeMark = input.mark();
								try {
									for (int nvaeConsume = 0; nvaeConsume < 5 - 1; nvaeConsume++) {
										input.consume();
									}
									NoViableAltException nvae =
										new NoViableAltException("", 4, 30, input);
									throw nvae;
								} finally {
									input.rewind(nvaeMark);
								}
							}

							}
							break;
						case CLOSE:
							{
							alt4=14;
							}
							break;
						default:
							int nvaeMark = input.mark();
							try {
								for (int nvaeConsume = 0; nvaeConsume < 4 - 1; nvaeConsume++) {
									input.consume();
								}
								NoViableAltException nvae =
									new NoViableAltException("", 4, 22, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}
					}
					else if ( (LA4_12==ALL||(LA4_12 >= EXISTS && LA4_12 <= TRUE)||LA4_12==14) ) {
						alt4=16;
					}

					else {
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 4, 12, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 4, 7, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 4, 0, input);
				throw nvae;
			}
			switch (alt4) {
				case 1 :
					// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:12:9: TRUE
					{
					match(input,TRUE,FOLLOW_TRUE_in_formula52); 
					 result = new Formula(true);
					}
					break;
				case 2 :
					// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:13:12: FALSE
					{
					match(input,FALSE,FOLLOW_FALSE_in_formula67); 
					 result = new Formula(false);
					}
					break;
				case 3 :
					// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:14:11: OPEN first ( op_bool second )? CLOSE
					{
					boolean op = false;
					match(input,OPEN,FOLLOW_OPEN_in_formula83); 
					pushFollow(FOLLOW_first_in_formula85);
					first2=first();
					state._fsp--;

					// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:14:44: ( op_bool second )?
					int alt1=2;
					int LA1_0 = input.LA(1);
					if ( ((LA1_0 >= 15 && LA1_0 <= 18)) ) {
						alt1=1;
					}
					switch (alt1) {
						case 1 :
							// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:14:45: op_bool second
							{
							pushFollow(FOLLOW_op_bool_in_formula88);
							op_bool4=op_bool();
							state._fsp--;

							pushFollow(FOLLOW_second_in_formula90);
							second3=second();
							state._fsp--;

							op = true;
							}
							break;

					}

					match(input,CLOSE,FOLLOW_CLOSE_in_formula96); 
					 if (op) {
					        	 	  result = new Formula( first2, second3,  (op_bool4!=null?((FormulaParser.op_bool_return)op_bool4).result:null));
					        	  } else {
					        	 	  result = new Formula(first2);
					        	 	} 
					        	
					}
					break;
				case 4 :
					// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:21:11: OPEN first op_bool ctl_end CLOSE
					{
					match(input,OPEN,FOLLOW_OPEN_in_formula121); 
					pushFollow(FOLLOW_first_in_formula123);
					first5=first();
					state._fsp--;

					pushFollow(FOLLOW_op_bool_in_formula125);
					op_bool7=op_bool();
					state._fsp--;

					pushFollow(FOLLOW_ctl_end_in_formula127);
					ctl_end6=ctl_end();
					state._fsp--;

					match(input,CLOSE,FOLLOW_CLOSE_in_formula129); 
					result = new Formula(first5, ctl_end6, (op_bool7!=null?((FormulaParser.op_bool_return)op_bool7).result:null));
					}
					break;
				case 5 :
					// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:22:11: neg ctl_init
					{
					pushFollow(FOLLOW_neg_in_formula143);
					neg8=neg();
					state._fsp--;

					pushFollow(FOLLOW_ctl_init_in_formula145);
					ctl_init9=ctl_init();
					state._fsp--;

					 result = new Formula(neg8, ctl_init9); 
					}
					break;
				case 6 :
					// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:23:8: OPEN ctl_init ( op_bool ctl_end )? CLOSE
					{
					boolean op = false;
					match(input,OPEN,FOLLOW_OPEN_in_formula158); 
					pushFollow(FOLLOW_ctl_init_in_formula160);
					ctl_init10=ctl_init();
					state._fsp--;

					// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:23:44: ( op_bool ctl_end )?
					int alt2=2;
					int LA2_0 = input.LA(1);
					if ( ((LA2_0 >= 15 && LA2_0 <= 18)) ) {
						alt2=1;
					}
					switch (alt2) {
						case 1 :
							// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:23:45: op_bool ctl_end
							{
							pushFollow(FOLLOW_op_bool_in_formula163);
							op_bool12=op_bool();
							state._fsp--;

							pushFollow(FOLLOW_ctl_end_in_formula165);
							ctl_end11=ctl_end();
							state._fsp--;

							op = true;
							}
							break;

					}

					match(input,CLOSE,FOLLOW_CLOSE_in_formula171); 
					 if (op) {
					        	 	  result = new Formula( ctl_init10,  ctl_end11, (op_bool12!=null?((FormulaParser.op_bool_return)op_bool12).result:null));
					        	 	  } else {
					        	 	  result = ctl_init10;
					        	 	  } 
					}
					break;
				case 7 :
					// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:29:11: EXISTS OPEN first UNTIL second CLOSE
					{
					match(input,EXISTS,FOLLOW_EXISTS_in_formula197); 
					match(input,OPEN,FOLLOW_OPEN_in_formula199); 
					pushFollow(FOLLOW_first_in_formula201);
					first13=first();
					state._fsp--;

					UNTIL15=(Token)match(input,UNTIL,FOLLOW_UNTIL_in_formula203); 
					pushFollow(FOLLOW_second_in_formula205);
					second14=second();
					state._fsp--;

					match(input,CLOSE,FOLLOW_CLOSE_in_formula207); 
					 result = new Formula("E", first13, second14, (UNTIL15!=null?UNTIL15.getText():null));
					}
					break;
				case 8 :
					// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:30:11: ALL OPEN first UNTIL second CLOSE
					{
					match(input,ALL,FOLLOW_ALL_in_formula221); 
					match(input,OPEN,FOLLOW_OPEN_in_formula223); 
					pushFollow(FOLLOW_first_in_formula225);
					first16=first();
					state._fsp--;

					UNTIL18=(Token)match(input,UNTIL,FOLLOW_UNTIL_in_formula227); 
					pushFollow(FOLLOW_second_in_formula229);
					second17=second();
					state._fsp--;

					match(input,CLOSE,FOLLOW_CLOSE_in_formula231); 
					 result = new Formula("A", first16, second17, (UNTIL18!=null?UNTIL18.getText():null));
					}
					break;
				case 9 :
					// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:31:11: EXISTS OPEN first UNTIL ctl_end CLOSE
					{
					match(input,EXISTS,FOLLOW_EXISTS_in_formula245); 
					match(input,OPEN,FOLLOW_OPEN_in_formula247); 
					pushFollow(FOLLOW_first_in_formula249);
					first19=first();
					state._fsp--;

					UNTIL21=(Token)match(input,UNTIL,FOLLOW_UNTIL_in_formula251); 
					pushFollow(FOLLOW_ctl_end_in_formula253);
					ctl_end20=ctl_end();
					state._fsp--;

					match(input,CLOSE,FOLLOW_CLOSE_in_formula255); 
					 result = new Formula("E", first19, ctl_end20, (UNTIL21!=null?UNTIL21.getText():null));
					}
					break;
				case 10 :
					// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:32:11: ALL OPEN first UNTIL ctl_end CLOSE
					{
					match(input,ALL,FOLLOW_ALL_in_formula269); 
					match(input,OPEN,FOLLOW_OPEN_in_formula271); 
					pushFollow(FOLLOW_first_in_formula273);
					first22=first();
					state._fsp--;

					UNTIL24=(Token)match(input,UNTIL,FOLLOW_UNTIL_in_formula275); 
					pushFollow(FOLLOW_ctl_end_in_formula277);
					ctl_end23=ctl_end();
					state._fsp--;

					match(input,CLOSE,FOLLOW_CLOSE_in_formula279); 
					 result = new Formula("A", first22, ctl_end23, (UNTIL24!=null?UNTIL24.getText():null));
					}
					break;
				case 11 :
					// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:33:11: EXISTS OPEN ctl_init UNTIL ctl_end CLOSE
					{
					match(input,EXISTS,FOLLOW_EXISTS_in_formula293); 
					match(input,OPEN,FOLLOW_OPEN_in_formula295); 
					pushFollow(FOLLOW_ctl_init_in_formula297);
					ctl_init25=ctl_init();
					state._fsp--;

					UNTIL27=(Token)match(input,UNTIL,FOLLOW_UNTIL_in_formula299); 
					pushFollow(FOLLOW_ctl_end_in_formula301);
					ctl_end26=ctl_end();
					state._fsp--;

					match(input,CLOSE,FOLLOW_CLOSE_in_formula303); 
					 result = new Formula( "E",ctl_init25,  ctl_end26, (UNTIL27!=null?UNTIL27.getText():null)); 
					}
					break;
				case 12 :
					// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:34:11: ALL OPEN ctl_init UNTIL ctl_end CLOSE
					{
					match(input,ALL,FOLLOW_ALL_in_formula317); 
					match(input,OPEN,FOLLOW_OPEN_in_formula319); 
					pushFollow(FOLLOW_ctl_init_in_formula321);
					ctl_init28=ctl_init();
					state._fsp--;

					UNTIL30=(Token)match(input,UNTIL,FOLLOW_UNTIL_in_formula323); 
					pushFollow(FOLLOW_ctl_end_in_formula325);
					ctl_end29=ctl_end();
					state._fsp--;

					match(input,CLOSE,FOLLOW_CLOSE_in_formula327); 
					 result = new Formula( "A", ctl_init28,  ctl_end29, (UNTIL30!=null?UNTIL30.getText():null)); 
					}
					break;
				case 13 :
					// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:35:11: TEMPORAL OPEN first op_bool second CLOSE
					{
					TEMPORAL31=(Token)match(input,TEMPORAL,FOLLOW_TEMPORAL_in_formula342); 
					match(input,OPEN,FOLLOW_OPEN_in_formula344); 
					pushFollow(FOLLOW_first_in_formula346);
					first32=first();
					state._fsp--;

					pushFollow(FOLLOW_op_bool_in_formula348);
					op_bool34=op_bool();
					state._fsp--;

					pushFollow(FOLLOW_second_in_formula350);
					second33=second();
					state._fsp--;

					match(input,CLOSE,FOLLOW_CLOSE_in_formula352); 
					 result = new Formula((TEMPORAL31!=null?TEMPORAL31.getText():null), first32, second33, (op_bool34!=null?input.toString(op_bool34.start,op_bool34.stop):null));
					}
					break;
				case 14 :
					// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:36:11: TEMPORAL OPEN first CLOSE
					{
					TEMPORAL35=(Token)match(input,TEMPORAL,FOLLOW_TEMPORAL_in_formula366); 
					match(input,OPEN,FOLLOW_OPEN_in_formula368); 
					pushFollow(FOLLOW_first_in_formula370);
					first36=first();
					state._fsp--;

					match(input,CLOSE,FOLLOW_CLOSE_in_formula372); 
					 result = new Formula((TEMPORAL35!=null?TEMPORAL35.getText():null), first36);
					}
					break;
				case 15 :
					// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:37:11: TEMPORAL OPEN first op_bool ctl_end CLOSE
					{
					TEMPORAL37=(Token)match(input,TEMPORAL,FOLLOW_TEMPORAL_in_formula386); 
					match(input,OPEN,FOLLOW_OPEN_in_formula388); 
					pushFollow(FOLLOW_first_in_formula390);
					first38=first();
					state._fsp--;

					pushFollow(FOLLOW_op_bool_in_formula392);
					op_bool40=op_bool();
					state._fsp--;

					pushFollow(FOLLOW_ctl_end_in_formula394);
					ctl_end39=ctl_end();
					state._fsp--;

					match(input,CLOSE,FOLLOW_CLOSE_in_formula396); 
					 result = new Formula((TEMPORAL37!=null?TEMPORAL37.getText():null), first38, ctl_end39, (op_bool40!=null?input.toString(op_bool40.start,op_bool40.stop):null));
					}
					break;
				case 16 :
					// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:38:11: TEMPORAL OPEN ctl_init ( op_bool ctl_end )? CLOSE
					{
					boolean op = false;
					TEMPORAL41=(Token)match(input,TEMPORAL,FOLLOW_TEMPORAL_in_formula412); 
					match(input,OPEN,FOLLOW_OPEN_in_formula414); 
					pushFollow(FOLLOW_ctl_init_in_formula416);
					ctl_init42=ctl_init();
					state._fsp--;

					// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:38:56: ( op_bool ctl_end )?
					int alt3=2;
					int LA3_0 = input.LA(1);
					if ( ((LA3_0 >= 15 && LA3_0 <= 18)) ) {
						alt3=1;
					}
					switch (alt3) {
						case 1 :
							// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:38:57: op_bool ctl_end
							{
							pushFollow(FOLLOW_op_bool_in_formula419);
							op_bool44=op_bool();
							state._fsp--;

							pushFollow(FOLLOW_ctl_end_in_formula421);
							ctl_end43=ctl_end();
							state._fsp--;

							op = true;
							}
							break;

					}

					match(input,CLOSE,FOLLOW_CLOSE_in_formula427); 
					 if (op) {
					        	 	  result = new Formula((TEMPORAL41!=null?TEMPORAL41.getText():null), ctl_init42,  ctl_end43, (op_bool44!=null?((FormulaParser.op_bool_return)op_bool44).result:null));
					        	 	  } else {
					        	 	  result = new Formula((TEMPORAL41!=null?TEMPORAL41.getText():null), ctl_init42);
					        	 	  } 
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return result;
	}
	// $ANTLR end "formula"



	// $ANTLR start "first"
	// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:46:1: first returns [String result] : ATOMIC ;
	public final String first() throws RecognitionException {
		String result = null;


		Token ATOMIC45=null;

		try {
			// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:46:30: ( ATOMIC )
			// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:46:32: ATOMIC
			{
			ATOMIC45=(Token)match(input,ATOMIC,FOLLOW_ATOMIC_in_first458); 
			result = (ATOMIC45!=null?ATOMIC45.getText():null);
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return result;
	}
	// $ANTLR end "first"



	// $ANTLR start "second"
	// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:47:1: second returns [String result] : ATOMIC ;
	public final String second() throws RecognitionException {
		String result = null;


		Token ATOMIC46=null;

		try {
			// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:47:31: ( ATOMIC )
			// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:47:33: ATOMIC
			{
			ATOMIC46=(Token)match(input,ATOMIC,FOLLOW_ATOMIC_in_second470); 
			result = (ATOMIC46!=null?ATOMIC46.getText():null);
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return result;
	}
	// $ANTLR end "second"



	// $ANTLR start "ctl_init"
	// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:49:1: ctl_init returns [Formula result] : formula ;
	public final Formula ctl_init() throws RecognitionException {
		Formula result = null;


		Formula formula47 =null;

		try {
			// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:49:34: ( formula )
			// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:50:5: formula
			{
			pushFollow(FOLLOW_formula_in_ctl_init487);
			formula47=formula();
			state._fsp--;

			result = formula47;
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return result;
	}
	// $ANTLR end "ctl_init"



	// $ANTLR start "ctl_end"
	// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:53:1: ctl_end returns [Formula result] : formula ;
	public final Formula ctl_end() throws RecognitionException {
		Formula result = null;


		Formula formula48 =null;

		try {
			// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:53:33: ( formula )
			// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:54:5: formula
			{
			pushFollow(FOLLOW_formula_in_ctl_end508);
			formula48=formula();
			state._fsp--;

			result = formula48;
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return result;
	}
	// $ANTLR end "ctl_end"



	// $ANTLR start "neg"
	// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:57:1: neg returns [boolean result] : '!' ;
	public final boolean neg() throws RecognitionException {
		boolean result = false;


		try {
			// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:57:31: ( '!' )
			// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:58:4: '!'
			{
			match(input,14,FOLLOW_14_in_neg530); 
			 result = true; 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return result;
	}
	// $ANTLR end "neg"


	public static class op_bool_return extends ParserRuleReturnScope {
		public String result;
	};


	// $ANTLR start "op_bool"
	// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:62:1: op_bool returns [String result] : ( '&&' | '||' | '=>' | '<=>' );
	public final FormulaParser.op_bool_return op_bool() throws RecognitionException {
		FormulaParser.op_bool_return retval = new FormulaParser.op_bool_return();
		retval.start = input.LT(1);

		try {
			// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:62:32: ( '&&' | '||' | '=>' | '<=>' )
			int alt5=4;
			switch ( input.LA(1) ) {
			case 15:
				{
				alt5=1;
				}
				break;
			case 18:
				{
				alt5=2;
				}
				break;
			case 17:
				{
				alt5=3;
				}
				break;
			case 16:
				{
				alt5=4;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 5, 0, input);
				throw nvae;
			}
			switch (alt5) {
				case 1 :
					// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:63:6: '&&'
					{
					match(input,15,FOLLOW_15_in_op_bool554); 
					retval.result = "&&";
					}
					break;
				case 2 :
					// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:64:7: '||'
					{
					match(input,18,FOLLOW_18_in_op_bool564); 
					retval.result = "||"; 
					}
					break;
				case 3 :
					// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:65:7: '=>'
					{
					match(input,17,FOLLOW_17_in_op_bool575); 
					retval.result = "=>";
					}
					break;
				case 4 :
					// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:66:7: '<=>'
					{
					match(input,16,FOLLOW_16_in_op_bool585); 
					retval.result = "<=>";
					}
					break;

			}
			retval.stop = input.LT(-1);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "op_bool"

	// Delegated rules



	public static final BitSet FOLLOW_formula_in_query28 = new BitSet(new long[]{0x0000000000000000L});
	public static final BitSet FOLLOW_EOF_in_query30 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_TRUE_in_formula52 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_FALSE_in_formula67 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_OPEN_in_formula83 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_first_in_formula85 = new BitSet(new long[]{0x0000000000078040L});
	public static final BitSet FOLLOW_op_bool_in_formula88 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_second_in_formula90 = new BitSet(new long[]{0x0000000000000040L});
	public static final BitSet FOLLOW_CLOSE_in_formula96 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_OPEN_in_formula121 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_first_in_formula123 = new BitSet(new long[]{0x0000000000078000L});
	public static final BitSet FOLLOW_op_bool_in_formula125 = new BitSet(new long[]{0x0000000000004F90L});
	public static final BitSet FOLLOW_ctl_end_in_formula127 = new BitSet(new long[]{0x0000000000000040L});
	public static final BitSet FOLLOW_CLOSE_in_formula129 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_neg_in_formula143 = new BitSet(new long[]{0x0000000000004F90L});
	public static final BitSet FOLLOW_ctl_init_in_formula145 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_OPEN_in_formula158 = new BitSet(new long[]{0x0000000000004F90L});
	public static final BitSet FOLLOW_ctl_init_in_formula160 = new BitSet(new long[]{0x0000000000078040L});
	public static final BitSet FOLLOW_op_bool_in_formula163 = new BitSet(new long[]{0x0000000000004F90L});
	public static final BitSet FOLLOW_ctl_end_in_formula165 = new BitSet(new long[]{0x0000000000000040L});
	public static final BitSet FOLLOW_CLOSE_in_formula171 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_EXISTS_in_formula197 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_OPEN_in_formula199 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_first_in_formula201 = new BitSet(new long[]{0x0000000000001000L});
	public static final BitSet FOLLOW_UNTIL_in_formula203 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_second_in_formula205 = new BitSet(new long[]{0x0000000000000040L});
	public static final BitSet FOLLOW_CLOSE_in_formula207 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ALL_in_formula221 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_OPEN_in_formula223 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_first_in_formula225 = new BitSet(new long[]{0x0000000000001000L});
	public static final BitSet FOLLOW_UNTIL_in_formula227 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_second_in_formula229 = new BitSet(new long[]{0x0000000000000040L});
	public static final BitSet FOLLOW_CLOSE_in_formula231 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_EXISTS_in_formula245 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_OPEN_in_formula247 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_first_in_formula249 = new BitSet(new long[]{0x0000000000001000L});
	public static final BitSet FOLLOW_UNTIL_in_formula251 = new BitSet(new long[]{0x0000000000004F90L});
	public static final BitSet FOLLOW_ctl_end_in_formula253 = new BitSet(new long[]{0x0000000000000040L});
	public static final BitSet FOLLOW_CLOSE_in_formula255 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ALL_in_formula269 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_OPEN_in_formula271 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_first_in_formula273 = new BitSet(new long[]{0x0000000000001000L});
	public static final BitSet FOLLOW_UNTIL_in_formula275 = new BitSet(new long[]{0x0000000000004F90L});
	public static final BitSet FOLLOW_ctl_end_in_formula277 = new BitSet(new long[]{0x0000000000000040L});
	public static final BitSet FOLLOW_CLOSE_in_formula279 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_EXISTS_in_formula293 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_OPEN_in_formula295 = new BitSet(new long[]{0x0000000000004F90L});
	public static final BitSet FOLLOW_ctl_init_in_formula297 = new BitSet(new long[]{0x0000000000001000L});
	public static final BitSet FOLLOW_UNTIL_in_formula299 = new BitSet(new long[]{0x0000000000004F90L});
	public static final BitSet FOLLOW_ctl_end_in_formula301 = new BitSet(new long[]{0x0000000000000040L});
	public static final BitSet FOLLOW_CLOSE_in_formula303 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ALL_in_formula317 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_OPEN_in_formula319 = new BitSet(new long[]{0x0000000000004F90L});
	public static final BitSet FOLLOW_ctl_init_in_formula321 = new BitSet(new long[]{0x0000000000001000L});
	public static final BitSet FOLLOW_UNTIL_in_formula323 = new BitSet(new long[]{0x0000000000004F90L});
	public static final BitSet FOLLOW_ctl_end_in_formula325 = new BitSet(new long[]{0x0000000000000040L});
	public static final BitSet FOLLOW_CLOSE_in_formula327 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_TEMPORAL_in_formula342 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_OPEN_in_formula344 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_first_in_formula346 = new BitSet(new long[]{0x0000000000078000L});
	public static final BitSet FOLLOW_op_bool_in_formula348 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_second_in_formula350 = new BitSet(new long[]{0x0000000000000040L});
	public static final BitSet FOLLOW_CLOSE_in_formula352 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_TEMPORAL_in_formula366 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_OPEN_in_formula368 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_first_in_formula370 = new BitSet(new long[]{0x0000000000000040L});
	public static final BitSet FOLLOW_CLOSE_in_formula372 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_TEMPORAL_in_formula386 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_OPEN_in_formula388 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_first_in_formula390 = new BitSet(new long[]{0x0000000000078000L});
	public static final BitSet FOLLOW_op_bool_in_formula392 = new BitSet(new long[]{0x0000000000004F90L});
	public static final BitSet FOLLOW_ctl_end_in_formula394 = new BitSet(new long[]{0x0000000000000040L});
	public static final BitSet FOLLOW_CLOSE_in_formula396 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_TEMPORAL_in_formula412 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_OPEN_in_formula414 = new BitSet(new long[]{0x0000000000004F90L});
	public static final BitSet FOLLOW_ctl_init_in_formula416 = new BitSet(new long[]{0x0000000000078040L});
	public static final BitSet FOLLOW_op_bool_in_formula419 = new BitSet(new long[]{0x0000000000004F90L});
	public static final BitSet FOLLOW_ctl_end_in_formula421 = new BitSet(new long[]{0x0000000000000040L});
	public static final BitSet FOLLOW_CLOSE_in_formula427 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ATOMIC_in_first458 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ATOMIC_in_second470 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_formula_in_ctl_init487 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_formula_in_ctl_end508 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_14_in_neg530 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_15_in_op_bool554 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_18_in_op_bool564 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_17_in_op_bool575 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_16_in_op_bool585 = new BitSet(new long[]{0x0000000000000002L});
}
