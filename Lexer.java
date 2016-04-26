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

    int mathmode = 0;
    int equmode = 0;
    int nItems = 0;
    boolean insideItem = false;
    String itemText = "";
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
		/* 10 */ YY_START,
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
		/* 27 */ YY_NO_ANCHOR,
		/* 28 */ YY_NO_ANCHOR,
		/* 29 */ YY_NOT_ACCEPT,
		/* 30 */ YY_NO_ANCHOR,
		/* 31 */ YY_NO_ANCHOR,
		/* 32 */ YY_NOT_ACCEPT,
		/* 33 */ YY_NO_ANCHOR,
		/* 34 */ YY_NOT_ACCEPT,
		/* 35 */ YY_NOT_ACCEPT,
		/* 36 */ YY_NOT_ACCEPT,
		/* 37 */ YY_NOT_ACCEPT,
		/* 38 */ YY_NOT_ACCEPT,
		/* 39 */ YY_NOT_ACCEPT,
		/* 40 */ YY_NOT_ACCEPT,
		/* 41 */ YY_NOT_ACCEPT,
		/* 42 */ YY_NOT_ACCEPT,
		/* 43 */ YY_NOT_ACCEPT,
		/* 44 */ YY_NOT_ACCEPT,
		/* 45 */ YY_NOT_ACCEPT,
		/* 46 */ YY_NOT_ACCEPT,
		/* 47 */ YY_NOT_ACCEPT,
		/* 48 */ YY_NOT_ACCEPT,
		/* 49 */ YY_NOT_ACCEPT,
		/* 50 */ YY_NOT_ACCEPT,
		/* 51 */ YY_NOT_ACCEPT,
		/* 52 */ YY_NOT_ACCEPT,
		/* 53 */ YY_NOT_ACCEPT,
		/* 54 */ YY_NOT_ACCEPT,
		/* 55 */ YY_NOT_ACCEPT,
		/* 56 */ YY_NOT_ACCEPT,
		/* 57 */ YY_NOT_ACCEPT,
		/* 58 */ YY_NOT_ACCEPT,
		/* 59 */ YY_NOT_ACCEPT,
		/* 60 */ YY_NOT_ACCEPT,
		/* 61 */ YY_NOT_ACCEPT,
		/* 62 */ YY_NOT_ACCEPT,
		/* 63 */ YY_NOT_ACCEPT,
		/* 64 */ YY_NOT_ACCEPT,
		/* 65 */ YY_NOT_ACCEPT,
		/* 66 */ YY_NOT_ACCEPT,
		/* 67 */ YY_NOT_ACCEPT,
		/* 68 */ YY_NOT_ACCEPT,
		/* 69 */ YY_NOT_ACCEPT,
		/* 70 */ YY_NOT_ACCEPT,
		/* 71 */ YY_NOT_ACCEPT,
		/* 72 */ YY_NOT_ACCEPT,
		/* 73 */ YY_NOT_ACCEPT,
		/* 74 */ YY_NOT_ACCEPT,
		/* 75 */ YY_NOT_ACCEPT,
		/* 76 */ YY_NOT_ACCEPT,
		/* 77 */ YY_NOT_ACCEPT,
		/* 78 */ YY_NOT_ACCEPT,
		/* 79 */ YY_NOT_ACCEPT,
		/* 80 */ YY_NOT_ACCEPT,
		/* 81 */ YY_NOT_ACCEPT,
		/* 82 */ YY_NOT_ACCEPT,
		/* 83 */ YY_NOT_ACCEPT,
		/* 84 */ YY_NOT_ACCEPT,
		/* 85 */ YY_NOT_ACCEPT,
		/* 86 */ YY_NOT_ACCEPT,
		/* 87 */ YY_NOT_ACCEPT,
		/* 88 */ YY_NOT_ACCEPT,
		/* 89 */ YY_NOT_ACCEPT,
		/* 90 */ YY_NOT_ACCEPT,
		/* 91 */ YY_NOT_ACCEPT,
		/* 92 */ YY_NOT_ACCEPT,
		/* 93 */ YY_NOT_ACCEPT,
		/* 94 */ YY_NOT_ACCEPT,
		/* 95 */ YY_NOT_ACCEPT,
		/* 96 */ YY_NOT_ACCEPT,
		/* 97 */ YY_NOT_ACCEPT,
		/* 98 */ YY_NOT_ACCEPT,
		/* 99 */ YY_NOT_ACCEPT,
		/* 100 */ YY_NOT_ACCEPT,
		/* 101 */ YY_NOT_ACCEPT,
		/* 102 */ YY_NOT_ACCEPT,
		/* 103 */ YY_NOT_ACCEPT,
		/* 104 */ YY_NOT_ACCEPT,
		/* 105 */ YY_NOT_ACCEPT,
		/* 106 */ YY_NOT_ACCEPT,
		/* 107 */ YY_NOT_ACCEPT,
		/* 108 */ YY_NOT_ACCEPT,
		/* 109 */ YY_NOT_ACCEPT,
		/* 110 */ YY_NOT_ACCEPT,
		/* 111 */ YY_NOT_ACCEPT,
		/* 112 */ YY_NOT_ACCEPT,
		/* 113 */ YY_NOT_ACCEPT,
		/* 114 */ YY_NOT_ACCEPT,
		/* 115 */ YY_NOT_ACCEPT,
		/* 116 */ YY_NOT_ACCEPT,
		/* 117 */ YY_NOT_ACCEPT,
		/* 118 */ YY_NOT_ACCEPT,
		/* 119 */ YY_NOT_ACCEPT,
		/* 120 */ YY_NOT_ACCEPT,
		/* 121 */ YY_NOT_ACCEPT,
		/* 122 */ YY_NOT_ACCEPT,
		/* 123 */ YY_NOT_ACCEPT,
		/* 124 */ YY_NOT_ACCEPT,
		/* 125 */ YY_NOT_ACCEPT,
		/* 126 */ YY_NOT_ACCEPT,
		/* 127 */ YY_NOT_ACCEPT,
		/* 128 */ YY_NOT_ACCEPT,
		/* 129 */ YY_NOT_ACCEPT,
		/* 130 */ YY_NOT_ACCEPT,
		/* 131 */ YY_NOT_ACCEPT,
		/* 132 */ YY_NOT_ACCEPT,
		/* 133 */ YY_NOT_ACCEPT,
		/* 134 */ YY_NOT_ACCEPT,
		/* 135 */ YY_NOT_ACCEPT,
		/* 136 */ YY_NOT_ACCEPT,
		/* 137 */ YY_NOT_ACCEPT,
		/* 138 */ YY_NOT_ACCEPT,
		/* 139 */ YY_NOT_ACCEPT,
		/* 140 */ YY_NOT_ACCEPT,
		/* 141 */ YY_NOT_ACCEPT,
		/* 142 */ YY_NOT_ACCEPT,
		/* 143 */ YY_NOT_ACCEPT,
		/* 144 */ YY_NOT_ACCEPT,
		/* 145 */ YY_NOT_ACCEPT,
		/* 146 */ YY_NOT_ACCEPT,
		/* 147 */ YY_NOT_ACCEPT,
		/* 148 */ YY_NOT_ACCEPT,
		/* 149 */ YY_NOT_ACCEPT,
		/* 150 */ YY_NOT_ACCEPT,
		/* 151 */ YY_NOT_ACCEPT,
		/* 152 */ YY_NOT_ACCEPT,
		/* 153 */ YY_NOT_ACCEPT,
		/* 154 */ YY_NOT_ACCEPT,
		/* 155 */ YY_NOT_ACCEPT,
		/* 156 */ YY_NOT_ACCEPT,
		/* 157 */ YY_NOT_ACCEPT,
		/* 158 */ YY_NOT_ACCEPT,
		/* 159 */ YY_NOT_ACCEPT,
		/* 160 */ YY_NOT_ACCEPT,
		/* 161 */ YY_NOT_ACCEPT,
		/* 162 */ YY_NOT_ACCEPT,
		/* 163 */ YY_NOT_ACCEPT,
		/* 164 */ YY_NOT_ACCEPT,
		/* 165 */ YY_NOT_ACCEPT,
		/* 166 */ YY_NOT_ACCEPT,
		/* 167 */ YY_NOT_ACCEPT
	};
	private int yy_cmap[] = unpackFromString(1,130,
"17:10,27,17:2,34,17:22,28,25,17:5,32,17:3,32,29:10,17:3,32,17:29,23,1,24,32" +
",31,17,7,10,11,6,5,15,19,30,3,30,21,4,20,13,12,22,33,36,8,2,9,30:2,14,30,26" +
",16,17,18,17:2,35,0")[0];

	private int yy_rmap[] = unpackFromString(1,168,
"0,1,2,3,4,1:2,5,1:2,6,7,1:5,8,1:9,9,10,11,6,12,13,1,14,15,16,17,18,19,7,20," +
"21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45," +
"46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,8,63,64,65,66,67,68,69,7" +
"0,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,9" +
"5,96,97,98,99,100,101,102,103,104,105,106,107,9,10,108,109,110,111,112,113," +
"114,115,116,117,118,119,120,121,122,123,124,125,126,127,128,129,130,131,132" +
",133,134,135,136,137,138,139,140,141,142,143")[0];

	private int yy_nxt[][] = unpackFromString(144,37,
"1,2,3:14,31,33:2,3:4,33:2,4,3,5,6,7,3,33,8,3,-1,30,3,-1:38,9,29,32,34,35,36" +
",-1,37,38,39,-1:9,133,-1:18,3:14,-1:3,3:4,-1:3,3,-1:2,3:3,-1,3,-1:2,3,-1,4:" +
"26,-1,4:6,-1:2,4,-1:29,7,-1:9,10:14,-1,10:8,-1,10,-1:3,10:5,-1,10,-1,40:17," +
"11,40:8,-1,40:6,-1:2,40,-1,84:17,17,84:8,-1,84:6,-1:2,84,-1,130:17,27,130:8" +
",-1,130:6,-1:2,130,-1,131:17,28,131:8,-1,131:6,-1:2,131,-1:3,132,-1,41,-1:3" +
"2,40:26,-1,40:6,-1:2,40,-1:2,134,-1:41,42,-1:42,43,-1:22,44,-1:7,45,-1:4,46" +
",-1:29,137,-1:3,135,-1:35,157,-1:33,47,-1:45,136,-1:32,51,-1:32,52,-1:66,53" +
",-1:2,54,-1:45,55,-1:44,58,-1:38,138,-1:19,59,-1:52,12,-1:21,61,-1:47,62,-1" +
":32,63,-1:29,13,-1:40,64,-1:29,139,-1:56,65,-1:17,66,-1:38,14,-1:41,68,-1:3" +
"0,69,-1:35,140,-1,70,71,-1:66,72,-1:20,73,-1:23,143,-1:42,141,-1:25,74,-1:4" +
"9,15,-1:37,75,-1:53,76,-1:15,160,-1:35,77,-1:30,78,-1:34,83,-1:34,84:26,-1," +
"84:6,-1:2,84,-1:9,86,-1:39,165,-1:37,145,-1:36,16,-1:27,88,-1:53,146,-1:18," +
"161,-1,89,90,-1:32,91,-1:54,92,-1:23,93,-1:38,94,-1:32,18,-1:64,98,-1:15,14" +
"8,-1:28,99,-1:35,100,-1:35,101,-1:54,149,-1:27,103,-1:44,162,-1:22,104,-1:4" +
"0,105,-1:32,19,-1:57,107,-1:13,108,-1:46,109,-1:27,151,-1:52,111,-1:23,112," +
"-1:38,113,-1:32,114,-1:43,115,-1:43,20,-1:40,117,-1:16,118,-1:35,119,-1:54," +
"120,-1:34,21,-1:31,121,-1:31,153,-1:29,167:26,-1,167:6,-1:2,167,-1:26,155,-" +
"1:13,123,-1:38,124,-1:49,22,-1:36,23,-1:30,156,-1:37,127,-1:39,154,-1:38,24" +
",-1:20,129,-1:52,25,-1:36,26,-1:20,49,-1:41,48,-1:34,50,-1:41,164,-1:28,60," +
"-1:45,56,-1:30,67,-1:34,142,-1:35,144,-1:50,82,-1:32,79,-1:35,81,-1:30,85,-" +
"1:33,95,-1:41,96,-1:32,102,-1:44,106,-1:30,150,-1:44,152,-1:30,116,-1:31,12" +
"2,-1:42,125,-1:29,130:26,-1,130:6,-1:2,130,-1:5,126,-1:44,128,-1:28,57,-1:3" +
"4,159,-1:35,80,-1:45,87,-1:27,97,-1:39,110,-1:32,131:26,-1,131:6,-1:2,131,-" +
"1:2,158,-1:54,147,-1:17,167:15,163,167:7,166,167:2,-1,167:6,-1:2,167,-1,167" +
":23,166,167:2,-1,167:6,-1:2,167");

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
  //return new Token(Token.ERROR, "Invalid input: " + yytext());
}
					case -3:
						break;
					case 3:
						{
  if (insideItem && mathmode % 2 == 0 && equmode % 2 == 0) {
    itemText += yytext() + " ";
  } else if (mathmode%2 == 1 || equmode % 2 == 1) {
    System.out.println("1st var");
    return (new Token(Token.VAR,yytext()));
  } else {
    return new Token(Token.BODY, yytext());
  }
}
					case -4:
						break;
					case 4:
						{}
					case -5:
						break;
					case 5:
						{
  if (insideItem) {
    Token t = new Token(Token.BODY, itemText);
    itemText = "";
    insideItem = false;
    return t;
  }
}
					case -6:
						break;
					case 6:
						{ mathmode++; if(mathmode % 2 == 1) { return (new Token(Token.MATHMODE,yytext()));}}
					case -7:
						break;
					case 7:
						{
  if (insideItem && mathmode % 2 == 0 && equmode % 2 == 0) {
    itemText += yytext() + " ";
  } else {
    return (new Token(Token.NM,yytext()));
  }
}
					case -8:
						break;
					case 8:
						{return (new Token(Token.OPERATOR,yytext()));}
					case -9:
						break;
					case 9:
						{return (new Token(Token.NEWLINE,yytext()));}
					case -10:
						break;
					case 10:
						{
  if (mathmode % 2 == 0 && equmode % 2 == 0) {
    return new Token(Token.BODY,yytext());
  } else if (mathmode%2 == 1) {
  	if (equmode%2 == 1) {
  		return (new Token(Token.FUNC,yytext()));
  	}
  	else {
	  	System.out.println("2nd var");
	    return new Token(Token.VAR, yytext());
	   }
  }
}
					case -11:
						break;
					case 11:
						{ return (new Token(Token.TEXT,yytext()));}
					case -12:
						break;
					case 12:
						{
  insideItem = true;
  nItems++;
  return (new Token(Token.ITEM,yytext()));
}
					case -13:
						break;
					case 13:
						{ return (new Token(Token.DATE,yytext()));}
					case -14:
						break;
					case 14:
						{ return (new Token(Token.TITLE,yytext()));}
					case -15:
						break;
					case 15:
						{ return (new Token(Token.BF,yytext()));}
					case -16:
						break;
					case 16:
						{ return (new Token(Token.SECTION,yytext()));}
					case -17:
						break;
					case 17:
						{ return (new Token(Token.LABEL,yytext()));}
					case -18:
						break;
					case 18:
						{ return (new Token(Token.SUB_TITLE,yytext()));}
					case -19:
						break;
					case 19:
						{ return (new Token(Token.MAKE,yytext()));}
					case -20:
						break;
					case 20:
						{
  return new Token(Token.ERROR, "Line: " + yyline + " Undefined control sequence: " + yytext());
}
					case -21:
						break;
					case 21:
						{ if (nItems > 0) {
													return (new Token(Token.END,yytext()));}
													else {
														return new Token(Token.ERROR, "Line: " + yyline + " Item list with missing \\item");
														}
												}
					case -22:
						break;
					case 22:
						{ mathmode++; equmode++; return (new Token(Token.END,yytext()));}
					case -23:
						break;
					case 23:
						{ return (new Token(Token.END,yytext()));}
					case -24:
						break;
					case 24:
						{
  itemText = "";
  nItems = 0;
  return (new Token(Token.BEGIN,yytext()));
}
					case -25:
						break;
					case 25:
						{mathmode++; equmode++; {return (new Token(Token.BEGIN,yytext()));}}
					case -26:
						break;
					case 26:
						{ return (new Token(Token.BEGIN,yytext()));}
					case -27:
						break;
					case 27:
						{ return (new Token(Token.DOC_CLASS,yytext()));}
					case -28:
						break;
					case 28:
						{ return (new Token(Token.PACKAGE,yytext()));}
					case -29:
						break;
					case 30:
						
					case -30:
						break;
					case 31:
						{
  //return new Token(Token.ERROR, "Invalid input: " + yytext());
}
					case -31:
						break;
					case 33:
						{
  //return new Token(Token.ERROR, "Invalid input: " + yytext());
}
					case -32:
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
