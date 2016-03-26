
public class Token {
	
	int token;
	String lexeme;
	
	static final int EOF = -1;
	static final int DOC_CLASS=0;
	static final int PACKAGE=1;
	static final int BEGIN=2;
	static final int END=3;
	static final int TITLE=4;
	static final int TEXT=5;
	static final int SUB_TITLE=6;
	static final int SECTION=7;
	static final int DATE=8;
	static final int MAKE=9;
	static final int BF=10;
	static final int BODY=11;
	static final int NEWLINE=25;
	static final int ERROR=30;
	
	public Token(int token, String lexeme)
	{
		this.token=token;
		this.lexeme=lexeme;
	}

	public int getTokenType()
	{
		return token;
	}
	public String getLexeme()
	{
		return lexeme;
	}
	//String representation of token + lexeme
	public String toString()
	{
	
		
		switch(token)
		{
		
		case TITLE:
			return "TITLE : "+lexeme;
		case SUB_TITLE:
			return "SUBTITLE : "+lexeme;
		case BODY:
			return "BODY : "+lexeme;
		case TEXT:
			return "TEXT : "+lexeme.substring(1,lexeme.length()-1);
		case BF:
			return "TEXTBF : "+lexeme;
		case DOC_CLASS:
			return "DOCUMENT CLASS : "+lexeme;
		case PACKAGE:
			return "PACKAGE : "+lexeme;
		case BEGIN:
			return "BEGIN DOCUMENT : "+lexeme;
		case END:
			return "END DOCUMENT: "+lexeme;
		case MAKE:
			return "MAKE TITLE : "+lexeme;
		case SECTION:
			return "SECTION : "+lexeme;
		case DATE:
			return "DATE: "+lexeme;
		case NEWLINE:
			return "NEWLINE";
		default:
		return "ERROR "+lexeme;
		}
	}
}
