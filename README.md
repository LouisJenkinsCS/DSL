# DSL

DSL is a very minimal domain specific language, with a problem domain being academic research, designed and created for a Theory of Computation course. Bundled with this program is a simple text editor which allows the user to execute (interpret) code written in the language, generate a graph of the Abstract Syntax Tree for the language, and a reduced Control Flow Graph.

## Screenshots

### Output from interpretation

![screenshots/output.png]

### Generated Abstract Syntax Tree

![screenshot/ast.png]

### Generated Control Flow Graph

![screenshot/cfg.png]

## Grammar

The BNF grammar can be seen below...

```
prog : prog stmt
  | stmt
  | /* Empty */
  ;

conditional : WHILE expr block
    | IF expr block 
    | IF expr block ELSE block
    ;

stmt : VAR NAME '=' expr ';' 
    | NAME '=' expr ';' 
    | conditional 
    | PRINT expr ';' 
    ;

stmt_list : stmt stmt_list 
    | /* Empty */   
    ;

block : '{' stmt_list '}'
    ;


expr : expr '+' expr 
    | expr '-' expr 
    | expr '*' expr 
    | expr '/' expr 
    | expr EQ expr
    | expr NEQ expr
    | expr GE expr 
    | expr LE expr 
    | expr '>' expr 
    | expr '<' expr 
    | '(' expr ')' 
    | INTEGER 
    | STRING 
    | NAME 
    ;

```


### Code Example

```
var x = 0;
var y = 100;
while (x < y) {
  if (x == 10) {
    print "X: " + x;
  }
}
```

## Developer's Note and Warning

I would like to note that this project was hacked together in a very brief time under *extremely* tense time constraints, and as such the code quality, to say the *least* is lacking. As well, the logic is counter-intuitive at times, and some things are still buggy and broken. I am officially done with the project, although it does deserve to be displayed here as a lot of work went into it. 

### Bugs

#### Control Flow Graph - Basic Block Reduction

Control Flow Graph incorrectly reduces the last node of a while loop into its own basic block due to the wonky reduction algorithm I devised. If anyone wants to work on it, that's one of the first things needing to be done; the Control Flow Graph algorithm needs an entirely new overhaul. It should be noted that control flow itself is correct, as it does introduce a back-edge from the incorrectly-constructed singleton basic block.
