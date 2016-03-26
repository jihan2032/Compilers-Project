import java.lang.System;
import java.io.*;
class Lexer {
	Yylex tokenizer;
	public  Lexer(String fileName)
	{
	  try
	  {
	  tokenizer=new Yylex(new BufferedReader(new FileReader(fileName)));
	  }
	  catch(Exception e)
	  {
	  }
	}
	public Token nextToken()
	{
		Token next=null;
		try
		{
		 next=  tokenizer.getToken();
		}
		catch(Exception e)
		{
		}
		return next;
	}
	}


class Yylex {
	private final int YY_BUFFER_SIZE = 512;
	private final int YY_F = -1;
	private final int YY_NO_STATE = -1;
	private final int YY_NOT_ACCEPT = 0;
	private final int YY_START = 1;
	private final int YY_END = 2;
	private final int YY_NO_ANCHOR = 4;
	private final int YY_BOL = 128;
	private final int YY_EOF = 129;

	//initialize  variables to be used by class
	private java.io.BufferedReader yy_reader;
	private int yy_buffer_index;
	private int yy_buffer_read;
	private int yy_buffer_start;
	private int yy_buffer_end;
	private char yy_buffer[];
	private int yychar;
	private int yyline;
	private boolean yy_at_bol;
	private int yy_lexical_state;

	Yylex (java.io.Reader reader) {
		this ();
		if (null == reader) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(reader);
	}

	Yylex (java.io.InputStream instream) {
		this ();
		if (null == instream) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(new java.io.InputStreamReader(instream));
	}

	private Yylex () {
		yy_buffer = new char[YY_BUFFER_SIZE];
		yy_buffer_read = 0;
		yy_buffer_index = 0;
		yy_buffer_start = 0;
		yy_buffer_end = 0;
		yychar = 0;
		yyline = 0;
		yy_at_bol = true;
		yy_lexical_state = YYINITIAL;

//Add code to be executed on initialization of the lexer
	}

	private boolean yy_eof_done = false;
	private final int YYINITIAL = 0;
	private final int yy_state_dtrans[] = {
		0
	};
	private void yybegin (int state) {
		yy_lexical_state = state;
	}
	private int yy_advance ()
		throws java.io.IOException {
		int next_read;
		int i;
		int j;

		if (yy_buffer_index < yy_buffer_read) {
			return yy_buffer[yy_buffer_index++];
		}

		if (0 != yy_buffer_start) {
			i = yy_buffer_start;
			j = 0;
			while (i < yy_buffer_read) {
				yy_buffer[j] = yy_buffer[i];
				++i;
				++j;
			}
			yy_buffer_end = yy_buffer_end - yy_buffer_start;
			yy_buffer_start = 0;
			yy_buffer_read = j;
			yy_buffer_index = j;
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}

		while (yy_buffer_index >= yy_buffer_read) {
			if (yy_buffer_index >= yy_buffer.length) {
				yy_buffer = yy_double(yy_buffer);
			}
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}
		return yy_buffer[yy_buffer_index++];
	}
	private void yy_move_end () {
		if (yy_buffer_end > yy_buffer_start &&
		    '\n' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
		if (yy_buffer_end > yy_buffer_start &&
		    '\r' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
	}
	private boolean yy_last_was_cr=false;
	private void yy_mark_start () {
		int i;
		for (i = yy_buffer_start; i < yy_buffer_index; ++i) {
			if ('\n' == yy_buffer[i] && !yy_last_was_cr) {
				++yyline;
			}
			if ('\r' == yy_buffer[i]) {
				++yyline;
				yy_last_was_cr=true;
			} else yy_last_was_cr=false;
		}
		yychar = yychar
			+ yy_buffer_index - yy_buffer_start;
		yy_buffer_start = yy_buffer_index;
	}
	private void yy_mark_end () {
		yy_buffer_end = yy_buffer_index;
	}
	private void yy_to_mark () {
		yy_buffer_index = yy_buffer_end;
		yy_at_bol = (yy_buffer_end > yy_buffer_start) &&
		            ('\r' == yy_buffer[yy_buffer_end-1] ||
		             '\n' == yy_buffer[yy_buffer_end-1] ||
		             2028/*LS*/ == yy_buffer[yy_buffer_end-1] ||
		             2029/*PS*/ == yy_buffer[yy_buffer_end-1]);
	}
	private java.lang.String yytext () {
		return (new java.lang.String(yy_buffer,
			yy_buffer_start,
			yy_buffer_end - yy_buffer_start));
	}
	private int yylength () {
		return yy_buffer_end - yy_buffer_start;
	}
	private char[] yy_double (char buf[]) {
		int i;
		char newbuf[];
		newbuf = new char[2*buf.length];
		for (i = 0; i < buf.length; ++i) {
			newbuf[i] = buf[i];
		}
		return newbuf;
	}
	private final int YY_E_INTERNAL = 0;
	private final int YY_E_MATCH = 1;
	private java.lang.String yy_error_string[] = {
		"Error: Internal error.\n",
		"Error: Unmatched input.\n"
	};
	private void yy_error (int code,boolean fatal) {
		java.lang.System.out.print(yy_error_string[code]);
		java.lang.System.out.flush();
		if (fatal) {
			throw new Error("Fatal Error.\n");
		}
	}
	private int[][] unpackFromString(int size1, int size2, String st) {
		int colonIndex = -1;
		String lengthString;
		int sequenceLength = 0;
		int sequenceInteger = 0;

		int commaIndex;
		String workString;

		int res[][] = new int[size1][size2];
		for (int i= 0; i < size1; i++) {
			for (int j= 0; j < size2; j++) {
				if (sequenceLength != 0) {
					res[i][j] = sequenceInteger;
					sequenceLength--;
					continue;
				}
				commaIndex = st.indexOf(',');
				workString = (commaIndex==-1) ? st :
					st.substring(0, commaIndex);
				st = st.substring(commaIndex+1);
				colonIndex = workString.indexOf(':');
				if (colonIndex == -1) {
					res[i][j]=Integer.parseInt(workString);
					continue;
				}
				lengthString =
					workString.substring(colonIndex+1);
				sequenceLength=Integer.parseInt(lengthString);
				workString=workString.substring(0,colonIndex);
				sequenceInteger=Integer.parseInt(workString);
				res[i][j] = sequenceInteger;
				sequenceLength--;
			}
		}
		return res;
	}
	private int yy_acpt[] = {
		/* 0 */ YY_NOT_ACCEPT,
		/* 1 */ YY_NO_ANCHOR,
		/* 2 */ YY_NO_ANCHOR,
		/* 3 */ YY_NO_ANCHOR,
		/* 4 */ YY_NO_ANCHOR,
		/* 5 */ YY_NO_ANCHOR,
		/* 6 */ YY_NO_ANCHOR,
		/* 7 */ YY_NO_ANCHOR,
		/* 8 */ YY_NO_ANCHOR,
		/* 9 */ YY_NO_ANCHOR,
		/* 10 */ YY_NO_ANCHOR,
		/* 11 */ YY_NO_ANCHOR,
		/* 12 */ YY_NO_ANCHOR,
		/* 13 */ YY_NO_ANCHOR,
		/* 14 */ YY_NO_ANCHOR,
		/* 15 */ YY_NO_ANCHOR,
		/* 16 */ YY_NO_ANCHOR,
		/* 17 */ YY_NO_ANCHOR,
		/* 18 */ YY_NO_ANCHOR,
		/* 19 */ YY_NO_ANCHOR,
		/* 20 */ YY_NO_ANCHOR,
		/* 21 */ YY_NO_ANCHOR,
		/* 22 */ YY_NO_ANCHOR,
		/* 23 */ YY_NO_ANCHOR,
		/* 24 */ YY_NO_ANCHOR,
		/* 25 */ YY_NO_ANCHOR,
		/* 26 */ YY_NO_ANCHOR,
		/* 27 */ YY_NOT_ACCEPT,
		/* 28 */ YY_NO_ANCHOR,
		/* 29 */ YY_NO_ANCHOR,
		/* 30 */ YY_NO_ANCHOR,
		/* 31 */ YY_NO_ANCHOR,
		/* 32 */ YY_NO_ANCHOR,
		/* 33 */ YY_NO_ANCHOR,
		/* 34 */ YY_NO_ANCHOR,
		/* 35 */ YY_NO_ANCHOR,
		/* 36 */ YY_NO_ANCHOR,
		/* 37 */ YY_NO_ANCHOR,
		/* 38 */ YY_NO_ANCHOR,
		/* 39 */ YY_NO_ANCHOR,
		/* 40 */ YY_NO_ANCHOR,
		/* 41 */ YY_NO_ANCHOR,
		/* 42 */ YY_NO_ANCHOR,
		/* 43 */ YY_NO_ANCHOR,
		/* 44 */ YY_NO_ANCHOR,
		/* 45 */ YY_NO_ANCHOR,
		/* 46 */ YY_NO_ANCHOR,
		/* 47 */ YY_NO_ANCHOR,
		/* 48 */ YY_NO_ANCHOR,
		/* 49 */ YY_NO_ANCHOR,
		/* 50 */ YY_NO_ANCHOR,
		/* 51 */ YY_NO_ANCHOR,
		/* 52 */ YY_NO_ANCHOR,
		/* 53 */ YY_NO_ANCHOR,
		/* 54 */ YY_NO_ANCHOR,
		/* 55 */ YY_NO_ANCHOR,
		/* 56 */ YY_NO_ANCHOR,
		/* 57 */ YY_NO_ANCHOR,
		/* 58 */ YY_NO_ANCHOR,
		/* 59 */ YY_NO_ANCHOR,
		/* 60 */ YY_NO_ANCHOR,
		/* 61 */ YY_NO_ANCHOR,
		/* 62 */ YY_NO_ANCHOR,
		/* 63 */ YY_NO_ANCHOR,
		/* 64 */ YY_NO_ANCHOR,
		/* 65 */ YY_NO_ANCHOR,
		/* 66 */ YY_NO_ANCHOR,
		/* 67 */ YY_NO_ANCHOR,
		/* 68 */ YY_NO_ANCHOR,
		/* 69 */ YY_NO_ANCHOR,
		/* 70 */ YY_NO_ANCHOR,
		/* 71 */ YY_NO_ANCHOR,
		/* 72 */ YY_NO_ANCHOR,
		/* 73 */ YY_NO_ANCHOR,
		/* 74 */ YY_NO_ANCHOR,
		/* 75 */ YY_NO_ANCHOR,
		/* 76 */ YY_NO_ANCHOR,
		/* 77 */ YY_NO_ANCHOR,
		/* 78 */ YY_NO_ANCHOR,
		/* 79 */ YY_NO_ANCHOR,
		/* 80 */ YY_NO_ANCHOR,
		/* 81 */ YY_NO_ANCHOR,
		/* 82 */ YY_NO_ANCHOR,
		/* 83 */ YY_NO_ANCHOR,
		/* 84 */ YY_NO_ANCHOR,
		/* 85 */ YY_NO_ANCHOR,
		/* 86 */ YY_NO_ANCHOR,
		/* 87 */ YY_NO_ANCHOR,
		/* 88 */ YY_NO_ANCHOR,
		/* 89 */ YY_NO_ANCHOR,
		/* 90 */ YY_NO_ANCHOR,
		/* 91 */ YY_NO_ANCHOR,
		/* 92 */ YY_NO_ANCHOR,
		/* 93 */ YY_NO_ANCHOR,
		/* 94 */ YY_NO_ANCHOR,
		/* 95 */ YY_NO_ANCHOR,
		/* 96 */ YY_NO_ANCHOR,
		/* 97 */ YY_NO_ANCHOR,
		/* 98 */ YY_NO_ANCHOR,
		/* 99 */ YY_NO_ANCHOR,
		/* 100 */ YY_NO_ANCHOR,
		/* 101 */ YY_NO_ANCHOR,
		/* 102 */ YY_NO_ANCHOR,
		/* 103 */ YY_NO_ANCHOR,
		/* 104 */ YY_NO_ANCHOR,
		/* 105 */ YY_NO_ANCHOR,
		/* 106 */ YY_NO_ANCHOR,
		/* 107 */ YY_NO_ANCHOR,
		/* 108 */ YY_NO_ANCHOR,
		/* 109 */ YY_NO_ANCHOR,
		/* 110 */ YY_NO_ANCHOR,
		/* 111 */ YY_NO_ANCHOR,
		/* 112 */ YY_NO_ANCHOR,
		/* 113 */ YY_NO_ANCHOR,
		/* 114 */ YY_NO_ANCHOR,
		/* 115 */ YY_NO_ANCHOR,
		/* 116 */ YY_NO_ANCHOR,
		/* 117 */ YY_NO_ANCHOR,
		/* 118 */ YY_NO_ANCHOR,
		/* 119 */ YY_NO_ANCHOR,
		/* 120 */ YY_NO_ANCHOR,
		/* 121 */ YY_NO_ANCHOR,
		/* 122 */ YY_NO_ANCHOR,
		/* 123 */ YY_NO_ANCHOR,
		/* 124 */ YY_NO_ANCHOR,
		/* 125 */ YY_NO_ANCHOR,
		/* 126 */ YY_NO_ANCHOR,
		/* 127 */ YY_NO_ANCHOR,
		/* 128 */ YY_NO_ANCHOR,
		/* 129 */ YY_NO_ANCHOR,
		/* 130 */ YY_NO_ANCHOR,
		/* 131 */ YY_NO_ANCHOR,
		/* 132 */ YY_NO_ANCHOR,
		/* 133 */ YY_NO_ANCHOR,
		/* 134 */ YY_NO_ANCHOR,
		/* 135 */ YY_NO_ANCHOR,
		/* 136 */ YY_NO_ANCHOR,
		/* 137 */ YY_NO_ANCHOR,
		/* 138 */ YY_NO_ANCHOR,
		/* 139 */ YY_NO_ANCHOR,
		/* 140 */ YY_NO_ANCHOR,
		/* 141 */ YY_NO_ANCHOR,
		/* 142 */ YY_NO_ANCHOR,
		/* 143 */ YY_NO_ANCHOR,
		/* 144 */ YY_NO_ANCHOR,
		/* 145 */ YY_NO_ANCHOR,
		/* 146 */ YY_NO_ANCHOR,
		/* 147 */ YY_NO_ANCHOR,
		/* 148 */ YY_NO_ANCHOR,
		/* 149 */ YY_NO_ANCHOR,
		/* 150 */ YY_NO_ANCHOR,
		/* 151 */ YY_NO_ANCHOR,
		/* 152 */ YY_NO_ANCHOR,
		/* 153 */ YY_NO_ANCHOR,
		/* 154 */ YY_NO_ANCHOR
	};
	private int yy_cmap[] = unpackFromString(1,130,
"17:10,27,17:2,31,17:22,28,25,17:10,29:10,17:33,23,1,24,17:3,7,10,11,6,5,15," +
"19,17,3,17,21,4,20,13,12,22,30,17,8,2,9,17:2,14,17,26,16,17,18,17:2,0:2")[0];

	private int yy_rmap[] = unpackFromString(1,155,
"0,1,2,3,4,1,3:2,5,6,7,5:5,8,5:8,9,10,7,11,12,13,14,15,16,8,17,18,19,20,21,2" +
"2,23,24,9,10,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45" +
",46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70" +
",71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95" +
",96,97,98,99,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,11" +
"5,116,117,118,119,120,121,122,123,124,125,126,127,128,129,5,130,131,132,133")[0];

	private int yy_nxt[][] = unpackFromString(134,32,
"1,2,3:14,28,3:8,4,3,5,6,7,3:2,-1:33,8,9,61,131,147,62,150,151,152,153,150:9" +
",154,150:6,-1,150:3,-1:3,3:14,-1,3:8,-1,3,-1,3:4,-1,4:26,-1,4:3,-1:2,150:26" +
",-1,150:3,-1:2,150:2,63,150,64,150:21,-1,150:3,-1:2,27:17,10,27:8,-1,27:3,-" +
"1:2,34:17,16,34:8,-1,34:3,-1:2,43:17,25,43:8,-1,43:3,-1:2,44:17,26,44:8,-1," +
"44:3,-1:2,27:26,-1,27:3,-1:2,150:19,11,150:6,-1,150:3,-1:2,150:4,12,150:21," +
"-1,150:3,-1:2,150:4,13,150:21,-1,150:3,-1:2,150:14,14,150:11,-1,150:3,-1:2," +
"150:12,15,150:13,-1,150:3,-1:2,150:4,17,150:21,-1,150:3,-1:2,150:4,18,150:2" +
"1,-1,150:3,-1:2,150:17,19,150:8,-1,150:3,-1:2,150:17,20,150:8,-1,150:3,-1:2" +
",150:17,21,150:8,-1,150:3,-1:2,150:17,22,150:8,-1,150:3,-1:2,150:17,23,150:" +
"8,-1,150:3,-1:2,150:17,24,150:8,-1,150:3,-1:2,150:4,29,150:21,-1,150:3,-1:2" +
",150,30,150:24,-1,150:3,-1:2,150:3,31,150:22,-1,150:3,-1:2,150:9,32,150:16," +
"-1,150:3,-1:2,150:11,33,150:14,-1,150:3,-1:2,34:26,-1,34:3,-1:2,150:3,35,15" +
"0:22,-1,150:3,-1:2,150:3,36,150:22,-1,150:3,-1:2,150:4,37,150:21,-1,150:3,-" +
"1:2,150:12,38,150:13,-1,150:3,-1:2,150,39,150:24,-1,150:3,-1:2,150:4,40,150" +
":21,-1,150:3,-1:2,150:12,41,150:13,-1,150:3,-1:2,150,42,150:24,-1,150:3,-1:" +
"2,43:26,-1,43:3,-1:2,44:26,-1,44:3,-1:2,150,45,150:24,-1,150:3,-1:2,150:6,4" +
"6,150:4,67,150:14,-1,150:3,-1:2,150,47,150:24,-1,150:3,-1:2,150:13,71,150:1" +
"2,-1,150:3,-1:2,150:9,134,150:16,-1,150:3,-1:2,150:5,72,150:20,-1,150:3,-1:" +
"2,150:10,73,150:15,-1,150:3,-1:2,150:4,75,150:21,-1,150:3,-1:2,150:18,76,15" +
"0:7,-1,150:3,-1:2,150:20,77,150:5,-1,150:3,-1:2,150,48,150:24,-1,150:3,-1:2" +
",150:15,79,150:10,-1,150:3,-1:2,150:8,80,150:17,-1,150:3,-1:2,150,81,150:24" +
",-1,150:3,-1:2,150:21,82,150:4,-1,150:3,-1:2,150:2,83,150:23,-1,150:3,-1:2," +
"150:4,84,150:21,-1,150:3,-1:2,150:3,85,150:22,-1,150:3,-1:2,150:2,137,150,8" +
"6,87,150:20,-1,150:3,-1:2,150:19,88,150:6,-1,150:3,-1:2,150:2,49,150:23,-1," +
"150:3,-1:2,150:6,90,150:19,-1,150:3,-1:2,150:12,91,150:13,-1,150:3,-1:2,150" +
",92,150:24,-1,150:3,-1:2,150:15,50,150:10,-1,150:3,-1:2,150:26,-1,150:2,93," +
"-1:2,150:11,139,150:14,-1,150:3,-1:2,150:4,94,150:21,-1,150:3,-1:2,150,51,1" +
"50:24,-1,150:3,-1:2,150:10,95,150:15,-1,150:3,-1:2,150:15,96,150:10,-1,150:" +
"3,-1:2,150:2,97,150:23,-1,150:3,-1:2,150:8,99,150:17,-1,150:3,-1:2,150:12,1" +
"01,150:13,-1,150:3,-1:2,150:20,140,150:5,-1,150:3,-1:2,150:2,141,150,102,10" +
"3,150:20,-1,150:3,-1:2,150,52,150:24,-1,150:3,-1:2,150:19,104,150:6,-1,150:" +
"3,-1:2,150:6,148,150:19,-1,150:3,-1:2,150:8,105,150:17,-1,150:3,-1:2,150,10" +
"6,150:24,-1,150:3,-1:2,150:26,-1,150:2,109,-1:2,150:11,142,150:14,-1,150:3," +
"-1:2,150:2,110,150:23,-1,150:3,-1:2,150:19,143,150:6,-1,150:3,-1:2,150:10,1" +
"12,150:15,-1,150:3,-1:2,150:18,149,150:7,-1,150:3,-1:2,150:4,113,150:21,-1," +
"150:3,-1:2,150:8,114,150:17,-1,150:3,-1:2,150:25,53,-1,150:3,-1:2,150:2,116" +
",150:23,-1,150:3,-1:2,150:3,144,150:22,-1,150:3,-1:2,150:19,119,150:6,-1,15" +
"0:3,-1:2,150:6,120,150:19,-1,150:3,-1:2,150:8,121,150:17,-1,150:3,-1:2,150:" +
"11,54,150:14,-1,150:3,-1:2,150:12,55,150:13,-1,150:3,-1:2,150:22,123,150:3," +
"-1,150:3,-1:2,150:2,124,150:23,-1,150:3,-1:2,150,125,150:24,-1,150:3,-1:2,1" +
"50:19,126,150:6,-1,150:3,-1:2,150:7,145,150:18,-1,150:3,-1:2,146:26,-1,146:" +
"3,-1:2,150:25,56,-1,150:3,-1:2,150:2,127,150:23,-1,150:3,-1:2,150:4,128,150" +
":21,-1,150:3,-1:2,150:11,57,150:14,-1,150:3,-1:2,150:12,58,150:13,-1,150:3," +
"-1:2,150:15,59,150:10,-1,150:3,-1:2,146:15,60,146:7,130,146:2,-1,146:3,-1:2" +
",150:6,65,150:19,-1,150:3,-1:2,150:9,135,150:16,-1,150:3,-1:2,150:10,74,150" +
":15,-1,150:3,-1:2,150:4,78,150:21,-1,150:3,-1:2,150,136,150:24,-1,150:3,-1:" +
"2,150:2,89,150:23,-1,150:3,-1:2,150,138,150:24,-1,150:3,-1:2,150:4,98,150:2" +
"1,-1,150:3,-1:2,150:10,100,150:15,-1,150:3,-1:2,150:6,107,150:19,-1,150:3,-" +
"1:2,150,108,150:24,-1,150:3,-1:2,150:10,115,150:15,-1,150:3,-1:2,150:4,117," +
"150:21,-1,150:3,-1:2,150:6,122,150:19,-1,150:3,-1:2,150:7,129,150:18,-1,150" +
":3,-1:2,146:23,130,146:2,-1,146:3,-1:2,150:12,66,150:13,-1,150:3,-1:2,150,1" +
"11,150:24,-1,150:3,-1:2,150:4,118,150:21,-1,150:3,-1:2,150:4,133,150:3,132," +
"150:17,-1,150:3,-1:2,150:7,68,150:18,-1,150:3,-1:2,150:4,69,150:21,-1,150:3" +
",-1:2,150:6,70,150:19,-1,150:3,-1");

	public Token getToken ()
		throws java.io.IOException {
		int yy_lookahead;
		int yy_anchor = YY_NO_ANCHOR;
		int yy_state = yy_state_dtrans[yy_lexical_state];
		int yy_next_state = YY_NO_STATE;
		int yy_last_accept_state = YY_NO_STATE;
		boolean yy_initial = true;
		int yy_this_accept;

		yy_mark_start();
		yy_this_accept = yy_acpt[yy_state];
		if (YY_NOT_ACCEPT != yy_this_accept) {
			yy_last_accept_state = yy_state;
			yy_mark_end();
		}
		while (true) {
			if (yy_initial && yy_at_bol) yy_lookahead = YY_BOL;
			else yy_lookahead = yy_advance();
			yy_next_state = YY_F;
			yy_next_state = yy_nxt[yy_rmap[yy_state]][yy_cmap[yy_lookahead]];
			if (YY_EOF == yy_lookahead && true == yy_initial) {
				return null;
			}
			if (YY_F != yy_next_state) {
				yy_state = yy_next_state;
				yy_initial = false;
				yy_this_accept = yy_acpt[yy_state];
				if (YY_NOT_ACCEPT != yy_this_accept) {
					yy_last_accept_state = yy_state;
					yy_mark_end();
				}
			}
			else {
				if (YY_NO_STATE == yy_last_accept_state) {
					throw (new Error("Lexical Error: Unmatched Input."));
				}
				else {
					yy_anchor = yy_acpt[yy_last_accept_state];
					if (0 != (YY_END & yy_anchor)) {
						yy_move_end();
					}
					yy_to_mark();
					switch (yy_last_accept_state) {
					case 1:
						
					case -2:
						break;
					case 2:
						{
  return new Token(Token.ERROR, "Invalid input: " + yytext());
}
					case -3:
						break;
					case 3:
						{ return (new Token(Token.BODY,yytext()));}
					case -4:
						break;
					case 4:
						{}
					case -5:
						break;
					case 5:
						{}
					case -6:
						break;
					case 6:
						{return (new Token(Token.MATHMODE,yytext()));}
					case -7:
						break;
					case 7:
						{return (new Token(Token.NM,yytext()));}
					case -8:
						break;
					case 8:
						{return (new Token(Token.NEWLINE,yytext()));}
					case -9:
						break;
					case 9:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -10:
						break;
					case 10:
						{ return (new Token(Token.TEXT,yytext()));}
					case -11:
						break;
					case 11:
						{return (new Token(Token.ITEM,yytext()));}
					case -12:
						break;
					case 12:
						{ return (new Token(Token.DATE,yytext()));}
					case -13:
						break;
					case 13:
						{ return (new Token(Token.TITLE,yytext()));}
					case -14:
						break;
					case 14:
						{ return (new Token(Token.BF,yytext()));}
					case -15:
						break;
					case 15:
						{ return (new Token(Token.SECTION,yytext()));}
					case -16:
						break;
					case 16:
						{ return (new Token(Token.LABEL,yytext()));}
					case -17:
						break;
					case 17:
						{ return (new Token(Token.SUB_TITLE,yytext()));}
					case -18:
						break;
					case 18:
						{ return (new Token(Token.MAKE,yytext()));}
					case -19:
						break;
					case 19:
						{return (new Token(Token.END,yytext()));}
					case -20:
						break;
					case 20:
						{return (new Token(Token.END,yytext()));}
					case -21:
						break;
					case 21:
						{ return (new Token(Token.END,yytext()));}
					case -22:
						break;
					case 22:
						{return (new Token(Token.BEGIN,yytext()));}
					case -23:
						break;
					case 23:
						{ return (new Token(Token.BEGIN,yytext()));}
					case -24:
						break;
					case 24:
						{ return (new Token(Token.BEGIN,yytext()));}
					case -25:
						break;
					case 25:
						{ return (new Token(Token.DOC_CLASS,yytext()));}
					case -26:
						break;
					case 26:
						{ return (new Token(Token.PACKAGE,yytext()));}
					case -27:
						break;
					case 28:
						{
  return new Token(Token.ERROR, "Invalid input: " + yytext());
}
					case -28:
						break;
					case 29:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -29:
						break;
					case 30:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -30:
						break;
					case 31:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -31:
						break;
					case 32:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -32:
						break;
					case 33:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -33:
						break;
					case 34:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -34:
						break;
					case 35:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -35:
						break;
					case 36:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -36:
						break;
					case 37:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -37:
						break;
					case 38:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -38:
						break;
					case 39:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -39:
						break;
					case 40:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -40:
						break;
					case 41:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -41:
						break;
					case 42:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -42:
						break;
					case 43:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -43:
						break;
					case 44:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -44:
						break;
					case 45:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -45:
						break;
					case 46:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -46:
						break;
					case 47:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -47:
						break;
					case 48:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -48:
						break;
					case 49:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -49:
						break;
					case 50:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -50:
						break;
					case 51:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -51:
						break;
					case 52:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -52:
						break;
					case 53:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -53:
						break;
					case 54:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -54:
						break;
					case 55:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -55:
						break;
					case 56:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -56:
						break;
					case 57:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -57:
						break;
					case 58:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -58:
						break;
					case 59:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -59:
						break;
					case 60:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -60:
						break;
					case 61:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -61:
						break;
					case 62:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -62:
						break;
					case 63:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -63:
						break;
					case 64:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -64:
						break;
					case 65:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -65:
						break;
					case 66:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -66:
						break;
					case 67:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -67:
						break;
					case 68:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -68:
						break;
					case 69:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -69:
						break;
					case 70:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -70:
						break;
					case 71:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -71:
						break;
					case 72:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -72:
						break;
					case 73:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -73:
						break;
					case 74:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -74:
						break;
					case 75:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -75:
						break;
					case 76:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -76:
						break;
					case 77:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -77:
						break;
					case 78:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -78:
						break;
					case 79:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -79:
						break;
					case 80:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -80:
						break;
					case 81:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -81:
						break;
					case 82:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -82:
						break;
					case 83:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -83:
						break;
					case 84:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -84:
						break;
					case 85:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -85:
						break;
					case 86:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -86:
						break;
					case 87:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -87:
						break;
					case 88:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -88:
						break;
					case 89:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -89:
						break;
					case 90:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -90:
						break;
					case 91:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -91:
						break;
					case 92:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -92:
						break;
					case 93:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -93:
						break;
					case 94:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -94:
						break;
					case 95:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -95:
						break;
					case 96:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -96:
						break;
					case 97:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -97:
						break;
					case 98:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -98:
						break;
					case 99:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -99:
						break;
					case 100:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -100:
						break;
					case 101:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -101:
						break;
					case 102:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -102:
						break;
					case 103:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -103:
						break;
					case 104:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -104:
						break;
					case 105:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -105:
						break;
					case 106:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -106:
						break;
					case 107:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -107:
						break;
					case 108:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -108:
						break;
					case 109:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -109:
						break;
					case 110:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -110:
						break;
					case 111:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -111:
						break;
					case 112:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -112:
						break;
					case 113:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -113:
						break;
					case 114:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -114:
						break;
					case 115:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -115:
						break;
					case 116:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -116:
						break;
					case 117:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -117:
						break;
					case 118:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -118:
						break;
					case 119:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -119:
						break;
					case 120:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -120:
						break;
					case 121:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -121:
						break;
					case 122:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -122:
						break;
					case 123:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -123:
						break;
					case 124:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -124:
						break;
					case 125:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -125:
						break;
					case 126:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -126:
						break;
					case 127:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -127:
						break;
					case 128:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -128:
						break;
					case 129:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -129:
						break;
					case 130:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -130:
						break;
					case 131:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -131:
						break;
					case 132:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -132:
						break;
					case 133:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -133:
						break;
					case 134:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -134:
						break;
					case 135:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -135:
						break;
					case 136:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -136:
						break;
					case 137:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -137:
						break;
					case 138:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -138:
						break;
					case 139:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -139:
						break;
					case 140:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -140:
						break;
					case 141:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -141:
						break;
					case 142:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -142:
						break;
					case 143:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -143:
						break;
					case 144:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -144:
						break;
					case 145:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -145:
						break;
					case 146:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -146:
						break;
					case 147:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -147:
						break;
					case 148:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -148:
						break;
					case 149:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -149:
						break;
					case 150:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -150:
						break;
					case 151:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -151:
						break;
					case 152:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -152:
						break;
					case 153:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -153:
						break;
					case 154:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -154:
						break;
					default:
						yy_error(YY_E_INTERNAL,false);
					case -1:
					}
					yy_initial = true;
					yy_state = yy_state_dtrans[yy_lexical_state];
					yy_next_state = YY_NO_STATE;
					yy_last_accept_state = YY_NO_STATE;
					yy_mark_start();
					yy_this_accept = yy_acpt[yy_state];
					if (YY_NOT_ACCEPT != yy_this_accept) {
						yy_last_accept_state = yy_state;
						yy_mark_end();
					}
				}
			}
		}
	}
}
