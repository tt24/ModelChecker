package lsv.grammar;

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class FormulaLexer extends Lexer {
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
	// delegators
	public Lexer[] getDelegates() {
		return new Lexer[] {};
	}

	public FormulaLexer() {} 
	public FormulaLexer(CharStream input) {
		this(input, new RecognizerSharedState());
	}
	public FormulaLexer(CharStream input, RecognizerSharedState state) {
		super(input,state);
	}
	@Override public String getGrammarFileName() { return "C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g"; }

	// $ANTLR start "T__14"
	public final void mT__14() throws RecognitionException {
		try {
			int _type = T__14;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:7:7: ( '!' )
			// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:7:9: '!'
			{
			match('!'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__14"

	// $ANTLR start "T__15"
	public final void mT__15() throws RecognitionException {
		try {
			int _type = T__15;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:8:7: ( '&&' )
			// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:8:9: '&&'
			{
			match("&&"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__15"

	// $ANTLR start "T__16"
	public final void mT__16() throws RecognitionException {
		try {
			int _type = T__16;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:9:7: ( '<=>' )
			// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:9:9: '<=>'
			{
			match("<=>"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__16"

	// $ANTLR start "T__17"
	public final void mT__17() throws RecognitionException {
		try {
			int _type = T__17;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:10:7: ( '=>' )
			// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:10:9: '=>'
			{
			match("=>"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__17"

	// $ANTLR start "T__18"
	public final void mT__18() throws RecognitionException {
		try {
			int _type = T__18;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:11:7: ( '||' )
			// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:11:9: '||'
			{
			match("||"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__18"

	// $ANTLR start "TRUE"
	public final void mTRUE() throws RecognitionException {
		try {
			int _type = TRUE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:70:6: ( 'True' )
			// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:70:8: 'True'
			{
			match("True"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "TRUE"

	// $ANTLR start "FALSE"
	public final void mFALSE() throws RecognitionException {
		try {
			int _type = FALSE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:71:6: ( 'False' )
			// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:71:8: 'False'
			{
			match("False"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "FALSE"

	// $ANTLR start "ALL"
	public final void mALL() throws RecognitionException {
		try {
			int _type = ALL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:72:6: ( 'A' )
			// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:72:8: 'A'
			{
			match('A'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ALL"

	// $ANTLR start "EXISTS"
	public final void mEXISTS() throws RecognitionException {
		try {
			int _type = EXISTS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:73:8: ( 'E' )
			// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:73:10: 'E'
			{
			match('E'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "EXISTS"

	// $ANTLR start "UNTIL"
	public final void mUNTIL() throws RecognitionException {
		try {
			int _type = UNTIL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:74:8: ( ( 'a' .. 'z' )? 'U' ( 'a' .. 'z' )? )
			// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:74:10: ( 'a' .. 'z' )? 'U' ( 'a' .. 'z' )?
			{
			// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:74:10: ( 'a' .. 'z' )?
			int alt1=2;
			int LA1_0 = input.LA(1);
			if ( ((LA1_0 >= 'a' && LA1_0 <= 'z')) ) {
				alt1=1;
			}
			switch (alt1) {
				case 1 :
					// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:
					{
					if ( (input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

			}

			match('U'); 
			// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:74:24: ( 'a' .. 'z' )?
			int alt2=2;
			int LA2_0 = input.LA(1);
			if ( ((LA2_0 >= 'a' && LA2_0 <= 'z')) ) {
				alt2=1;
			}
			switch (alt2) {
				case 1 :
					// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:
					{
					if ( (input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "UNTIL"

	// $ANTLR start "OPEN"
	public final void mOPEN() throws RecognitionException {
		try {
			int _type = OPEN;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:75:8: ( '(' )
			// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:75:10: '('
			{
			match('('); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "OPEN"

	// $ANTLR start "CLOSE"
	public final void mCLOSE() throws RecognitionException {
		try {
			int _type = CLOSE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:76:8: ( ')' )
			// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:76:10: ')'
			{
			match(')'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "CLOSE"

	// $ANTLR start "ATOMIC"
	public final void mATOMIC() throws RecognitionException {
		try {
			int _type = ATOMIC;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:77:8: ( ( '!' )* ( 'a' .. 'z' ) )
			// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:77:9: ( '!' )* ( 'a' .. 'z' )
			{
			// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:77:9: ( '!' )*
			loop3:
			while (true) {
				int alt3=2;
				int LA3_0 = input.LA(1);
				if ( (LA3_0=='!') ) {
					alt3=1;
				}

				switch (alt3) {
				case 1 :
					// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:77:10: '!'
					{
					match('!'); 
					}
					break;

				default :
					break loop3;
				}
			}

			if ( (input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ATOMIC"

	// $ANTLR start "TEMPORAL"
	public final void mTEMPORAL() throws RecognitionException {
		try {
			int _type = TEMPORAL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:78:9: ( 'A' ( 'a' .. 'z' )? 'F' ( 'a' .. 'z' )? | 'A' ( 'a' .. 'z' )? 'G' ( 'a' .. 'z' )? | 'A' ( 'a' .. 'z' )? 'X' ( 'a' .. 'z' )? | 'E' ( 'a' .. 'z' )? 'F' ( 'a' .. 'z' )? | 'E' ( 'a' .. 'z' )? 'G' ( 'a' .. 'z' )? | 'E' ( 'a' .. 'z' )? 'X' ( 'a' .. 'z' )? )
			int alt16=6;
			int LA16_0 = input.LA(1);
			if ( (LA16_0=='A') ) {
				switch ( input.LA(2) ) {
				case 'a':
				case 'b':
				case 'c':
				case 'd':
				case 'e':
				case 'f':
				case 'g':
				case 'h':
				case 'i':
				case 'j':
				case 'k':
				case 'l':
				case 'm':
				case 'n':
				case 'o':
				case 'p':
				case 'q':
				case 'r':
				case 's':
				case 't':
				case 'u':
				case 'v':
				case 'w':
				case 'x':
				case 'y':
				case 'z':
					{
					switch ( input.LA(3) ) {
					case 'F':
						{
						alt16=1;
						}
						break;
					case 'G':
						{
						alt16=2;
						}
						break;
					case 'X':
						{
						alt16=3;
						}
						break;
					default:
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 16, 3, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}
					}
					break;
				case 'F':
					{
					alt16=1;
					}
					break;
				case 'G':
					{
					alt16=2;
					}
					break;
				case 'X':
					{
					alt16=3;
					}
					break;
				default:
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 16, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}
			}
			else if ( (LA16_0=='E') ) {
				switch ( input.LA(2) ) {
				case 'a':
				case 'b':
				case 'c':
				case 'd':
				case 'e':
				case 'f':
				case 'g':
				case 'h':
				case 'i':
				case 'j':
				case 'k':
				case 'l':
				case 'm':
				case 'n':
				case 'o':
				case 'p':
				case 'q':
				case 'r':
				case 's':
				case 't':
				case 'u':
				case 'v':
				case 'w':
				case 'x':
				case 'y':
				case 'z':
					{
					switch ( input.LA(3) ) {
					case 'F':
						{
						alt16=4;
						}
						break;
					case 'G':
						{
						alt16=5;
						}
						break;
					case 'X':
						{
						alt16=6;
						}
						break;
					default:
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 16, 7, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}
					}
					break;
				case 'F':
					{
					alt16=4;
					}
					break;
				case 'G':
					{
					alt16=5;
					}
					break;
				case 'X':
					{
					alt16=6;
					}
					break;
				default:
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 16, 2, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 16, 0, input);
				throw nvae;
			}

			switch (alt16) {
				case 1 :
					// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:79:3: 'A' ( 'a' .. 'z' )? 'F' ( 'a' .. 'z' )?
					{
					match('A'); 
					// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:79:6: ( 'a' .. 'z' )?
					int alt4=2;
					int LA4_0 = input.LA(1);
					if ( ((LA4_0 >= 'a' && LA4_0 <= 'z')) ) {
						alt4=1;
					}
					switch (alt4) {
						case 1 :
							// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:
							{
							if ( (input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
								input.consume();
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								recover(mse);
								throw mse;
							}
							}
							break;

					}

					match('F'); 
					// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:79:20: ( 'a' .. 'z' )?
					int alt5=2;
					int LA5_0 = input.LA(1);
					if ( ((LA5_0 >= 'a' && LA5_0 <= 'z')) ) {
						alt5=1;
					}
					switch (alt5) {
						case 1 :
							// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:
							{
							if ( (input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
								input.consume();
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								recover(mse);
								throw mse;
							}
							}
							break;

					}

					}
					break;
				case 2 :
					// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:80:4: 'A' ( 'a' .. 'z' )? 'G' ( 'a' .. 'z' )?
					{
					match('A'); 
					// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:80:7: ( 'a' .. 'z' )?
					int alt6=2;
					int LA6_0 = input.LA(1);
					if ( ((LA6_0 >= 'a' && LA6_0 <= 'z')) ) {
						alt6=1;
					}
					switch (alt6) {
						case 1 :
							// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:
							{
							if ( (input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
								input.consume();
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								recover(mse);
								throw mse;
							}
							}
							break;

					}

					match('G'); 
					// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:80:21: ( 'a' .. 'z' )?
					int alt7=2;
					int LA7_0 = input.LA(1);
					if ( ((LA7_0 >= 'a' && LA7_0 <= 'z')) ) {
						alt7=1;
					}
					switch (alt7) {
						case 1 :
							// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:
							{
							if ( (input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
								input.consume();
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								recover(mse);
								throw mse;
							}
							}
							break;

					}

					}
					break;
				case 3 :
					// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:81:4: 'A' ( 'a' .. 'z' )? 'X' ( 'a' .. 'z' )?
					{
					match('A'); 
					// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:81:7: ( 'a' .. 'z' )?
					int alt8=2;
					int LA8_0 = input.LA(1);
					if ( ((LA8_0 >= 'a' && LA8_0 <= 'z')) ) {
						alt8=1;
					}
					switch (alt8) {
						case 1 :
							// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:
							{
							if ( (input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
								input.consume();
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								recover(mse);
								throw mse;
							}
							}
							break;

					}

					match('X'); 
					// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:81:21: ( 'a' .. 'z' )?
					int alt9=2;
					int LA9_0 = input.LA(1);
					if ( ((LA9_0 >= 'a' && LA9_0 <= 'z')) ) {
						alt9=1;
					}
					switch (alt9) {
						case 1 :
							// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:
							{
							if ( (input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
								input.consume();
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								recover(mse);
								throw mse;
							}
							}
							break;

					}

					}
					break;
				case 4 :
					// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:82:4: 'E' ( 'a' .. 'z' )? 'F' ( 'a' .. 'z' )?
					{
					match('E'); 
					// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:82:7: ( 'a' .. 'z' )?
					int alt10=2;
					int LA10_0 = input.LA(1);
					if ( ((LA10_0 >= 'a' && LA10_0 <= 'z')) ) {
						alt10=1;
					}
					switch (alt10) {
						case 1 :
							// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:
							{
							if ( (input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
								input.consume();
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								recover(mse);
								throw mse;
							}
							}
							break;

					}

					match('F'); 
					// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:82:21: ( 'a' .. 'z' )?
					int alt11=2;
					int LA11_0 = input.LA(1);
					if ( ((LA11_0 >= 'a' && LA11_0 <= 'z')) ) {
						alt11=1;
					}
					switch (alt11) {
						case 1 :
							// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:
							{
							if ( (input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
								input.consume();
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								recover(mse);
								throw mse;
							}
							}
							break;

					}

					}
					break;
				case 5 :
					// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:83:4: 'E' ( 'a' .. 'z' )? 'G' ( 'a' .. 'z' )?
					{
					match('E'); 
					// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:83:7: ( 'a' .. 'z' )?
					int alt12=2;
					int LA12_0 = input.LA(1);
					if ( ((LA12_0 >= 'a' && LA12_0 <= 'z')) ) {
						alt12=1;
					}
					switch (alt12) {
						case 1 :
							// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:
							{
							if ( (input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
								input.consume();
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								recover(mse);
								throw mse;
							}
							}
							break;

					}

					match('G'); 
					// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:83:21: ( 'a' .. 'z' )?
					int alt13=2;
					int LA13_0 = input.LA(1);
					if ( ((LA13_0 >= 'a' && LA13_0 <= 'z')) ) {
						alt13=1;
					}
					switch (alt13) {
						case 1 :
							// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:
							{
							if ( (input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
								input.consume();
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								recover(mse);
								throw mse;
							}
							}
							break;

					}

					}
					break;
				case 6 :
					// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:84:4: 'E' ( 'a' .. 'z' )? 'X' ( 'a' .. 'z' )?
					{
					match('E'); 
					// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:84:7: ( 'a' .. 'z' )?
					int alt14=2;
					int LA14_0 = input.LA(1);
					if ( ((LA14_0 >= 'a' && LA14_0 <= 'z')) ) {
						alt14=1;
					}
					switch (alt14) {
						case 1 :
							// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:
							{
							if ( (input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
								input.consume();
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								recover(mse);
								throw mse;
							}
							}
							break;

					}

					match('X'); 
					// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:84:21: ( 'a' .. 'z' )?
					int alt15=2;
					int LA15_0 = input.LA(1);
					if ( ((LA15_0 >= 'a' && LA15_0 <= 'z')) ) {
						alt15=1;
					}
					switch (alt15) {
						case 1 :
							// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:
							{
							if ( (input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
								input.consume();
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								recover(mse);
								throw mse;
							}
							}
							break;

					}

					}
					break;

			}
			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "TEMPORAL"

	// $ANTLR start "WS"
	public final void mWS() throws RecognitionException {
		try {
			int _type = WS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:88:8: ( ( ' ' | '\\t' | '\\r' | '\\n' | '\\f' )+ )
			// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:88:11: ( ' ' | '\\t' | '\\r' | '\\n' | '\\f' )+
			{
			// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:88:11: ( ' ' | '\\t' | '\\r' | '\\n' | '\\f' )+
			int cnt17=0;
			loop17:
			while (true) {
				int alt17=2;
				int LA17_0 = input.LA(1);
				if ( ((LA17_0 >= '\t' && LA17_0 <= '\n')||(LA17_0 >= '\f' && LA17_0 <= '\r')||LA17_0==' ') ) {
					alt17=1;
				}

				switch (alt17) {
				case 1 :
					// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:
					{
					if ( (input.LA(1) >= '\t' && input.LA(1) <= '\n')||(input.LA(1) >= '\f' && input.LA(1) <= '\r')||input.LA(1)==' ' ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt17 >= 1 ) break loop17;
					EarlyExitException eee = new EarlyExitException(17, input);
					throw eee;
				}
				cnt17++;
			}

			_channel=HIDDEN;
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "WS"

	@Override
	public void mTokens() throws RecognitionException {
		// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:1:8: ( T__14 | T__15 | T__16 | T__17 | T__18 | TRUE | FALSE | ALL | EXISTS | UNTIL | OPEN | CLOSE | ATOMIC | TEMPORAL | WS )
		int alt18=15;
		switch ( input.LA(1) ) {
		case '!':
			{
			int LA18_1 = input.LA(2);
			if ( (LA18_1=='!'||(LA18_1 >= 'a' && LA18_1 <= 'z')) ) {
				alt18=13;
			}

			else {
				alt18=1;
			}

			}
			break;
		case '&':
			{
			alt18=2;
			}
			break;
		case '<':
			{
			alt18=3;
			}
			break;
		case '=':
			{
			alt18=4;
			}
			break;
		case '|':
			{
			alt18=5;
			}
			break;
		case 'T':
			{
			alt18=6;
			}
			break;
		case 'F':
			{
			alt18=7;
			}
			break;
		case 'A':
			{
			int LA18_8 = input.LA(2);
			if ( ((LA18_8 >= 'F' && LA18_8 <= 'G')||LA18_8=='X'||(LA18_8 >= 'a' && LA18_8 <= 'z')) ) {
				alt18=14;
			}

			else {
				alt18=8;
			}

			}
			break;
		case 'E':
			{
			int LA18_9 = input.LA(2);
			if ( ((LA18_9 >= 'F' && LA18_9 <= 'G')||LA18_9=='X'||(LA18_9 >= 'a' && LA18_9 <= 'z')) ) {
				alt18=14;
			}

			else {
				alt18=9;
			}

			}
			break;
		case 'a':
		case 'b':
		case 'c':
		case 'd':
		case 'e':
		case 'f':
		case 'g':
		case 'h':
		case 'i':
		case 'j':
		case 'k':
		case 'l':
		case 'm':
		case 'n':
		case 'o':
		case 'p':
		case 'q':
		case 'r':
		case 's':
		case 't':
		case 'u':
		case 'v':
		case 'w':
		case 'x':
		case 'y':
		case 'z':
			{
			int LA18_10 = input.LA(2);
			if ( (LA18_10=='U') ) {
				alt18=10;
			}

			else {
				alt18=13;
			}

			}
			break;
		case 'U':
			{
			alt18=10;
			}
			break;
		case '(':
			{
			alt18=11;
			}
			break;
		case ')':
			{
			alt18=12;
			}
			break;
		case '\t':
		case '\n':
		case '\f':
		case '\r':
		case ' ':
			{
			alt18=15;
			}
			break;
		default:
			NoViableAltException nvae =
				new NoViableAltException("", 18, 0, input);
			throw nvae;
		}
		switch (alt18) {
			case 1 :
				// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:1:10: T__14
				{
				mT__14(); 

				}
				break;
			case 2 :
				// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:1:16: T__15
				{
				mT__15(); 

				}
				break;
			case 3 :
				// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:1:22: T__16
				{
				mT__16(); 

				}
				break;
			case 4 :
				// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:1:28: T__17
				{
				mT__17(); 

				}
				break;
			case 5 :
				// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:1:34: T__18
				{
				mT__18(); 

				}
				break;
			case 6 :
				// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:1:40: TRUE
				{
				mTRUE(); 

				}
				break;
			case 7 :
				// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:1:45: FALSE
				{
				mFALSE(); 

				}
				break;
			case 8 :
				// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:1:51: ALL
				{
				mALL(); 

				}
				break;
			case 9 :
				// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:1:55: EXISTS
				{
				mEXISTS(); 

				}
				break;
			case 10 :
				// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:1:62: UNTIL
				{
				mUNTIL(); 

				}
				break;
			case 11 :
				// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:1:68: OPEN
				{
				mOPEN(); 

				}
				break;
			case 12 :
				// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:1:73: CLOSE
				{
				mCLOSE(); 

				}
				break;
			case 13 :
				// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:1:79: ATOMIC
				{
				mATOMIC(); 

				}
				break;
			case 14 :
				// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:1:86: TEMPORAL
				{
				mTEMPORAL(); 

				}
				break;
			case 15 :
				// C:\\Users\\IBM_ADMIN\\antlr\\antlr\\src\\Formula.g:1:95: WS
				{
				mWS(); 

				}
				break;

		}
	}



}
