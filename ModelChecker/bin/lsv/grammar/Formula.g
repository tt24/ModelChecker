/**
*This is an Antlr grammar used to generate the parser and lexer
*/
grammar Formula;

options {
  language = Java;
}
query returns [Formula result]: formula EOF { $result = $formula.result;};

formula returns [Formula result]: 
		      TRUE { $result = new Formula(true);}
        |  FALSE { $result = new Formula(false);}
        | {boolean op = false;} OPEN first (op_bool second {op = true;})? CLOSE 
         	{ if (op) {
        	 	  $result = new Formula( $first.result, $second.result,  $op_bool.result);
        	  } else {
        	 	  $result = new Formula($first.result);
        	 	} 
        	}
        | OPEN first op_bool ctl_end CLOSE {$result = new Formula($first.result, $ctl_end.result, $op_bool.result);}
        | neg ctl_init { $result = new Formula($neg.result, $ctl_init.result); }
 				| {boolean op = false;} OPEN ctl_init (op_bool ctl_end {op = true;})? CLOSE 
        	 	{ if (op) {
        	 	  $result = new Formula( $ctl_init.result,  $ctl_end.result, $op_bool.result);
        	 	  } else {
        	 	  $result = $ctl_init.result;
        	 	  } }
        | EXISTS OPEN first UNTIL second CLOSE { $result = new Formula("E", $first.result, $second.result, $UNTIL.text);}
        | ALL OPEN first UNTIL second CLOSE { $result = new Formula("A", $first.result, $second.result, $UNTIL.text);}
        | EXISTS OPEN first UNTIL ctl_end CLOSE { $result = new Formula("E", $first.result, $ctl_end.result, $UNTIL.text);}
        | ALL OPEN first UNTIL ctl_end CLOSE { $result = new Formula("A", $first.result, $ctl_end.result, $UNTIL.text);}
        | EXISTS OPEN ctl_init UNTIL ctl_end CLOSE { $result = new Formula( "E",$ctl_init.result,  $ctl_end.result, $UNTIL.text); }
        | ALL OPEN ctl_init UNTIL ctl_end CLOSE  { $result = new Formula( "A", $ctl_init.result,  $ctl_end.result, $UNTIL.text); }
        | TEMPORAL OPEN first op_bool second CLOSE { $result = new Formula($TEMPORAL.text, $first.result, $second.result, $op_bool.text);}
        | TEMPORAL OPEN first CLOSE { $result = new Formula($TEMPORAL.text, $first.result);}
        | TEMPORAL OPEN first op_bool ctl_end CLOSE { $result = new Formula($TEMPORAL.text, $first.result, $ctl_end.result, $op_bool.text);}
        | {boolean op = false;} TEMPORAL OPEN ctl_init (op_bool ctl_end {op = true;})? CLOSE
        	{ if (op) {
        	 	  $result = new Formula($TEMPORAL.text, $ctl_init.result,  $ctl_end.result, $op_bool.result);
        	 	  } else {
        	 	  $result = new Formula($TEMPORAL.text, $ctl_init.result);
        	 	  } }
        ;

first returns [String result]: ATOMIC {$result = $ATOMIC.text;};
second returns [String result]: ATOMIC {$result = $ATOMIC.text;};

ctl_init returns [Formula result]:
				formula {$result = $formula.result;}
			;

ctl_end returns [Formula result]:
				formula {$result = $formula.result;}
			;

neg returns [boolean result]  :
			'!' { $result = true; }
			;


op_bool returns [String result]: 
					'&&' {$result = "&&";}
				| '||'  {$result = "||"; }
				| '=>' {$result = "=>";}
				| '<=>' {$result = "<=>";}
				;

//LEXER
TRUE : 'True';
FALSE: 'False';
ALL  : 'A';
EXISTS : 'E';
UNTIL  : ('a'..'z')?'U'('a'..'z')?;
OPEN   : '(';
CLOSE  : ')';
ATOMIC :('!')*('a'..'z');
TEMPORAL:
      'A'('a'..'z')?'F'('a'..'z')?
	| 'A'('a'..'z')?'G'('a'..'z')?
	| 'A'('a'..'z')?'X'('a'..'z')?
	| 'E'('a'..'z')?'F'('a'..'z')?
	| 'E'('a'..'z')?'G'('a'..'z')?
	| 'E'('a'..'z')?'X'('a'..'z')?
	;


WS     :  (' ' | '\t' | '\r'| '\n' | '\f')+  {$channel=HIDDEN;};

	