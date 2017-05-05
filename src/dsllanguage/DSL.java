// Output created by jacc on Mon May 01 23:23:04 GMT 2017

package dsllanguage;
 import java.util.Collections; 
class DSL implements DSLTokens {
    private int yyss = 100;
    private int yytok;
    private int yysp = 0;
    private int[] yyst;
    protected int yyerrno = (-1);
    private int yyerrstatus = 3;
    private dsllanguage.ASTNodes.ASTNode[] yysv;
    private dsllanguage.ASTNodes.ASTNode yyrv;

    public boolean parse() {
        int yyn = 0;
        yysp = 0;
        yyst = new int[yyss];
        yyerrstatus = 3;
        yysv = new dsllanguage.ASTNodes.ASTNode[yyss];
        yytok = (lexer.getToken()
                 );
    loop:
        for (;;) {
            switch (yyn) {
                case 0:
                    yyst[yysp] = 0;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 58:
                    yyn = yys0();
                    continue;

                case 1:
                    yyst[yysp] = 1;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 59:
                    yyn = yys1();
                    continue;

                case 2:
                    yyst[yysp] = 2;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 60:
                    yyn = yys2();
                    continue;

                case 3:
                    yyst[yysp] = 3;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 61:
                    yyn = yys3();
                    continue;

                case 4:
                    yyst[yysp] = 4;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    yyerrstatus++;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 62:
                    switch (yytok) {
                        case INTEGER:
                            yyn = 12;
                            continue;
                        case NAME:
                            yyn = 13;
                            continue;
                        case STRING:
                            yyn = 14;
                            continue;
                        case '(':
                            yyn = 15;
                            continue;
                    }
                    yyn = 119;
                    continue;

                case 5:
                    yyst[yysp] = 5;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    yyerrstatus++;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 63:
                    switch (yytok) {
                        case '=':
                            yyn = 16;
                            continue;
                    }
                    yyn = 119;
                    continue;

                case 6:
                    yyst[yysp] = 6;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    yyerrstatus++;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 64:
                    switch (yytok) {
                        case INTEGER:
                            yyn = 12;
                            continue;
                        case NAME:
                            yyn = 13;
                            continue;
                        case STRING:
                            yyn = 14;
                            continue;
                        case '(':
                            yyn = 15;
                            continue;
                    }
                    yyn = 119;
                    continue;

                case 7:
                    yyst[yysp] = 7;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    yyerrstatus++;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 65:
                    switch (yytok) {
                        case NAME:
                            yyn = 18;
                            continue;
                    }
                    yyn = 119;
                    continue;

                case 8:
                    yyst[yysp] = 8;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    yyerrstatus++;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 66:
                    switch (yytok) {
                        case INTEGER:
                            yyn = 12;
                            continue;
                        case NAME:
                            yyn = 13;
                            continue;
                        case STRING:
                            yyn = 14;
                            continue;
                        case '(':
                            yyn = 15;
                            continue;
                    }
                    yyn = 119;
                    continue;

                case 9:
                    yyst[yysp] = 9;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    yyerrstatus++;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 67:
                    switch (yytok) {
                        case ';':
                            yyn = 20;
                            continue;
                    }
                    yyn = 119;
                    continue;

                case 10:
                    yyst[yysp] = 10;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 68:
                    yyn = yys10();
                    continue;

                case 11:
                    yyst[yysp] = 11;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 69:
                    yyn = yys11();
                    continue;

                case 12:
                    yyst[yysp] = 12;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    yyerrstatus++;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 70:
                    yyn = yys12();
                    continue;

                case 13:
                    yyst[yysp] = 13;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    yyerrstatus++;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 71:
                    yyn = yys13();
                    continue;

                case 14:
                    yyst[yysp] = 14;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    yyerrstatus++;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 72:
                    yyn = yys14();
                    continue;

                case 15:
                    yyst[yysp] = 15;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    yyerrstatus++;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 73:
                    switch (yytok) {
                        case INTEGER:
                            yyn = 12;
                            continue;
                        case NAME:
                            yyn = 13;
                            continue;
                        case STRING:
                            yyn = 14;
                            continue;
                        case '(':
                            yyn = 15;
                            continue;
                    }
                    yyn = 119;
                    continue;

                case 16:
                    yyst[yysp] = 16;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    yyerrstatus++;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 74:
                    switch (yytok) {
                        case INTEGER:
                            yyn = 12;
                            continue;
                        case NAME:
                            yyn = 13;
                            continue;
                        case STRING:
                            yyn = 14;
                            continue;
                        case '(':
                            yyn = 15;
                            continue;
                    }
                    yyn = 119;
                    continue;

                case 17:
                    yyst[yysp] = 17;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 75:
                    yyn = yys17();
                    continue;

                case 18:
                    yyst[yysp] = 18;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    yyerrstatus++;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 76:
                    switch (yytok) {
                        case '=':
                            yyn = 36;
                            continue;
                    }
                    yyn = 119;
                    continue;

                case 19:
                    yyst[yysp] = 19;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 77:
                    yyn = yys19();
                    continue;

                case 20:
                    yyst[yysp] = 20;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    yyerrstatus++;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 78:
                    yyn = yys20();
                    continue;

                case 21:
                    yyst[yysp] = 21;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 79:
                    yyn = yys21();
                    continue;

                case 22:
                    yyst[yysp] = 22;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    yyerrstatus++;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 80:
                    switch (yytok) {
                        case INTEGER:
                            yyn = 12;
                            continue;
                        case NAME:
                            yyn = 13;
                            continue;
                        case STRING:
                            yyn = 14;
                            continue;
                        case '(':
                            yyn = 15;
                            continue;
                    }
                    yyn = 119;
                    continue;

                case 23:
                    yyst[yysp] = 23;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    yyerrstatus++;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 81:
                    switch (yytok) {
                        case INTEGER:
                            yyn = 12;
                            continue;
                        case NAME:
                            yyn = 13;
                            continue;
                        case STRING:
                            yyn = 14;
                            continue;
                        case '(':
                            yyn = 15;
                            continue;
                    }
                    yyn = 119;
                    continue;

                case 24:
                    yyst[yysp] = 24;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    yyerrstatus++;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 82:
                    switch (yytok) {
                        case INTEGER:
                            yyn = 12;
                            continue;
                        case NAME:
                            yyn = 13;
                            continue;
                        case STRING:
                            yyn = 14;
                            continue;
                        case '(':
                            yyn = 15;
                            continue;
                    }
                    yyn = 119;
                    continue;

                case 25:
                    yyst[yysp] = 25;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    yyerrstatus++;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 83:
                    switch (yytok) {
                        case INTEGER:
                            yyn = 12;
                            continue;
                        case NAME:
                            yyn = 13;
                            continue;
                        case STRING:
                            yyn = 14;
                            continue;
                        case '(':
                            yyn = 15;
                            continue;
                    }
                    yyn = 119;
                    continue;

                case 26:
                    yyst[yysp] = 26;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    yyerrstatus++;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 84:
                    switch (yytok) {
                        case INTEGER:
                            yyn = 12;
                            continue;
                        case NAME:
                            yyn = 13;
                            continue;
                        case STRING:
                            yyn = 14;
                            continue;
                        case '(':
                            yyn = 15;
                            continue;
                    }
                    yyn = 119;
                    continue;

                case 27:
                    yyst[yysp] = 27;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    yyerrstatus++;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 85:
                    switch (yytok) {
                        case INTEGER:
                            yyn = 12;
                            continue;
                        case NAME:
                            yyn = 13;
                            continue;
                        case STRING:
                            yyn = 14;
                            continue;
                        case '(':
                            yyn = 15;
                            continue;
                    }
                    yyn = 119;
                    continue;

                case 28:
                    yyst[yysp] = 28;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    yyerrstatus++;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 86:
                    switch (yytok) {
                        case INTEGER:
                            yyn = 12;
                            continue;
                        case NAME:
                            yyn = 13;
                            continue;
                        case STRING:
                            yyn = 14;
                            continue;
                        case '(':
                            yyn = 15;
                            continue;
                    }
                    yyn = 119;
                    continue;

                case 29:
                    yyst[yysp] = 29;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    yyerrstatus++;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 87:
                    switch (yytok) {
                        case INTEGER:
                            yyn = 12;
                            continue;
                        case NAME:
                            yyn = 13;
                            continue;
                        case STRING:
                            yyn = 14;
                            continue;
                        case '(':
                            yyn = 15;
                            continue;
                    }
                    yyn = 119;
                    continue;

                case 30:
                    yyst[yysp] = 30;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    yyerrstatus++;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 88:
                    switch (yytok) {
                        case INTEGER:
                            yyn = 12;
                            continue;
                        case NAME:
                            yyn = 13;
                            continue;
                        case STRING:
                            yyn = 14;
                            continue;
                        case '(':
                            yyn = 15;
                            continue;
                    }
                    yyn = 119;
                    continue;

                case 31:
                    yyst[yysp] = 31;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    yyerrstatus++;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 89:
                    switch (yytok) {
                        case INTEGER:
                            yyn = 12;
                            continue;
                        case NAME:
                            yyn = 13;
                            continue;
                        case STRING:
                            yyn = 14;
                            continue;
                        case '(':
                            yyn = 15;
                            continue;
                    }
                    yyn = 119;
                    continue;

                case 32:
                    yyst[yysp] = 32;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    yyerrstatus++;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 90:
                    yyn = yys32();
                    continue;

                case 33:
                    yyst[yysp] = 33;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 91:
                    yyn = yys33();
                    continue;

                case 34:
                    yyst[yysp] = 34;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 92:
                    yyn = yys34();
                    continue;

                case 35:
                    yyst[yysp] = 35;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    yyerrstatus++;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 93:
                    yyn = yys35();
                    continue;

                case 36:
                    yyst[yysp] = 36;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    yyerrstatus++;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 94:
                    switch (yytok) {
                        case INTEGER:
                            yyn = 12;
                            continue;
                        case NAME:
                            yyn = 13;
                            continue;
                        case STRING:
                            yyn = 14;
                            continue;
                        case '(':
                            yyn = 15;
                            continue;
                    }
                    yyn = 119;
                    continue;

                case 37:
                    yyst[yysp] = 37;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 95:
                    yyn = yys37();
                    continue;

                case 38:
                    yyst[yysp] = 38;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    yyerrstatus++;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 96:
                    switch (yytok) {
                        case '{':
                            yyn = 32;
                            continue;
                    }
                    yyn = 119;
                    continue;

                case 39:
                    yyst[yysp] = 39;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 97:
                    yyn = yys39();
                    continue;

                case 40:
                    yyst[yysp] = 40;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 98:
                    yyn = yys40();
                    continue;

                case 41:
                    yyst[yysp] = 41;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 99:
                    yyn = yys41();
                    continue;

                case 42:
                    yyst[yysp] = 42;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 100:
                    yyn = yys42();
                    continue;

                case 43:
                    yyst[yysp] = 43;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 101:
                    yyn = yys43();
                    continue;

                case 44:
                    yyst[yysp] = 44;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 102:
                    yyn = yys44();
                    continue;

                case 45:
                    yyst[yysp] = 45;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 103:
                    yyn = yys45();
                    continue;

                case 46:
                    yyst[yysp] = 46;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 104:
                    yyn = yys46();
                    continue;

                case 47:
                    yyst[yysp] = 47;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 105:
                    yyn = yys47();
                    continue;

                case 48:
                    yyst[yysp] = 48;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 106:
                    yyn = yys48();
                    continue;

                case 49:
                    yyst[yysp] = 49;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 107:
                    yyn = yys49();
                    continue;

                case 50:
                    yyst[yysp] = 50;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 108:
                    switch (yytok) {
                        case '}':
                            yyn = 56;
                            continue;
                    }
                    yyn = 119;
                    continue;

                case 51:
                    yyst[yysp] = 51;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    yyerrstatus++;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 109:
                    yyn = yys51();
                    continue;

                case 52:
                    yyst[yysp] = 52;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    yyerrstatus++;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 110:
                    yyn = yys52();
                    continue;

                case 53:
                    yyst[yysp] = 53;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 111:
                    yyn = yys53();
                    continue;

                case 54:
                    yyst[yysp] = 54;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 112:
                    yyn = yys54();
                    continue;

                case 55:
                    yyst[yysp] = 55;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 113:
                    switch (yytok) {
                        case '}':
                            yyn = yyr12();
                            continue;
                    }
                    yyn = 119;
                    continue;

                case 56:
                    yyst[yysp] = 56;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    yyerrstatus++;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 114:
                    yyn = yys56();
                    continue;

                case 57:
                    yyst[yysp] = 57;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    yyerrstatus++;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 115:
                    yyn = yys57();
                    continue;

                case 116:
                    return true;
                case 117:
                    yyerror("stack overflow");
                case 118:
                    return false;
                case 119:
                    if (yyerrstatus>2) {
                        yyerror("syntax error");
                    }
                case 120 :
                    if (yyerrstatus==0) {
                        if ((lexer.getToken()
                             )==ENDINPUT) {
                            return false;
                        }
                        lexer.nextToken()
                        ;
                        yyn = 58 + yyst[yysp-1];
                        continue;
                    } else {
                        yyerrstatus = 0;
                        while (yysp>0) {
                            switch(yyst[yysp-1]) {
                                case 0:
                                    yyn = 9;
                                    continue loop;
                                case 1:
                                    yyn = 9;
                                    continue loop;
                                case 32:
                                    yyn = 9;
                                    continue loop;
                                case 49:
                                    yyn = 9;
                                    continue loop;
                            }
                            yysp--;
                        }
                        return false;
                    }
            }
        }
    }

    protected void yyexpand() {
        int[] newyyst = new int[2*yyst.length];
        dsllanguage.ASTNodes.ASTNode[] newyysv = new dsllanguage.ASTNodes.ASTNode[2*yyst.length];
        for (int i=0; i<yyst.length; i++) {
            newyyst[i] = yyst[i];
            newyysv[i] = yysv[i];
        }
        yyst = newyyst;
        yysv = newyysv;
    }

    public void yyerrok() {
        yyerrstatus = 3;
    }

    public void yyclearin() {
        yytok = (lexer.nextToken()
                );
    }

    private int yys0() {
        switch (yytok) {
            case IF:
                return 4;
            case NAME:
                return 5;
            case PRINT:
                return 6;
            case VAR:
                return 7;
            case WHILE:
                return 8;
            case error:
                return 9;
            case ENDINPUT:
                return yyr3();
        }
        return 119;
    }

    private int yys1() {
        switch (yytok) {
            case ENDINPUT:
                return 116;
            case IF:
                return 4;
            case NAME:
                return 5;
            case PRINT:
                return 6;
            case VAR:
                return 7;
            case WHILE:
                return 8;
            case error:
                return 9;
        }
        return 119;
    }

    private int yys2() {
        switch (yytok) {
            case NAME:
            case error:
            case '}':
            case WHILE:
            case VAR:
            case PRINT:
            case ENDINPUT:
            case IF:
                return yyr9();
        }
        return 119;
    }

    private int yys3() {
        switch (yytok) {
            case NAME:
            case ENDINPUT:
            case error:
            case WHILE:
            case VAR:
            case PRINT:
            case IF:
                return yyr2();
        }
        return 119;
    }

    private int yys10() {
        switch (yytok) {
            case NAME:
            case ENDINPUT:
            case error:
            case WHILE:
            case VAR:
            case PRINT:
            case IF:
                return yyr1();
        }
        return 119;
    }

    private int yys11() {
        switch (yytok) {
            case EQ:
                return 22;
            case GE:
                return 23;
            case LE:
                return 24;
            case NEQ:
                return 25;
            case '*':
                return 26;
            case '+':
                return 27;
            case '-':
                return 28;
            case '/':
                return 29;
            case '<':
                return 30;
            case '>':
                return 31;
            case '{':
                return 32;
        }
        return 119;
    }

    private int yys12() {
        switch (yytok) {
            case ')':
            case '{':
            case LE:
            case GE:
            case '>':
            case '<':
            case ';':
            case '/':
            case '-':
            case '+':
            case '*':
            case NEQ:
            case EQ:
                return yyr26();
        }
        return 119;
    }

    private int yys13() {
        switch (yytok) {
            case ')':
            case '{':
            case LE:
            case GE:
            case '>':
            case '<':
            case ';':
            case '/':
            case '-':
            case '+':
            case '*':
            case NEQ:
            case EQ:
                return yyr28();
        }
        return 119;
    }

    private int yys14() {
        switch (yytok) {
            case ')':
            case '{':
            case LE:
            case GE:
            case '>':
            case '<':
            case ';':
            case '/':
            case '-':
            case '+':
            case '*':
            case NEQ:
            case EQ:
                return yyr27();
        }
        return 119;
    }

    private int yys17() {
        switch (yytok) {
            case EQ:
                return 22;
            case GE:
                return 23;
            case LE:
                return 24;
            case NEQ:
                return 25;
            case '*':
                return 26;
            case '+':
                return 27;
            case '-':
                return 28;
            case '/':
                return 29;
            case '<':
                return 30;
            case '>':
                return 31;
            case ';':
                return 35;
        }
        return 119;
    }

    private int yys19() {
        switch (yytok) {
            case EQ:
                return 22;
            case GE:
                return 23;
            case LE:
                return 24;
            case NEQ:
                return 25;
            case '*':
                return 26;
            case '+':
                return 27;
            case '-':
                return 28;
            case '/':
                return 29;
            case '<':
                return 30;
            case '>':
                return 31;
            case '{':
                return 32;
        }
        return 119;
    }

    private int yys20() {
        switch (yytok) {
            case NAME:
            case error:
            case '}':
            case WHILE:
            case VAR:
            case PRINT:
            case ENDINPUT:
            case IF:
                return yyr11();
        }
        return 119;
    }

    private int yys21() {
        switch (yytok) {
            case ELSE:
                return 38;
            case NAME:
            case error:
            case '}':
            case WHILE:
            case VAR:
            case PRINT:
            case ENDINPUT:
            case IF:
                return yyr5();
        }
        return 119;
    }

    private int yys32() {
        switch (yytok) {
            case IF:
                return 4;
            case NAME:
                return 5;
            case PRINT:
                return 6;
            case VAR:
                return 7;
            case WHILE:
                return 8;
            case error:
                return 9;
            case '}':
                return yyr13();
        }
        return 119;
    }

    private int yys33() {
        switch (yytok) {
            case EQ:
                return 22;
            case GE:
                return 23;
            case LE:
                return 24;
            case NEQ:
                return 25;
            case '*':
                return 26;
            case '+':
                return 27;
            case '-':
                return 28;
            case '/':
                return 29;
            case '<':
                return 30;
            case '>':
                return 31;
            case ')':
                return 51;
        }
        return 119;
    }

    private int yys34() {
        switch (yytok) {
            case EQ:
                return 22;
            case GE:
                return 23;
            case LE:
                return 24;
            case NEQ:
                return 25;
            case '*':
                return 26;
            case '+':
                return 27;
            case '-':
                return 28;
            case '/':
                return 29;
            case '<':
                return 30;
            case '>':
                return 31;
            case ';':
                return 52;
        }
        return 119;
    }

    private int yys35() {
        switch (yytok) {
            case NAME:
            case error:
            case '}':
            case WHILE:
            case VAR:
            case PRINT:
            case ENDINPUT:
            case IF:
                return yyr10();
        }
        return 119;
    }

    private int yys37() {
        switch (yytok) {
            case NAME:
            case error:
            case '}':
            case WHILE:
            case VAR:
            case PRINT:
            case ENDINPUT:
            case IF:
                return yyr4();
        }
        return 119;
    }

    private int yys39() {
        switch (yytok) {
            case EQ:
                return 22;
            case GE:
                return 23;
            case LE:
                return 24;
            case NEQ:
                return 25;
            case '*':
                return 26;
            case '+':
                return 27;
            case '-':
                return 28;
            case '/':
                return 29;
            case '<':
                return 30;
            case '>':
                return 31;
            case ')':
            case '{':
            case ';':
                return yyr19();
        }
        return 119;
    }

    private int yys40() {
        switch (yytok) {
            case EQ:
                return 22;
            case GE:
                return 23;
            case LE:
                return 24;
            case NEQ:
                return 25;
            case '*':
                return 26;
            case '+':
                return 27;
            case '-':
                return 28;
            case '/':
                return 29;
            case '<':
                return 30;
            case '>':
                return 31;
            case ')':
            case '{':
            case ';':
                return yyr21();
        }
        return 119;
    }

    private int yys41() {
        switch (yytok) {
            case EQ:
                return 22;
            case GE:
                return 23;
            case LE:
                return 24;
            case NEQ:
                return 25;
            case '*':
                return 26;
            case '+':
                return 27;
            case '-':
                return 28;
            case '/':
                return 29;
            case '<':
                return 30;
            case '>':
                return 31;
            case ')':
            case '{':
            case ';':
                return yyr22();
        }
        return 119;
    }

    private int yys42() {
        switch (yytok) {
            case EQ:
                return 22;
            case GE:
                return 23;
            case LE:
                return 24;
            case NEQ:
                return 25;
            case '*':
                return 26;
            case '+':
                return 27;
            case '-':
                return 28;
            case '/':
                return 29;
            case '<':
                return 30;
            case '>':
                return 31;
            case ')':
            case '{':
            case ';':
                return yyr20();
        }
        return 119;
    }

    private int yys43() {
        switch (yytok) {
            case EQ:
                return 22;
            case GE:
                return 23;
            case LE:
                return 24;
            case NEQ:
                return 25;
            case '*':
                return 26;
            case '+':
                return 27;
            case '-':
                return 28;
            case '/':
                return 29;
            case '<':
                return 30;
            case '>':
                return 31;
            case ')':
            case '{':
            case ';':
                return yyr17();
        }
        return 119;
    }

    private int yys44() {
        switch (yytok) {
            case EQ:
                return 22;
            case GE:
                return 23;
            case LE:
                return 24;
            case NEQ:
                return 25;
            case '*':
                return 26;
            case '+':
                return 27;
            case '-':
                return 28;
            case '/':
                return 29;
            case '<':
                return 30;
            case '>':
                return 31;
            case ')':
            case '{':
            case ';':
                return yyr15();
        }
        return 119;
    }

    private int yys45() {
        switch (yytok) {
            case EQ:
                return 22;
            case GE:
                return 23;
            case LE:
                return 24;
            case NEQ:
                return 25;
            case '*':
                return 26;
            case '+':
                return 27;
            case '-':
                return 28;
            case '/':
                return 29;
            case '<':
                return 30;
            case '>':
                return 31;
            case ')':
            case '{':
            case ';':
                return yyr16();
        }
        return 119;
    }

    private int yys46() {
        switch (yytok) {
            case EQ:
                return 22;
            case GE:
                return 23;
            case LE:
                return 24;
            case NEQ:
                return 25;
            case '*':
                return 26;
            case '+':
                return 27;
            case '-':
                return 28;
            case '/':
                return 29;
            case '<':
                return 30;
            case '>':
                return 31;
            case ')':
            case '{':
            case ';':
                return yyr18();
        }
        return 119;
    }

    private int yys47() {
        switch (yytok) {
            case EQ:
                return 22;
            case GE:
                return 23;
            case LE:
                return 24;
            case NEQ:
                return 25;
            case '*':
                return 26;
            case '+':
                return 27;
            case '-':
                return 28;
            case '/':
                return 29;
            case '<':
                return 30;
            case '>':
                return 31;
            case ')':
            case '{':
            case ';':
                return yyr24();
        }
        return 119;
    }

    private int yys48() {
        switch (yytok) {
            case EQ:
                return 22;
            case GE:
                return 23;
            case LE:
                return 24;
            case NEQ:
                return 25;
            case '*':
                return 26;
            case '+':
                return 27;
            case '-':
                return 28;
            case '/':
                return 29;
            case '<':
                return 30;
            case '>':
                return 31;
            case ')':
            case '{':
            case ';':
                return yyr23();
        }
        return 119;
    }

    private int yys49() {
        switch (yytok) {
            case IF:
                return 4;
            case NAME:
                return 5;
            case PRINT:
                return 6;
            case VAR:
                return 7;
            case WHILE:
                return 8;
            case error:
                return 9;
            case '}':
                return yyr13();
        }
        return 119;
    }

    private int yys51() {
        switch (yytok) {
            case ')':
            case '{':
            case LE:
            case GE:
            case '>':
            case '<':
            case ';':
            case '/':
            case '-':
            case '+':
            case '*':
            case NEQ:
            case EQ:
                return yyr25();
        }
        return 119;
    }

    private int yys52() {
        switch (yytok) {
            case NAME:
            case error:
            case '}':
            case WHILE:
            case VAR:
            case PRINT:
            case ENDINPUT:
            case IF:
                return yyr8();
        }
        return 119;
    }

    private int yys53() {
        switch (yytok) {
            case EQ:
                return 22;
            case GE:
                return 23;
            case LE:
                return 24;
            case NEQ:
                return 25;
            case '*':
                return 26;
            case '+':
                return 27;
            case '-':
                return 28;
            case '/':
                return 29;
            case '<':
                return 30;
            case '>':
                return 31;
            case ';':
                return 57;
        }
        return 119;
    }

    private int yys54() {
        switch (yytok) {
            case NAME:
            case error:
            case '}':
            case WHILE:
            case VAR:
            case PRINT:
            case ENDINPUT:
            case IF:
                return yyr6();
        }
        return 119;
    }

    private int yys56() {
        switch (yytok) {
            case NAME:
            case error:
            case '}':
            case WHILE:
            case VAR:
            case PRINT:
            case IF:
            case ENDINPUT:
            case ELSE:
                return yyr14();
        }
        return 119;
    }

    private int yys57() {
        switch (yytok) {
            case NAME:
            case error:
            case '}':
            case WHILE:
            case VAR:
            case PRINT:
            case ENDINPUT:
            case IF:
                return yyr7();
        }
        return 119;
    }

    private int yyr1() { // prog : prog stmt
        { yyrv = yysv[yysp-1]; yyrv.execute(); DSLPanel.addNode(yyrv);}
        yysv[yysp-=2] = yyrv;
        return 1;
    }

    private int yyr2() { // prog : stmt
        { yyrv = yysv[yysp-1]; yyrv.execute(); DSLPanel.addNode(yyrv);}
        yysv[yysp-=1] = yyrv;
        return 1;
    }

    private int yyr3() { // prog : /* empty */
        return 1;
    }

    private int yyr4() { // conditional : WHILE expr block
        { yyrv = new dsllanguage.ASTNodes.WhileConditionalASTNode(yysv[yysp-2], yysv[yysp-1]); }
        yysv[yysp-=3] = yyrv;
        return 2;
    }

    private int yyr5() { // conditional : IF expr block
        { yyrv = new  dsllanguage.ASTNodes.IfConditionalASTNode(yysv[yysp-2], yysv[yysp-1], null); }
        yysv[yysp-=3] = yyrv;
        return 2;
    }

    private int yyr6() { // conditional : IF expr block ELSE block
        { yyrv = new  dsllanguage.ASTNodes.IfConditionalASTNode(yysv[yysp-4], yysv[yysp-3], yysv[yysp-1]); }
        yysv[yysp-=5] = yyrv;
        return 2;
    }

    private int yyr15() { // expr : expr '+' expr
        { yyrv = new  dsllanguage.ASTNodes.AdditionBinaryASTNode(yysv[yysp-3], yysv[yysp-1]); }
        yysv[yysp-=3] = yyrv;
        return yypexpr();
    }

    private int yyr16() { // expr : expr '-' expr
        { yyrv = new  dsllanguage.ASTNodes.SubtractionBinaryASTNode(yysv[yysp-3], yysv[yysp-1]); }
        yysv[yysp-=3] = yyrv;
        return yypexpr();
    }

    private int yyr17() { // expr : expr '*' expr
        { yyrv = new  dsllanguage.ASTNodes.MultiplicationBinaryASTNode(yysv[yysp-3], yysv[yysp-1]); }
        yysv[yysp-=3] = yyrv;
        return yypexpr();
    }

    private int yyr18() { // expr : expr '/' expr
        { yyrv = new  dsllanguage.ASTNodes.DivisionBinaryASTNode(yysv[yysp-3], yysv[yysp-1]); }
        yysv[yysp-=3] = yyrv;
        return yypexpr();
    }

    private int yyr19() { // expr : expr EQ expr
        { yyrv = new dsllanguage.ASTNodes.ComparisonBinaryASTNode(yysv[yysp-3], yysv[yysp-1], "=="); }
        yysv[yysp-=3] = yyrv;
        return yypexpr();
    }

    private int yyr20() { // expr : expr NEQ expr
        { yyrv = new dsllanguage.ASTNodes.ComparisonBinaryASTNode(yysv[yysp-3], yysv[yysp-1], "!="); }
        yysv[yysp-=3] = yyrv;
        return yypexpr();
    }

    private int yyr21() { // expr : expr GE expr
        { yyrv = new dsllanguage.ASTNodes.ComparisonBinaryASTNode(yysv[yysp-3], yysv[yysp-1], ">="); }
        yysv[yysp-=3] = yyrv;
        return yypexpr();
    }

    private int yyr22() { // expr : expr LE expr
        { yyrv = new dsllanguage.ASTNodes.ComparisonBinaryASTNode(yysv[yysp-3], yysv[yysp-1], "<="); }
        yysv[yysp-=3] = yyrv;
        return yypexpr();
    }

    private int yyr23() { // expr : expr '>' expr
        { yyrv = new dsllanguage.ASTNodes.ComparisonBinaryASTNode(yysv[yysp-3], yysv[yysp-1], ">"); }
        yysv[yysp-=3] = yyrv;
        return yypexpr();
    }

    private int yyr24() { // expr : expr '<' expr
        { yyrv = new dsllanguage.ASTNodes.ComparisonBinaryASTNode(yysv[yysp-3], yysv[yysp-1], "<"); }
        yysv[yysp-=3] = yyrv;
        return yypexpr();
    }

    private int yyr25() { // expr : '(' expr ')'
        { yyrv = yysv[yysp-2]; }
        yysv[yysp-=3] = yyrv;
        return yypexpr();
    }

    private int yyr26() { // expr : INTEGER
        { yyrv = yysv[yysp-1]; }
        yysv[yysp-=1] = yyrv;
        return yypexpr();
    }

    private int yyr27() { // expr : STRING
        { yyrv = yysv[yysp-1]; }
        yysv[yysp-=1] = yyrv;
        return yypexpr();
    }

    private int yyr28() { // expr : NAME
        { yyrv = yysv[yysp-1]; }
        yysv[yysp-=1] = yyrv;
        return yypexpr();
    }

    private int yypexpr() {
        switch (yyst[yysp-1]) {
            case 31: return 48;
            case 30: return 47;
            case 29: return 46;
            case 28: return 45;
            case 27: return 44;
            case 26: return 43;
            case 25: return 42;
            case 24: return 41;
            case 23: return 40;
            case 22: return 39;
            case 16: return 34;
            case 15: return 33;
            case 8: return 19;
            case 6: return 17;
            case 4: return 11;
            default: return 53;
        }
    }

    private int yyr14() { // block : '{' stmt_list '}'
        { yyrv = yysv[yysp-2]; }
        yysv[yysp-=3] = yyrv;
        switch (yyst[yysp-1]) {
            case 19: return 37;
            case 11: return 21;
            default: return 54;
        }
    }

    private int yyr7() { // stmt : VAR NAME '=' expr ';'
        { yyrv = new  dsllanguage.ASTNodes.DefinitionASTNode(yysv[yysp-4], yysv[yysp-2]); }
        yysv[yysp-=5] = yyrv;
        return yypstmt();
    }

    private int yyr8() { // stmt : NAME '=' expr ';'
        { yyrv = new  dsllanguage.ASTNodes.AssignmentASTNode(yysv[yysp-4], yysv[yysp-2]); }
        yysv[yysp-=4] = yyrv;
        return yypstmt();
    }

    private int yyr9() { // stmt : conditional
        { yyrv = yysv[yysp-1]; }
        yysv[yysp-=1] = yyrv;
        return yypstmt();
    }

    private int yyr10() { // stmt : PRINT expr ';'
        { yyrv = new  dsllanguage.ASTNodes.PrintASTNode(yysv[yysp-2]); }
        yysv[yysp-=3] = yyrv;
        return yypstmt();
    }

    private int yyr11() { // stmt : error ';'
        yysp -= 2;
        return yypstmt();
    }

    private int yypstmt() {
        switch (yyst[yysp-1]) {
            case 1: return 10;
            case 0: return 3;
            default: return 49;
        }
    }

    private int yyr12() { // stmt_list : stmt stmt_list
        { (( dsllanguage.ASTNodes.StatementASTNode)yysv[yysp-1]).body.add(0, yysv[yysp-2]); yyrv = yysv[yysp-1]; }
        yysv[yysp-=2] = yyrv;
        return yypstmt_list();
    }

    private int yyr13() { // stmt_list : /* empty */
        { yyrv = new  dsllanguage.ASTNodes.StatementASTNode(); }
        yysv[yysp-=0] = yyrv;
        return yypstmt_list();
    }

    private int yypstmt_list() {
        switch (yyst[yysp-1]) {
            case 32: return 50;
            default: return 55;
        }
    }

    protected String[] yyerrmsgs = {
    };


private DSLLexer lexer;
DSL(DSLLexer lexer) { this.lexer = lexer; }
private void yyerror(String msg) { lexer.yyerror(msg); }

}
